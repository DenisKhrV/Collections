package pro.sky.Collections.service;

import org.junit.jupiter.api.Test;
import pro.sky.Collections.exceptions.EmployeeAlreadyAddedException;
import pro.sky.Collections.exceptions.EmployeeNameIsIncorrect;
import pro.sky.Collections.exceptions.EmployeeNotFoundException;
import pro.sky.Collections.exceptions.EmployeeStorageIsFullException;
import pro.sky.Collections.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Test
    void addEmployee_returnEqualCapitalizeName() {
        //Подготовка ожидаемого результата
        Employee expectedEmployee = new Employee("Vasiliy", "Pupkin", "Alexeevich", 20000, 1);

        //Начало теста
        Employee actualEmployee = employeeService.addEmployee("vasiliy", "pupkin", "alexeevich", 20000, 1);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void addEmployee_returnEmployeeStorageIsFullException() {
        //Подготовка входных данных
        Employee employee = employeeService.addEmployee("vasiliy", "pupkin", "alexeevich", 20000, 1);
        Employee employee2 = employeeService.addEmployee("nikolay", "frolov", "dmitrievich", 40000, 1);
        Employee employee3 = employeeService.addEmployee("ivan", "ivanov", "dmitrievich", 40000, 1);
        Employee employee4 = employeeService.addEmployee("stepan", "petrov", "dmitrievich", 40000, 1);
        Employee employee5 = employeeService.addEmployee("dmitriy", "frolov", "petrovich", 40000, 1);

        //Подготовка ожидаемого результата
        String expectedMessage = "Превышен лимит количества сотрудников в фирме";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeStorageIsFullException.class,
                () -> employeeService.addEmployee("Ivan", "Ivaniv", "Ivanovich", 35000, 2)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void addEmployee_returnEmployeeNameIsIncorrect() {
        //Подготовка ожидаемого результата
        String expectedMessage = "Некорректное ФИО";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNameIsIncorrect.class,
                () -> employeeService.addEmployee("123", "Ivaniv", "Ivanovich", 35000, 2)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void addEmployee_returnEmployeeAlreadyAddedException() {
        Employee employee = employeeService.addEmployee("ivan", "ivanov", "ivanovich", 40000, 1);
        //Подготовка ожидаемого результата
        String expectedMessage = "Уже есть такой сотрудник";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeAlreadyAddedException.class,
                () -> employeeService.addEmployee("Ivan", "Ivanov", "Ivanovich", 35000, 2)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void removeEmployee_returnEqualEmployee() {
        //Подготовка ожидаемого результата
        Employee expectedEmployee = new Employee("Vasiliy", "Pupkin", "Alexeevich", 20000, 1);
        employeeService.addEmployee("Vasiliy", "Pupkin", "Alexeevich", 20000, 1);

        //Начало теста
        Employee actualEmployee = employeeService.removeEmployee("Vasiliy", "Pupkin", "Alexeevich");
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void removeEmployee_returnEmployeeNotFoundException() {
        //Подготовка ожидаемого результата
        String expectedMessage = "Сотрудник не найден";

        //Начало теста
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee("Vasiliy", "Pupkin", "Alexeevich"));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void findEmployee_returnEqualEmployee() {
        //Подготовка ожидаемого результата
        Employee expectedEmployee = new Employee("Vasiliy", "Pupkin", "Alexeevich", 20000, 1);
        employeeService.addEmployee("Vasiliy", "Pupkin", "Alexeevich", 20000, 1);

        //Начало теста
        Employee actualEmployee = employeeService.findEmployee("Vasiliy", "Pupkin", "Alexeevich");
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void findEmployee_returnEmployeeNotFoundException() {
        //Подготовка ожидаемого результата
        String expectedMessage = "Сотрудник не найден";

        //Начало теста
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("Vasiliy", "Pupkin", "Alexeevich"));
        assertEquals(expectedMessage, exception.getMessage());
    }


    @Test
    void getAll() {
        Employee employee1 = employeeService.addEmployee("Stepan", "Ivanov", "Stepanovich", 30000, 4);
        Employee employee2 = employeeService.addEmployee("Ivan", "Ivanov", "Ivanovich", 20000, 1);
        //Подготовка ожидаемого результата
        Collection<Employee> expectedCollection = List.of(employee1, employee2);
        System.out.println(employeeService.getAll());
        //Начало теста
        Collection<Employee> actualCollection = employeeService.getAll();
        assertEquals(expectedCollection.toString(), actualCollection.toString());

//        return Collections.unmodifiableCollection(employees.values()
    }
}