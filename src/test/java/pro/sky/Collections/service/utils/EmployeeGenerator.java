package pro.sky.Collections.service.utils;

import pro.sky.Collections.model.Employee;

import java.util.Arrays;
import java.util.List;

public class EmployeeGenerator {
    //Подготовка входных данных
    public  static Employee employee1 = new Employee("Vasiliy", "Pupkin", "Alexeevich", 20000, 1);
    public  static Employee employee2 = new Employee("Nikolay", "Frolov", "Dmitrievich", 40000, 1);
    public  static Employee employee3 = new Employee("Ivan", "Ivanov", "Dmitrievich", 40000, 2);
    public  static Employee employee4 = new Employee("Stepan", "Petrov", "Dmitrievich", 60000, 3);
    public  static Employee employee5 = new Employee("Dmitriy", "Sidorov", "Petrovich", 80000, 1);

    public static List<Employee> getEmployeeList() {
        return Arrays.asList(employee1, employee2, employee3, employee4, employee5);
    }

    public static int getEmployeeSalary(Employee employee) {
        return employee.getSalary();
    }
}
