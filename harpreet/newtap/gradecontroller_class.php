<?php


 include_once 'grade_class.php';

 $controller = new gradeController();
 $controller->save();

class gradeController {

	function save() {
		$g = new grade();

		$gd = $_GET['gradename'];
		$g->gradename = $gd;
		
		$qry = "insert into grade(gradename) values ('$gd')";
		
		//print $qry;
		
		$this->connect();
		$result = mysql_query($qry);
		if(!$result)
			die( mysql_error() );
		$id = mysql_insert_id();
			
		session_start(); 
		$_SESSION['grade'] = serialize($g); 
		header( 'Location: gradeSaveSuccess.php?id='.$id ) ;
		
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