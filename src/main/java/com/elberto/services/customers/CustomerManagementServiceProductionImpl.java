package com.elberto.services.customers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elberto.dataaccess.CustomerDao;
import com.elberto.domain.Call;
import com.elberto.domain.Customer;
import com.elberto.exceptions.RecordNotFoundException;

@Transactional
@Service
public class CustomerManagementServiceProductionImpl implements CustomerManagementService 
{
	@Autowired
	private CustomerDao dao;
	

	
	public Customer newCustomer1(Customer newCustomer) 
	{
		if(newCustomer.getCustomerId() == null)
		{
			String newId = dao.getNextAvaillableCustomerID();
			
			System.out.println("\n\n\n newId before "+newId);

			
			newCustomer.setCustomerId(newId);
		}
		dao.create(newCustomer);
		
		System.out.println("\n\n\n newId after ");

		
		return newCustomer;
	}

	public void updateCustomer(Customer changedCustomer) throws CustomerNotFoundException
	{
		try
		{
			dao.update(changedCustomer);
		}
		catch (RecordNotFoundException e)
		{
			throw new CustomerNotFoundException();
		}
	}

	public void deleteCustomer(Customer oldCustomer) throws CustomerNotFoundException 
	{
		try
		{
			dao.delete(oldCustomer);
		}
		catch (RecordNotFoundException e)
		{
			throw new CustomerNotFoundException();
		}
	}

	public Customer findCustomerById(String customerId) throws CustomerNotFoundException 
	{
		try
		{
			return dao.getById(customerId);
		}
		catch (RecordNotFoundException e)
		{
			throw new CustomerNotFoundException();
		}
	}

	public List<Customer> findCustomersByName(String name) 
	{
		return dao.getByName(name);
	}

	public List<Customer> getAllCustomers() 
	{
		return dao.getAllCustomers();
	}

	public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException 
	{
		try
		{
			return dao.getFullCustomerDetail(customerId);			
		}
		catch (RecordNotFoundException e)
		{
			throw new CustomerNotFoundException();
		}
	}
	

	public void recordCall(String customerId, Call callDetails)throws CustomerNotFoundException 
	{
		try
		{
			dao.addCall(callDetails, customerId);			
		}
		catch (RecordNotFoundException e)
		{
			throw new CustomerNotFoundException();
		}
	}

	public List<Customer> getAllCustomersWithDetail() {
		return dao.getAllCustomersWithDetail();
	}

	public Customer newCustomer(Customer newCustomer) {
		// TODO Auto-generated method stub
		return null;
	}

}
