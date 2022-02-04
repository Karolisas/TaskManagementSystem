package lt.karolis.crm.controller;

import lt.karolis.crm.controller.domain.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> getCustomers();
    Customer getCustomer( Integer id);
}
