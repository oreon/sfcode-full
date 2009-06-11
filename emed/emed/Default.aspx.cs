using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;


namespace emed
{
    public partial class _Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Convert_ServerClick(object sender, EventArgs e)
        {
            decimal usamt = decimal.Parse(US.Text);

            if (Result.InnerText.Length != 0)
                Result.InnerText = "";

            Result.InnerText += " " + usamt * 0.85M;

        }
    }
}
