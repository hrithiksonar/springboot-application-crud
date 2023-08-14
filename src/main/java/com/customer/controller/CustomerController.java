package com.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
//
//	@GetMapping("/customer/v1/{id}")
//	public Customer getCustomerById(@PathVariable("id") int id) {
//
//	}
//
//	@PutMapping("/customer/v1")
//	public Customer updateProfile(@RequestBody Customer customer) {
//
//	}
//
//	@DeleteMapping("/customer/v1/{id}")
//	public Response deleteProfile(@PathVariable("id") int id) {
//	}

}
