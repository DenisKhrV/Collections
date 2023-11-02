package pro.sky.Collections.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Collections.exceptions.EmployeeNotFoundException;
import pro.sky.Collections.model.Employee;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static pro.sky.Collections.service.utils.EmployeeGenerator.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void getMaxSalary_shouldReturnMaxSalaryInDepartment() {
        //Подготовка входных данных
        Integer departmentId = 1;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getEmployeeList());
        int expectedSalary = getEmployeeSalary(employee5);
        //Начало теста
        int actualSalary = departmentService.getMaxSalary(departmentId);
        assertEquals(expectedSalary, actualSalary);
    }

    @Test
    void getMaxSalary_shouldReturnEmployeeNotFoundException() {
        //Подготовка входных данных
        Integer departmentId = 1;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage = "Employee not found";

        //Начало теста
        Exception exception = assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.getMaxSalary(departmentId));
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getMinSalary_shouldReturnMinSalaryInDepartment() {
        //Подготовка входных данных
        Integer departmentId = 1;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getEmployeeList());
        int expectedSalary = getEmployeeSalary(employee1);
        //Начало теста
        int actualSalary = departmentService.getMinSalary(departmentId);
        assertEquals(expectedSalary, actualSalary);
    }

    @Test
    void getMinSalary_shouldReturnEmployeeNotFoundException() {
        //Подготовка входных данных
        Integer departmentId = 1;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage = "Employee not found";

        //Начало теста
        Exception exception = assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.getMaxSalary(departmentId));
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getSumSalary_shouldReturnSumSalary() {
        //Подготовка входных данных
        Integer departmentId = 1;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getEmployeeList());
        int expectedSalary = (getEmployeeSalary(employee1) + getEmployeeSalary(employee2) + getEmployeeSalary(employee5));
        //Начало теста
        int actualSalary = departmentService.getSumSalary(departmentId);
        assertEquals(expectedSalary, actualSalary);
    }

    @Test
    void getSumSalary_shouldReturnZero() {
        //Подготовка входных данных
        Integer departmentId = 4;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getEmployeeList());
        int expectedSalary = 0;
        //Начало теста
        int actualSalary = departmentService.getSumSalary(departmentId);
        assertEquals(expectedSalary, actualSalary);
    }

    @Test
    void getEmployeesWithDepartmentId_shouldReturnListEmployeesByDepartment() {
        //Подготовка входных данных
        Integer departmentId = 1;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getEmployeeList());
        List<Employee> expectedList = new ArrayList<>(List.of(employee1, employee2, employee5));

        //Начало теста
        List<Employee> actualList = (List<Employee>) departmentService.getEmployees(departmentId);
        assertEquals(expectedList, actualList);
    }

    @Test
    void getEmployees_shouldReturnE() {
        //Подготовка входных данных
        Integer departmentId1 = 1;
        Integer departmentId2 = 2;
        Integer departmentId3 = 3;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getEmployeeList());
        Map<Integer, List<Employee>> expectedMap = new HashMap<>();
        expectedMap.put(departmentId1, Arrays.asList(employee1, employee2, employee5));
        expectedMap.put(departmentId2, Collections.singletonList(employee3));
        expectedMap.put(departmentId3, Collections.singletonList(employee4));

        //Начало теста
        Map<Integer, List<Employee>> actualMap = departmentService.getEmployees();
        assertEquals(expectedMap, actualMap);

    }
}