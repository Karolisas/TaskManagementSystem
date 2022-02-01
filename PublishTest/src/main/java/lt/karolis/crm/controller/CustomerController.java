package lt.karolis.crm.controller;

import lt.karolis.crm.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller //doesn't work
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

//    @GetMapping
//    public String listCustomers(Model model) {
//        customerDao.getCustomer();
//
//        return "list-customers";
//    }

    @GetMapping
    public String listCustomers(Model model) {
        List<Customer> customerList = customerDao.getCustomer();
        Customer customer = new Customer();
        customer.setCompany("Aladin");
        customer.setId(1);
        customer.setName("Albinas");
        customerList.add(customer);
        model.addAttribute("customers", customerList);
        return "list-customers";
    }
}
