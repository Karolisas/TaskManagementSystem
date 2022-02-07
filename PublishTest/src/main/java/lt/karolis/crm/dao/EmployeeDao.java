package lt.karolis.crm.dao;

import lt.karolis.crm.domain.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long employeeId);

    Employee createEmployee(Employee employee);

    Employee deleteEmployee(Long employeeId);
}
