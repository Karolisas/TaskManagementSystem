package lt.karolis.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller //doesn't work
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @GetMapping
    public String listCustomers (Model model){
        return "list-customers";
    }
}
