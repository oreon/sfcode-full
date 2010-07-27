package com.nas.recovery.web.action.users;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.witchcraft.seam.security.Authenticator;

@Scope(ScopeType.CONVERSATION)
@Name("myAction")
public class MyAction  implements java.io.Serializable {
	
	@In(create=true)
	Authenticator authenticator;
	
	private int count = 1;
	
	
	
	@Begin(join=true)
	public void meth(){
		
		++count;
		System.out.println("count is " + count );
		//return "success" ;
	}




	public void setCount(int count) {
		this.count = count;
	}




	public int getCount() {
		return count;
	}

	
}
