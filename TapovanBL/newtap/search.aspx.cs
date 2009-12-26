using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TapovanBL.domain;
using TapovanBL.dao;

namespace newtap
{
    public partial class WebForm2 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            
            
           // student.Gender = "";

          
            
        }

        List<Student> findStudents()
        {
            Student student = new Student();
            StudentDao stDao = new StudentDao();
            return stDao.findByExample(student);
        }
    }
}
