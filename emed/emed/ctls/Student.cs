using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace emed.ctls
{
    public class Student
    {
        public Student(String firstName, String lastName)
        {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String firstName;
        public String lastName;
    }
}
