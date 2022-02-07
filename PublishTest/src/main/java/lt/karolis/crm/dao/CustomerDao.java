package lt.karolis.crm.dao;

import lt.karolis.crm.domain.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> getCustomers();
    Customer getCustomer( Integer id);
}
