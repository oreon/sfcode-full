package com.wcmda.validator.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.mapping.Property;
import org.hibernate.validator.PropertyConstraint;
import org.hibernate.validator.Validator;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;

import com.wcmda.model.User;
import com.wcmda.validator.Unique;
import com.wcmda.validator.UniqueUser;

public class UniqueUserName implements Validator<UniqueUser>, PropertyConstraint {

	private Unique type; 
	private String uniquenessCheckValue;
	
	@In
	  EntityManager entityManager;
	
	public void initialize(UniqueUser parameters) 
	{
	
		type = parameters.type();
		
	}

	public boolean isValid(Object value ) {
		
		if( value == null ) return false;
		
		if( type == Unique.FIRSTNAME )
		{
			uniquenessCheckValue=(String)value;
			
			Query q = entityManager.createQuery("SELECT u FROM User u " +
    	    		"WHERE u.username = '?'").setParameter(1, uniquenessCheckValue);
			
			try {
	        
				User  user = (User) q.getSingleResult();
	            return true;
	          } catch (NoResultException nre) {
	            return false;
	          }
		}
			
		return false;
	}

	public void apply(Property arg0) {
		
		
	}
	 @Factory(value="pattern", scope=ScopeType.EVENT)
	   public String getSearchPattern()
	   {
	      return uniquenessCheckValue==null ?
	            "%" : '%' + uniquenessCheckValue.toLowerCase().replace('*', '%') + '%';
	   }	
	
}
