package com.customer.service;

import java.io.IOException;
import java.util.List;

import com.customer.model.Customer;

public interface CustomerService {

	public void insertCustomer(Customer customer) throws ClassNotFoundException, IOException;

	public List<Customer> loadCustomer();

	public Customer getCustomerById(int id);

	public void deleteById(int id);

}
