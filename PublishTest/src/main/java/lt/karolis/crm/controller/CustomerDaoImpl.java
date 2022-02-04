package lt.karolis.crm.controller;

import lt.karolis.crm.controller.domain.Customer;
import lt.karolis.crm.exception.CustomerNotFounException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    EntityManager entityManager;

    @Override
//    @Transactional // use later to check
    public List<Customer> getCustomers() {
//        sessionFactory.getCurrentSession(); //No CurrentSessionContext configured!
        List<Customer> list = entityManager.createQuery("from Customer", Customer.class).getResultList(); // veikia
//        entityManager.createQuery("from Customers");
//        Customer customer = session.get(Customer.class, 1L);
//        session.getTransaction().commit();
//        return List.of(customer);
        return list;
    }

    @Override
    public Customer getCustomer(Integer id) {
        List<Customer> customers = entityManager.createQuery("select c from Customer c where id = :id")
                .setParameter("id", id)
                .getResultList();
//        Customer customer = entityManager.createQuery()get(Customer.class, id);
        return customers.stream()
                .findFirst()
                .orElseThrow(() -> new CustomerNotFounException("", id));
    }
}
