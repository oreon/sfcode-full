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
  public partial class createGrade : System.Web.UI.Page
    {
        public Grade gd = new Grade();
        GradeDao gdDao = new GradeDao();

        protected void Page_Load(object sender, EventArgs e)
        {
           
            if (!IsPostBack)
            {
                String strId = Request.QueryString["id"];
                if (strId != null)
                {
                    long id = Int32.Parse(strId);
                    gd = gdDao.load(id);
                }
                DataBind();
            }
        }

        protected void submit_Click(object sender, EventArgs e)
        {
            // DataBind();
            gd.GradeName = gradename.Text;
            

            //gd.id = Int32.Parse(recordId.Value);


            gdDao.save(gd);
          //  gd.Text = gd.GradeName;

            Server.Transfer("studentListing.aspx", true);
        }

        public List<Grade> getGrades()
        {
            return gdDao.loadAll();
        }

    }
}

            