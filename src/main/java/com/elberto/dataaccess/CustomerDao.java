package com.elberto.dataaccess;

import java.util.List;


import com.elberto.domain.Call;
import com.elberto.domain.Customer;
import com.elberto.exceptions.RecordNotFoundException;


public interface CustomerDao 
{
	
	public String getNextAvaillableCustomerID();

	
	public Customer getById(String customerId) throws RecordNotFoundException;

	
	public List<Customer> getByName(String name);

	
	public void update(Customer customerToUpdate) throws RecordNotFoundException;
	
	
	public void delete(Customer oldCustomer) throws RecordNotFoundException;

	
	public List<Customer> getAllCustomers();
	
	public List<Customer> getAllCustomersWithDetail();

	
	
	public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException;
	
	
	public void addCall (Call newCall, String customerId) throws RecordNotFoundException;


	public void create(Customer newCustomer);
}
