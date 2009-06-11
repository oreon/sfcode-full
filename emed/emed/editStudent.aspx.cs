using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MySql.Data.MySqlClient;

namespace emed
{
    public partial class EditStudent : System.Web.UI.Page
    { 
        protected void Page_Load(object sender, EventArgs e)
        {
            List<string> fruits = new List<string>();
            fruits.Add("apple");
            fruits.Add("banana");
            fruits.Add("orange");
            /*
            String connectionString = "server=localhost;database=tapovandb;uid=root;pwd=root";
            MySqlConnection conn = new MySqlConnection(connectionString);
            conn.Open();
            MySqlCommand command = conn.CreateCommand();
            command.CommandText = "select firstname,lastname from student";
            MySqlDataReader reader = command.ExecuteReader();
            Gridview1.AutoGenerateColumns = false; */

            SqlDataSource1.ConnectionString = 
            SqlDataSource1.SelectCommand = "Select * from students";
          
            DataBind();
            
            //DataList1.DataBind(SqlDataSource1);
           // DataList1.DataSource = SqlDataSource1;
           

        }

        protected void SqlDataSource1_Selecting(object sender, SqlDataSourceSelectingEventArgs e)
        {

        }
    }
}
