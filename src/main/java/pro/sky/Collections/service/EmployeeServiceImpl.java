package pro.sky.Collections.service;

import org.springframework.stereotype.Service;
import pro.sky.Collections.exceptions.EmployeeAlreadyAddedException;
import pro.sky.Collections.exceptions.EmployeeNotFoundException;
import pro.sky.Collections.exceptions.EmployeeStorageIsFullException;
import pro.sky.Collections.model.Employee;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();
    private static final int MAX_POSSIBLE_NUMBER_OF_EMPLOYEES = 5;

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_POSSIBLE_NUMBER_OF_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме");
        }

        String key = getKey(firstName, lastName);

        Employee employee = new Employee(firstName, lastName);

        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Уже есть такой сотрудник");
        }
        employees.put(key, employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        Employee employee = employees.get(key);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employees.remove(key);
        return employee;
    }


    public Employee findEmployee(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        Employee employee = employees.get(key);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }
    public Collection<Employee> getAll() {
        return employees.values();
    }

    private String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }
}
