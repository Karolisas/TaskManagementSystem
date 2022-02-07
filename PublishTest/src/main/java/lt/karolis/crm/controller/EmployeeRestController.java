package lt.karolis.crm.controller;

import lt.karolis.crm.dao.EmployeeDao;
import lt.karolis.crm.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    @Autowired @Qualifier("EmployeeDaoImpl_JPA_ABC")
    private EmployeeDao employeeDao;

    @GetMapping
    public List<Employee> getListEmployees(Model model) {
        List<Employee> employeeList = employeeDao.getAllEmployees();
        return employeeList;
//        return "list-customers";
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        return employeeDao.createEmployee(employee);
    }

    @GetMapping(path = "/{employeeId}")
    public Employee getEmployee(@PathVariable Long employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @PutMapping(path = "/{employeeId}")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeDao.createEmployee(employee);
    }

    @DeleteMapping(path = "/{employeeId}")
    public Employee deleteEmployee(@PathVariable Long employeeId) {
        return employeeDao.deleteEmployee(employeeId);
    }

}
