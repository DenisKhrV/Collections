package pro.sky.Collections.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Collections.model.Employee;
import pro.sky.Collections.service.DepartmentService;
import pro.sky.Collections.service.DepartmentServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    private Employee getEmployeeWithMaxSalary(@RequestParam("departmentId") Integer department) {
        return departmentService.getEmployeeWithMaxSalary(department);
    }
    @GetMapping("/min-salary")
    private Employee getEmployeeWithMinSalary(@RequestParam Integer departmentId) {
        return departmentService.getEmployeeWithMinSalary(departmentId);
    }
    @GetMapping(value = "/all", params = {"departmentId"})
    private Collection<Employee> getEmployees(@RequestParam Integer departmentId) {
        return departmentService.getEmployees(departmentId);
    }
    @GetMapping("/all")
    private Map<Integer, List<Employee>> getEmployees() {
        return departmentService.getEmployees();
    }
}
