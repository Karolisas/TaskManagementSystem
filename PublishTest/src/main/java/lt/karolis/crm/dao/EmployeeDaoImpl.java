package lt.karolis.crm.dao;

import lt.karolis.crm.domain.Employee;
import lt.karolis.crm.exception.CustomerNotFounException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {


    EntityManager entityManager;

    @Autowired
    public EmployeeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        //get query hibernate session
        Session session = entityManager.unwrap(Session.class);
        // create a query
        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        //execute query and get result list
        List<Employee> employees = query.getResultList();
//        return results
        return employees;
//        return Collections.emptyList();
    }

    @Override
    @Transactional
    public Employee getEmployeeById(Long employeeId) {
        Session session = entityManager.unwrap(Session.class);
        Employee employee = session.get(Employee.class, employeeId);
        if (employee == null) {
            throw new CustomerNotFounException();
        }
        return employee;
    }

    @Override
    @Transactional // without does not work
    public Employee createEmployee(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(employee);
        return employee;
    }

    @Override
    @Transactional // without does not work
    public Employee deleteEmployee(Long employeeId) {
        Session session = entityManager.unwrap(Session.class);
        Employee employee = session.get(Employee.class, employeeId);
        if (employee == null) {
            throw new RuntimeException("Employee already deleted id=" + employeeId);
        }
        session.delete(employee);

        return employee;
    }
}
