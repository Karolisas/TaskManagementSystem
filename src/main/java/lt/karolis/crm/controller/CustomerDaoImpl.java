package lt.karolis.crm.controller;

import lt.karolis.crm.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Customer> getCustomer() {
//        sessionFactory.getCurrentSession(); //No CurrentSessionContext configured!
        List<Customer> list = entityManager.createQuery("from Customer", Customer.class).getResultList(); // veikia
//        entityManager.createQuery("from Customers");
//        Customer customer = session.get(Customer.class, 1L);
//        session.getTransaction().commit();
//        return List.of(customer);
        return list;
    }
}
