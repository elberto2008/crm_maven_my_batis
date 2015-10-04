package com.elberto.restcontrollers;


import java.util.ArrayList;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elberto.client.erros.ClientErrorInformation;
import com.elberto.domain.Customer;
import com.elberto.representation.CallCollection;
import com.elberto.representation.CustomerCollectionRepresentation;
import com.elberto.services.customers.CustomerManagementService;
import com.elberto.services.customers.CustomerNotFoundException;


@RestController
public class CustomerRestController 
{
	@Autowired
	private CustomerManagementService customerService;
	
/*	@Autowired
	private CallHandlingService callHandlingService;
	
	@Autowired
	private DiaryManagementService diaryService;*/
	
	
	
	@RequestMapping(value="/customer/{id}",method=RequestMethod.GET)
	public Customer findCustomerById(@PathVariable String id, @RequestParam(required=false, defaultValue="true") boolean fullDetail ) throws CustomerNotFoundException
	{	
	
		if(fullDetail)
		{
		  return  customerService.getFullCustomerDetail(id);
		}
		else
		{
			Customer customer = customerService.findCustomerById(id);
			customer.setCalls(null);
			return  customer;

		}
	
	}

    @RequestMapping(value="/customers",method=RequestMethod.GET)
	public CustomerCollectionRepresentation getAllCustomers(@RequestParam(required=true) boolean fullDetails) throws CustomerNotFoundException
	{
  
    	
    	
    	List<Customer> allCustomers = new ArrayList<Customer>();
    	
    	if(!fullDetails)
    	{
    		allCustomers = customerService.getAllCustomers();
    		
    		for(Customer next : allCustomers)
    		{
    			next.setCalls(null);

    		}
 
    	}
    	else
    	{
    		allCustomers = customerService.getAllCustomersWithDetail();

    	}
    		
		return new CustomerCollectionRepresentation(allCustomers);
   }
	
    
	@RequestMapping(value="/customers", method=RequestMethod.POST )
	public ResponseEntity<Customer> createNewCustomer(@RequestBody @Valid Customer newCustomer) throws CustomerNotFoundException 
	{
		Customer createdCusmer = customerService.newCustomer(newCustomer);
		
		createdCusmer.add(linkTo(methodOn(CustomerRestController.class).findCustomerById(createdCusmer.getCustomerId(),false)).withSelfRel());
		return new ResponseEntity<Customer>(createdCusmer, HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(value="/customer/{id}", method=RequestMethod.DELETE )
	public void deleteCustomerById(@PathVariable String id)
	{
		  try 
		  {
		    Customer foundCustomer = customerService.findCustomerById(id);
			customerService.deleteCustomer(foundCustomer);
		  } 
		  catch (CustomerNotFoundException e) 
		  {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/customer/{id}", method=RequestMethod.PUT )
	public ResponseEntity<Customer> updateCustomerById(@RequestBody Customer customerToUpdate)
	{

		
		System.out.println("\n\n\n\n\n\n In here");
		System.out.println("\n\n\n\n\n\n In here");
		System.out.println("\n\n\n\n\n\n In here");

			try 
			{
				customerService.updateCustomer(customerToUpdate);
				customerToUpdate.add(linkTo(methodOn(CustomerRestController.class).findCustomerById(customerToUpdate.getCustomerId(),false)).withSelfRel());

			} 
			catch (CustomerNotFoundException e) 
			{
				e.printStackTrace();
			}
			
			return new ResponseEntity<Customer>(customerToUpdate,HttpStatus.CREATED) ;
	
	}
	
	
	@RequestMapping(value="/customer/{id}", method=RequestMethod.PATCH )
	public ResponseEntity<Customer> partialUpdateCustomerById(@RequestBody Customer customerToUpdate, @PathVariable String id)
	{

			try 
			{
				Customer customer = customerService.findCustomerById(id);
				customerToUpdate.setCustomerId(id);
				
				String companyName = customerToUpdate.getCompanyName();
				String email = customerToUpdate.getEmail();
				String notes = customerToUpdate.getNotes();
				String phone = customerToUpdate.getTelephone();
				
				if(companyName==null || companyName == "")
				  customerToUpdate.setCompanyName(customer.getCompanyName());
				if(email==null || email == "")
				  customerToUpdate.setEmail(customer.getEmail());
				if(notes==null || notes == "")
				  customerToUpdate.setNotes(customer.getNotes());
				if(phone==null || phone == "")
				  customerToUpdate.setTelephone(customer.getTelephone());

				customerToUpdate.add(linkTo(methodOn(CustomerRestController.class).findCustomerById(id,false)).withSelfRel());
				customerService.updateCustomer(customerToUpdate);
			} 
			catch (CustomerNotFoundException e) 
			{
				e.printStackTrace();
			}
			
			return new ResponseEntity<Customer>(customerToUpdate,HttpStatus.CREATED);
	
	}
	
	/*@RequestMapping(value="/customer/{id}/calls", method=RequestMethod.POST)
	public ResponseEntity<CallActionRepresention> recordCallBussinessProcess(@RequestBody @Valid CallActionRepresention incomingCall, @PathVariable String id) throws CustomerNotFoundException
	{
		
		callHandlingService.recordCall(id, incomingCall.getCall(), incomingCall.getActions());
		incomingCall.add(linkTo(methodOn(CustomerRestController.class).getAllCallsForCustomer(id)).withRel("call"));
		
		
		Collection<Action> allActions = incomingCall.getActions();
		
		for(Action next : allActions)
		{
			incomingCall.add(linkTo(methodOn(CustomerRestController.class).getAllIncompleteActionsForUser(next.getOwningUser())).withRel("action"));

		}
		
		
		return new ResponseEntity<CallActionRepresention>(incomingCall, HttpStatus.CREATED);
		
		
	}*/
	
	
	@RequestMapping(value="/customer/{id}/calls", method=RequestMethod.GET)
	public CallCollection getAllCallsForCustomer(@PathVariable String id) throws CustomerNotFoundException
	{
		Customer customer =  customerService.getFullCustomerDetail(id);
		
		return new CallCollection(customer.getCalls());
		
	}
	
/*	@RequestMapping(value="/user/{userid}/actions", method=RequestMethod.GET)
	public ActionCollection getAllIncompleteActionsForUser(@PathVariable String userid) 
	{	
		return new ActionCollection(diaryService.getAllIncompleteActions(userid));
		
	}
	*/
	
	
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ClientErrorInformation> rulesForCustomerNotFound(HttpServletRequest rep, Exception e)
	{
		ClientErrorInformation error = new ClientErrorInformation(e.toString(), rep.getRequestURI());
		return new ResponseEntity<ClientErrorInformation>(error, HttpStatus.NOT_FOUND);
	}
	
	
/*	@RequestMapping(value="/customers")
	public CustomerCollectionRepresentation getAllCustomers(@RequestParam boolean fullDetails, @RequestParam int first, @RequestParam int last)
	{
    	System.out.println("\n\n First is "+first);
    	System.out.println("\n\n Last is "+last);
    	
    	List<Customer> allCustomers = new ArrayList<Customer>();
    	
    	if(!fullDetails)
    	{
    		allCustomers = customerService.getAllCustomers();
    		
    		for(Customer next : allCustomers)
    		{
    			next.setCalls(null);
    		}
    	}
    	else
    	{
    		allCustomers = customerService.getAllCustomersWithDetail();
    	}
    	

		
			
		return new CustomerCollectionRepresentation(allCustomers);
   }*/
	
    

	
}
