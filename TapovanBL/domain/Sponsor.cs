using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TapovanBL.domain
{
    public class Sponsor : Person
    {
        public virtual String Email { get; set; }
        public virtual String Password { get; set; }
        public virtual String Country { get; set; }
        public virtual String UserName { get; set; }
    }
}
