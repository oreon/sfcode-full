using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using FirstHiber.domain;

namespace emed
{
    public partial class edit : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Drug drug = new Drug();
            drug.Name = "Epinephrine";
            DrugDao dao = new DrugDao();
            dao.save(drug);
            

        }
    }
}
