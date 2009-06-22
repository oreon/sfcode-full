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
    public partial class register : System.Web.UI.Page
    {
        public Sponsor sp = new Sponsor();
        SponsorDao spDao = new SponsorDao();

        protected void Page_Load(object sender, EventArgs e)
        {
           
            if (!IsPostBack)
            {
                sp.FirstName = "Rohit";
                DataBind();
            }
            
        }

        protected void b1_Click(object sender, EventArgs e)
        {
           // DataBind();
            sp.FirstName = firstname.Text;
            sp.LastName = lastname.Text;
            sp.Email = email.Text;
            sp.Password = password.Text;
            sp.UserName = username.Text;
            spDao.save(sp);
            
            fn.Text = sp.FirstName;
        }
    }
}
