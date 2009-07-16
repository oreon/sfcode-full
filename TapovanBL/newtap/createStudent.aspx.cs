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
                    st = stDao.load(id);
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
             
            if (GradeDropDown.SelectedValue != null)
            {
                long id = Int32.Parse(  GradeDropDown.SelectedValue );
                Grade grade = new GradeDao().load (  id );
                st.Grade = grade;
            }

            st.id = Int32.Parse(recordId.Value);

            
            stDao.save(st);
            uploadFile(st);
            fn.Text = st.FirstName;

            Server.Transfer("studentListing.aspx", true);
        }

        protected void uploadFile(Student st)
        {
            String savePath = @"c:\temp\uploads\" + st.id + "\\" ;

            // Before attempting to perform operations
            // on the file, verify that the FileUpload 
            // control contains a file.
            if (FileUpload1.HasFile)
            {
                // Get the name of the file to upload.
                String fileName = FileUpload1.FileName;

                // Append the name of the file to upload to the path.
                savePath += fileName;


                // Call the SaveAs method to save the 
                // uploaded file to the specified path.
                // This example does not perform all
                // the necessary error checking.               
                // If a file with the same name
                // already exists in the specified path,  
                // the uploaded file overwrites it.
                FileUpload1.SaveAs(savePath);

                // Notify the user of the name of the file
                // was saved under.
                UploadStatusLabel1.Text = "Your file was saved as " + fileName;
            }
            else
            {
                // Notify the user that a file was not uploaded.
                UploadStatusLabel1.Text = "You did not specify a file to upload.";
            }

        }
   
    }
}
