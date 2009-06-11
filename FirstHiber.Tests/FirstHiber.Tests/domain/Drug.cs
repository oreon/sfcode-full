
	
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace FirstHiber.domain{

	public class Drug : BusinessEntity  {

    private String name;
	
	public virtual  String Name {
        get {
            return name;
        }
        set{
            name = value;
        }
    }

	public virtual String dosage {get; set; }
        
	public virtual String form {get; set; }

	public virtual  String activeIngred {get; set; }

	
	

	}
}
	