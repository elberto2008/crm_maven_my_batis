package com.elberto.representation;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

import com.elberto.domain.Call;
import com.elberto.domain.Customer;


@XmlRootElement(name="customers")
public class CustomerCollectionRepresentation extends ResourceSupport
{
	List<Customer> customers;
	List<Call> calls;

	@XmlElement(name="call")
	public List<Call> getCalls() 
	{
		return calls;
	}

	public void setCalls(List<Call> calls) 
	{
		this.calls = calls;
	}

	//Must be here for JAX WS to work
	public CustomerCollectionRepresentation() 
	{
	}

	public CustomerCollectionRepresentation(List<Customer> customers) 
	{
		super();
		this.customers = customers;
	}

    @XmlElement(name="customer")
	public List<Customer> getCustomers() 
	{
		return customers;
	}

	public void setCustomers(List<Customer> customers) 
	{
		this.customers = customers;
	}
	
	
	

}
