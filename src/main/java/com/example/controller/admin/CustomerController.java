package com.example.controller.admin;

import com.example.Entity.Customer;
import com.example.dto.CustomerDto;
import com.example.dto.OrderDto;
import com.example.service.CustomerService;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CustomerController {
    private OrderService orderService;
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/admin/customer")
    public String listCustomers(Model model) {
        List<CustomerDto> customerDtos = customerService.getAllCustomers();
        List<OrderDto> orderDtos = orderService.getAllOrders();
        model.addAttribute("customers", customerDtos);
        model.addAttribute("orders", orderDtos);
        return "admin/page/customer-page";
    }

    @GetMapping("/admin/customer/create")
    public String createCustomerForm(Model model) {
        CustomerDto customerDto = new CustomerDto();
        List<OrderDto> orderDtos = orderService.getAllOrders();
        model.addAttribute("orders", orderDtos);
        model.addAttribute("customer", customerDto);
        return "admin/create/create-customer";
    }

    @PostMapping("/admin/customer/create")
    public String createCustomer(@ModelAttribute("customer") CustomerDto customer) {
        customerService.save(customer);
        return "redirect:/admin/customer";
    }

    @GetMapping("/admin/customer/update")
    public String updateCustomerForm(@RequestParam("id") Long id, Model model) {
        CustomerDto customerDto = customerService.getCustomerById(id);
        List<OrderDto> orderDtos = orderService.getAllOrders();
        model.addAttribute("orders", orderDtos);
        model.addAttribute("customer", customerDto);
        return "/admin/update/update-customer";
    }

    @PostMapping("/admin/customer/update")
    public String updateCustomer(@ModelAttribute("customer") CustomerDto customer) {
        customerService.update(customer);
        return "redirect:/admin/customer";
    }

    @GetMapping("/admin/customer/delete")
    public String deleteCustomer(@RequestParam("id") Long id) {
        customerService.delete(id);
        return "redirect:/admin/customer";
    }

    @GetMapping("/admin/customer/search")
    public String searchCustomer(@RequestParam("name") String name, Model model) {
        List<Customer> customers = customerService.searchCustomer(name);
        model.addAttribute("customers", customers);
        return "admin/page/customer-page";
    }

}
