using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.Odbc;
using System.Configuration;
using emed.ctls;
using MySql.Data.MySqlClient;

namespace emed
{
    public partial class _Default : System.Web.UI.Page
    {
        protected Student student = new Student("mohan", "das");

        public String getVal()
        {
            return "sdfd";
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            DataBind();
           // String constr = ConfigurationManager.ConnectionStrings.("MyConnectionString");
           // debug.Text += "page Loaded " + constr;
            //firstName.Text = student.firstName;
            //lastName.Text = student.lastName;
        }

        protected void Convert_ServerClick(object sender, EventArgs e)
        {
            student.lastName = lastName.Text;
            student.firstName = firstName.Text;
            String connectionString = "server=localhost;database=tapovandb;uid=root;pwd=root";
            MySqlConnection conn = new MySqlConnection(connectionString);
            conn.Open();
            MySqlCommand command = conn.CreateCommand();
            
            command.CommandText = "insert into Student(firstName, lastName) values ('" + student.firstName + "','" +
                student.lastName +"')";
            command.ExecuteNonQuery();
            conn.Close();

            Server.Transfer("editStudent.aspx");

        }
    }
}
