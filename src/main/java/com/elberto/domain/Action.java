package com.elberto.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Action implements Serializable
{
	

	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int actionId;
	
	
	@NotEmpty
	private String details;
	
	@NotNull
	private Calendar requiredBy;
	
	@NotEmpty
	private String owningUser;
	
	
	private boolean complete;
	
	
	public Action(String details, Calendar requiredBy, String owningUser)
	{
		this.details = details;
		this.requiredBy = requiredBy;
		this.owningUser = owningUser;
		this.complete = false;
	}
	
	public Action(String actionId, String details, Calendar requiredBy, String owningUser, boolean complete)
	{
		this.details = details;
		this.requiredBy = requiredBy;
		this.owningUser = owningUser;
		this.complete = complete;
		this.actionId = new Integer(actionId);
	}
	
	
	public boolean isOverdue()
	{
		Calendar dateNow = new java.util.GregorianCalendar();
		
		return dateNow.after(this.requiredBy);
	}
	
	
	public String toString()
	{
		return "Action for " + this.owningUser + ": " + this.details + ", required by " + this.requiredBy.getTime();
	}

	
	public void completeAction()
	{
		this.complete = true;
	}
	
	
	public boolean isComplete() 
	{
		return this.complete;
	}

	public String getOwningUser() {
		return owningUser;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Calendar getRequiredBy() {
		return requiredBy;
	}

	public void setRequiredBy(Calendar requiredBy) {
		this.requiredBy = requiredBy;
	}

	public void setOwningUser(String owningUser) {
		this.owningUser = owningUser;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	
	// needed for JPA - 
	public Action() {}
}
