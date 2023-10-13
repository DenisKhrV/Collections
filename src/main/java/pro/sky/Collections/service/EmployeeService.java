package pro.sky.Collections.service;

import pro.sky.Collections.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    public Employee addEmployee(String firstName, String lastName, String patronymic);

    public Employee removeEmployee(String firstName, String lastName, String patronymic);

    public Employee findEmployee(String firstName, String lastName, String patronymic);

    public Collection<Employee> getAll();
}
