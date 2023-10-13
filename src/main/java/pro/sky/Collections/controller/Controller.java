package pro.sky.Collections.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.Collections.model.Employee;
import pro.sky.Collections.service.EmployeeService;
import pro.sky.Collections.service.EmployeeServiceImpl;

import java.util.Collection;


@RestController
@RequestMapping(path = "/employee")
public class Controller {
    @ExceptionHandler(RuntimeException.class)
    public String Exception(RuntimeException e) {
        return e.getMessage();
    }
    private final EmployeeService employeeService;

    public Controller(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("patronymic") String patronymic) {
        return employeeService.addEmployee(firstName, lastName, patronymic);
    }

    @GetMapping(path = "/remove")
    private Employee remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("patronymic") String patronymic) {
        return employeeService.removeEmployee(firstName, lastName, patronymic);
    }

    @GetMapping(path = "/find")
    private Employee find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("patronymic") String patronymic) {
        return employeeService.findEmployee(firstName, lastName, patronymic);
    }
    @GetMapping
    private Collection<Employee> getAll() {
        return employeeService.getAll();
    }
}
