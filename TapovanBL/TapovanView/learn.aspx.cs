using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace TapovanView
{
    public partial class learn : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!Page.IsPostBack) {
              ArrayList mycountries= new ArrayList();
              mycountries.Add("Norway");
              mycountries.Add("Sweden");
              mycountries.Add("France");
              mycountries.Add("Italy");
          }
        }
    }
}
