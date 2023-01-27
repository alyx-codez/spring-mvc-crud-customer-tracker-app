package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// Inject the customer DAO 
//	@Autowired
//	private CustomerDAO customerDAO;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		//get customers from DAO
//		List<Customer> theCustomers = customerDAO.getCustomers();
		
		// Here, we're only delegating a call to customer service layer through which we're accessing a particular - 
		// DAO
		List<Customer> theCustomers = customerService.getCustomers();

		
		// ADD the customer to model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// Create customer object
		Customer theCustomer = new Customer();
		
		// Bind the object to the model Attribute !:
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		// Save the customer using our service
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String updateCustomer(@RequestParam("customerId") int theId ,
								 Model theModel) {
		
		// Get the customer from Database
		Customer theCustomer = customerService.getCustomerById(theId);
		
		// Set customer as model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		// Send over to jsp		
		return "customer-form";
	}
	
	@RequestMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		// Delete Customer !:
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/searchCustomer")
	public String showSearchForm(@RequestParam("searchName") String searchName, Model theModel) {
		
		List<Customer> theCustomers = customerService.searchCustomers(searchName); 
		
		
		theModel.addAttribute("customers",theCustomers);
		
		return "list-customers";
	}
	
}
