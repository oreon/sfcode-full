
	
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FirstWebProject.wcbase;

namespace org.cerebrum.domain.drug{

	public class Drug : BusinessEntity  {
	
	private  string name ;

	private  string dosage ;

	private  String form ;

	private  String activeIngred ;

	
	
	public virtual void setName(String name){
		this.name = name;
	}
	
	public virtual String getName( ){
		return name;
	}
	

	
	public virtual void setDosage(String dosage){
		this.dosage = dosage;
	}
	
	public virtual String getDosage( ){
		return dosage;
	}
	

	
	public virtual void setForm(String form){
		this.form = form;
	}
	
	public virtual String getForm( ){
		return form;
	}
	

	
	public virtual void setActiveIngred(String activeIngred){
		this.activeIngred = activeIngred;
	}
	
	public virtual String getActiveIngred( ){
		return activeIngred;
	}
	

	}
}
	