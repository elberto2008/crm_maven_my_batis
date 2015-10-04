package com.elberto.persistence;

import java.util.List;


import com.elberto.domain.Call;
import com.elberto.domain.Customer;

public interface CustomerSqlMapper 
{

	public Customer getCustomerByIdSqlMapper (String id);
	
	public List<Customer> getAllCustomersSqlMapper();
		
	public void deleteCustomerByIdSqlMapper(String id); 
	
	public void createSqlMapper(Customer newCustomer);
	
	public String getNextAvaillableCustomerIDSqlMapper();

	public Customer getByIdSqlMapper(String customerId); 

	public List<Customer> getByNameSqlMapper(String name);

	public void updateSqlMapper(Customer customerToUpdate);

	public void deleteSqlMapper(Customer oldCustomer);
	
	public List<Customer> getAllCustomersWithDetailSqlMapper();

	public Customer getFullCustomerDetailSqlMapper(String customerId);

	public void addCallSqlMapper(Call newCall, String customerId);
	
}
