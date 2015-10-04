package com.elberto.services.customers;

import java.util.List;
import com.elberto.domain.Call;
import com.elberto.domain.Customer;


public interface CustomerManagementService 
{
	
	public Customer newCustomer(Customer newCustomer);
	
	
	public void updateCustomer(Customer changedCustomer) throws CustomerNotFoundException;
	
	
	public void deleteCustomer(Customer oldCustomer) throws CustomerNotFoundException;
	
	
	public Customer findCustomerById(String customerId) throws CustomerNotFoundException;

	
	public List<Customer> findCustomersByName (String name);

	
	public List<Customer> getAllCustomers();
	
	public List<Customer>  getAllCustomersWithDetail(); 

	
	public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException;
	
	
	
	public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException;
}
