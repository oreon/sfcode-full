using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TapovanBL.domain
{
    public class Student : Person
    {
        public virtual String DOB { get; set; }

    }
}