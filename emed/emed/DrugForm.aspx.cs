using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using FirstHiber.domain;

namespace emed
{
    public partial class DrugForm : System.Web.UI.Page
    {
        Drug drug = new Drug();

        protected void Page_Load(object sender, EventArgs e)
        {
            drug.Name = "Metmorfin";

        }
    }
}
