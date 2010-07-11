<%@page import="com.sm.entities.Teacher,com.sm.entities.TeacherManager" %>
<%
 out.print( request.getParameter("firstname"));

out.print( request.getParameter("lastname"));
Teacher teacher = new Teacher();
teacher.setFirstName(request.getParameter("firstname"));
teacher.setLastName(request.getParameter("lastname"));
TeacherManager.saveTeacher(teacher);


%>

<form>
First Name<input type = "text" name = "firstname"/>
last Name<input type = "text" name = "lastname"/>
<input type ="submit" value = "Save" />
</form>
