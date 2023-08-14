package com.customer.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository repository;

	@Override
	public void insertCustomer(Customer customer) throws ClassNotFoundException, IOException {
		if (customer != null) {
			repository.save(customer);
		}
	}

	@Override
	public List<Customer> loadCustomer() {
		return repository.findAll();
	}

	@Override
	public Customer getCustomerById(int id) {
		return repository.findById(id).get();
	}

	@Override
	public void deleteById(int id) {
		repository.deleteById(id);
	}

}
