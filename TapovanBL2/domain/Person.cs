using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TapovanBL.domain
{
    public abstract class Person : BusinessEntity
    {
        public virtual String FirstName { get; set; }
        public virtual String LastName { get; set; }
    }
}
