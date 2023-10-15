package pro.sky.Collections.service;

import pro.sky.Collections.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMaxSalary(Integer department);
    Employee getEmployeeWithMinSalary(Integer department);
    Collection<Employee> getEmployees(Integer department);
    Map<Integer, List<Employee>> getEmployees();
}
