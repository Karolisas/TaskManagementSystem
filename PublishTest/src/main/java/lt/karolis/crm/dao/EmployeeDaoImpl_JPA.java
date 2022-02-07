package lt.karolis.crm.dao;

import lt.karolis.crm.domain.Employee;
import lt.karolis.crm.exception.CustomerNotFounException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository(value = "EmployeeDaoImpl_JPA_ABC")
//@Qualifier("EmployeeDaoImpl_JPA_ABC")
public class EmployeeDaoImpl_JPA implements EmployeeDao {
    EntityManager entityManager;

    @Autowired
    public EmployeeDaoImpl_JPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        // create a query
        Query query = entityManager.createQuery("from Employee");
        //execute query and get result list
        List<Employee> employees = query.getResultList();
//        return results
        return employees;
//        return Collections.emptyList();
    }

    @Override
    @Transactional
    public Employee getEmployeeById(Long employeeId) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        if (employee == null) {
            throw new CustomerNotFounException();
        }
        return employee;
    }

    @Override
    @Transactional // without does not work
    public Employee createEmployee(Employee employee) {
        Employee savedEmployee = entityManager.merge(employee);
        employee.setId(savedEmployee.getId());
        return employee;
    }

    @Override
    @Transactional // without does not work
    public Employee deleteEmployee(Long employeeId) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        Query query = entityManager.createQuery("delete from Employees where id = :employeeUd");
//        entityManager.delete not exist
        query.setParameter("employeeUd", employeeId);
        query.executeUpdate();
        return employee;
    }
}
