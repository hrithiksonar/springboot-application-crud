package com.customer.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.model.Customer;
import com.customer.model.TrackCount;
import com.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository repository;

	@Override
	public void insertCustomer(Customer customer) throws ClassNotFoundException, IOException {
		if (customer != null) {
			System.out.println(customer);
			repository.save(customer);
		}
		System.out.println(customer);
	}

	@Override
	public List<Customer> loadCustomer() {
		return repository.findAll();
	}

}
