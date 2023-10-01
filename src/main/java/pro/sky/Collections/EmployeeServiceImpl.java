package pro.sky.Collections;

import org.springframework.stereotype.Service;
import pro.sky.Collections.Exceptions.EmployeeAlreadyAddedException;
import pro.sky.Collections.Exceptions.EmployeeNotFoundException;
import pro.sky.Collections.Exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private static final int MAX_POSSIBLE_NUMBER_OF_EMPLOYEES = 5;

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_POSSIBLE_NUMBER_OF_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме");
        }

        Employee employee = new Employee(firstName, lastName);

        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Уже есть такой сотрудник");
        }
        employees.add(employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employees.remove(employee);
        return employee;
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }

    public List<Employee> list() {
        return employees;
    }
}
