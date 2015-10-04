package com.elberto.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;



@Entity
@XmlRootElement
public class Customer extends ResourceSupport implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	
	@Id
	private String customerId;
	
    @NotEmpty	
	private String companyName;
	
    @NotEmpty
	private String email;
	
    @NotEmpty
	private String telephone;
	
    @NotEmpty
	private String notes;
	
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Call> calls;	
	
	
	public Customer(String customerId, String companyName, String email,
			        String telephone, String notes)
	{
		this(customerId, companyName, notes);
		this.email = email;
		this.telephone = telephone;
	}
	
	
	public Customer(String customerId, String companyName, String notes)
	{
		this.customerId = customerId;
		this.companyName = companyName;
		this.notes = notes;
		this.calls = new ArrayList<Call>();
	}
	
	
	public void addCall(Call callDetails) 
	{
		this.calls.add(callDetails);		
	}
	
	


	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", companyName="
				+ companyName + ", email=" + email + ", telephone=" + telephone
				+ ", notes=" + notes + "]";
	}


	public String getCustomerId() 
	{
		return this.customerId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getEmail() {
		return email;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getNotes() {
		return notes;
	}

	@XmlElement(name="call")
	public List<Call> getCalls() {
		return calls;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setCalls(List<Call> calls) {
		this.calls = calls;
	}

	// Questo qua e per JPA 
	public Customer() {}
}
