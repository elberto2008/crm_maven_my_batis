package com.elberto.dataaccess;

import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elberto.domain.Call;
import com.elberto.domain.Customer;
import com.elberto.exceptions.RecordNotFoundException;
import com.elberto.persistence.CustomerSqlMapper;

@Repository
public class CustomerDaoMyBatisImpl   implements CustomerDao 
{

	@Autowired
	private CustomerSqlMapper customerSqlMapper;

	public String getNextAvaillableCustomerID() 
	{
		return customerSqlMapper.getNextAvaillableCustomerIDSqlMapper();
	}

	public Customer getById(String customerId) throws RecordNotFoundException 
	{
		return customerSqlMapper.getCustomerByIdSqlMapper(customerId);
	}

	public List<Customer> getByName(String name) 
	{
		return null;
	}

	public void update(Customer customerToUpdate) throws RecordNotFoundException 
	{
		customerSqlMapper.updateSqlMapper(customerToUpdate);
	}

	public void delete(Customer oldCustomer) throws RecordNotFoundException 
	{
		
	}

	public List<Customer> getAllCustomers() 
	{
		return customerSqlMapper.getAllCustomersSqlMapper() ;
	}

	public List<Customer> getAllCustomersWithDetail() 
	{
		return null;
	}

	public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException 
	{
		return customerSqlMapper.getCustomerByIdSqlMapper(customerId);
	}

	public void addCall(Call newCall, String customerId)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		
	}

	public void create(Customer newCustomer) 
	{
		customerSqlMapper.createSqlMapper(newCustomer);	
	}
	
	



}
