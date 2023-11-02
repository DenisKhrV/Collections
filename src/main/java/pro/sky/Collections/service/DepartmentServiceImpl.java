package pro.sky.Collections.service;

import org.springframework.stereotype.Service;
import pro.sky.Collections.exceptions.EmployeeNotFoundException;
import pro.sky.Collections.model.Employee;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public int getMaxSalary(Integer department) {
        Employee employeeWithMaxSalary = employeeService.getAll()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return employeeWithMaxSalary.getSalary();
    }

    @Override
    public int getSumSalary(Integer department) {
        return employeeService.getAll()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary).sum();
    }
    @Override
    public int getMinSalary(Integer department) {
        Employee employeeWithMinSalary = employeeService.getAll()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return employeeWithMinSalary.getSalary();
    }

    @Override
    public Collection<Employee> getEmployees(Integer department) {
        return employeeService.getAll()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getEmployees() {
        return employeeService.getAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
