package com.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.model.Customer;
import com.customer.model.Response;
import com.customer.service.CustomerService;
import com.customer.utility.Constant;

@RestController
@RequestMapping("api")
public class CustomerController {

	@Autowired
	CustomerService service;

	@PostMapping("/customer/v1")
	public Response registerCustomer(@RequestBody Customer customer) {
		try {
			service.insertCustomer(customer);
			return new Response(Constant.SUCCESS, Constant.SUCCESS_MSG);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Response(Constant.FAILURE, Constant.FAILURE_MSG);
		}
	}

	@GetMapping("/customer/v1")
	public ResponseEntity<?> getCustomers() {
		try {
			return new ResponseEntity<>(service.loadCustomer(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/customer/v1/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(service.getCustomerById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/customer/v1/{id}")
	public ResponseEntity<?> updateProfile(@PathVariable("id") int id, @RequestBody Customer customer) {
		try {
			Customer existingCustomer = service.getCustomerById(id);
			existingCustomer.setName(customer.getName());
			existingCustomer.setEmail(customer.getEmail());
			existingCustomer.setAge(customer.getAge());
			service.insertCustomer(existingCustomer);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/customer/v1/{id}")
	public Response deleteProfile(@PathVariable("id") int id) {
		Response response = new Response();
		try {
			service.deleteById(id);
			response.setMessage(String.format("Customer id %d deleted successfully!!!", id));
			response.setStatusCode(200);
			return response;
		} catch (Exception e) {
			response.setMessage("Customer not found or may be some issue occured");
			response.setStatusCode(500);
			return response;
		}
	}

}
