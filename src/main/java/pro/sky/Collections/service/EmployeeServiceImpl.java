package pro.sky.Collections.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.Collections.exceptions.EmployeeAlreadyAddedException;
import pro.sky.Collections.exceptions.EmployeeNameIsIncorrect;
import pro.sky.Collections.exceptions.EmployeeNotFoundException;
import pro.sky.Collections.exceptions.EmployeeStorageIsFullException;
import pro.sky.Collections.model.Employee;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();
    private static final int MAX_POSSIBLE_NUMBER_OF_EMPLOYEES = 5;

//    public EmployeeServiceImpl() {
//        addEmployee("Ivan", "Ivanov", "Ivanovich", 20000, 1);
//        addEmployee("Stepan", "Ivanov", "Stepanovich", 30000, 4);
//        addEmployee("Petr", "Petrov", "Ivanovich", 50000, 5);
//        addEmployee("Semen", "Petrov", "Ivanovich", 10000, 5);
//    }

    public Employee addEmployee(String firstName, String lastName, String patronymic, int salary, int department) {
        if (employees.size() >= MAX_POSSIBLE_NUMBER_OF_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме");
        }
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new EmployeeNameIsIncorrect("Некорректное ФИО");
        }

        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);
        patronymic = StringUtils.capitalize(patronymic);

        String key = getKey(firstName, lastName, patronymic);

        Employee employee = new Employee(firstName, lastName, patronymic, salary, department);

        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Уже есть такой сотрудник");
        }
        employees.put(key, employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName, String patronymic) {
        String key = getKey(firstName, lastName, patronymic);
        Employee employee = employees.get(key);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employees.remove(key);
        return employee;
    }


    public Employee findEmployee(String firstName, String lastName, String patronymic) {
        String key = getKey(firstName, lastName, patronymic);
        Employee employee = employees.get(key);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }

    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    private String getKey(String firstName, String lastName, String patronymic) {
        return firstName + lastName + patronymic;
    }
}
