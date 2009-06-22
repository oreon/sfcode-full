using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TapovanBL.dao;
using TapovanBL.domain;

namespace newtap
{
    public partial class createStudent : System.Web.UI.Page
    {
        public Student st = new Student();
        StudentDao stDao = new StudentDao();

        protected void Page_Load(object sender, EventArgs e)
        {
           
            if (!IsPostBack)
            {
                String strId = Request.QueryString["id"];
                if (strId != null)
                {
                    long id = Int32.Parse(strId);
                    st = stDao.load(id, st);
                }
                DataBind();
            }
            
        }

        protected void submit_Click(object sender, EventArgs e)
        {
            // DataBind();
            st.FirstName = firstname.Text;
            st.LastName = lastname.Text;
            st.DOB = dob.Text;

            st.id = Int32.Parse(recordId.Value);
              

            stDao.save(st);
            fn.Text = st.FirstName;

            Server.Transfer("studentListing.aspx", true);
        }
    }
}
