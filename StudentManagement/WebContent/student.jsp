
<%
	out.println("You entered values: "
			+ request.getParameter("firstName"));

	Student stu = new Student();

	if (request.getParameter("yob") != null) {
		Integer yob = Integer.parseInt(request.getParameter("yob"));

		stu.setYob(yob);
		stu.getAge();
		out.println(stu.getAge());
	}
%>


<%@page import="com.sm.entities.Student"%><form action="">First
Name<input type="text" name="firstName" /> Last Name<input type="text"
	name="lastName" /> YOb<input type="text" name="yob" /> <input
	type="submit" /></form>