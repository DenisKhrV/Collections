package pro.sky.Collections.service;

import pro.sky.Collections.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    int getMaxSalary(Integer department);

    int getSumSalary(Integer department);

    int getMinSalary(Integer department);
    Collection<Employee> getEmployees(Integer department);
    Map<Integer, List<Employee>> getEmployees();
}
