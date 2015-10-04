package com.elberto.representation;

import java.util.Collection;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

import com.elberto.domain.Action;
import com.elberto.domain.Call;

@XmlRootElement
public class CallActionRepresention  extends ResourceSupport
{
	@Valid
	private Call call;
	private Collection<Action> actions;
	
	
	
	public CallActionRepresention()
	{
		
	}

	public Call getCall() 
	{
		return call;
	}

	public void setCall(Call call) 
	{
		this.call = call;
	}

	public Collection<Action> getActions() 
	{
		return actions;
	}

	public void setActions(Collection<Action> actions) 
	{
		this.actions = actions;
	}
	
	

}
