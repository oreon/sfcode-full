using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TapovanBL.domain
{
    public class Student : Person
    {
        /*
        Grade grade;

        public Student()
        {
            grade = new Grade();
        }*/

        public virtual String DOB { get; set; }

        public virtual Grade Grade { get; set; }

    }
}