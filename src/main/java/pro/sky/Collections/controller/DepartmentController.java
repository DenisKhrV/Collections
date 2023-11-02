package pro.sky.Collections.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.Collections.exceptions.EmployeeNotFoundException;
import pro.sky.Collections.model.Employee;
import pro.sky.Collections.service.DepartmentServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {
    @ExceptionHandler({EmployeeNotFoundException.class})
    public String Exception(RuntimeException e) {
        return e.getMessage();
    }
    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{id}/salary/max")
    private int getEmployeeWithMaxSalary(@PathVariable Integer id) {
        return departmentService.getMaxSalary(id);
    }
    @GetMapping("/{id}/salary/min")
    private int getEmployeeWithMinSalary(@PathVariable Integer id) {
        return departmentService.getMinSalary(id);
    }
    @GetMapping("/{id}/salary/sum")
    private int getSumSalary(@PathVariable Integer id) {
        return departmentService.getSumSalary(id);
    }
    @GetMapping("/{id}/employees")
    private Collection<Employee> getEmployees(@PathVariable Integer id) {
        return departmentService.getEmployees(id);
    }
    @GetMapping("/employees")
    private Map<Integer, List<Employee>> getEmployees() {
        return departmentService.getEmployees();
    }
}
