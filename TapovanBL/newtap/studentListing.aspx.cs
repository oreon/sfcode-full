using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TapovanBL.dao;
using TapovanBL.domain;

namespace newtap
{
    public partial class studentListing : System.Web.UI.Page
    {
        StudentDao dao = new StudentDao();
        bool filter = false;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                filter = false;
            }
            else
                filter = true;

        }

        protected void DataList1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        Student st = new Student();

        public List<Student> getStudents()
        {
            if (filter)
            {
                st.FirstName = "";
                st.LastName = "kaur";
                //st.LastName = lastName.Text;
                //st.DOB = dob.Text;
                return dao.findByExample(st);
            }
            else
                return dao.loadAll();
            /*
            ArrayList list = new ArrayList();
            Student s1 = new Student();
            s1.FirstName = "jatin";
            s1.LastName = "kumar";
            list.Add(s1);

            Student s2 = new Student();
            s2.FirstName = "Mike";
            s2.LastName = "Richards";
            list.Add(s2);

            return list;*/
        }

        protected void BtnFilter_Click(object sender, EventArgs e)
        {
            filter = true;
            ObjectDataSource1.Update();
        }
    }
}
