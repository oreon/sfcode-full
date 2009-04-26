<?php

 include_once 'student_class.php';

 $controller = new StudentController();
 $controller->save();

class StudentController {

	function save() {
		$s = new student();

		$fn = $_GET['firstName'];
		$ln = $_GET['lastName'];
		$qry = "insert into student(firstname, lastname) values ('$fn', '$ln')";
		$this->connect();
		$result = mysql_query($qry);
		if(!$result)
			echo mysql_error();
		
	}

	function connect(){
		$dbcnx = @mysql_connect("localhost", "root", "root");
		if (!$dbcnx)
		{
			echo( "<p>connection to database server failed!</p>");
			exit();
		}

		if (! @mysql_select_db("newtap") )
		{
			echo( "<p>Image Database Not Available!</p >" );
			exit();
		}
	}

}




?>