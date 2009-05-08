<?php
 include_once 'sponsor_class.php';
$controller = new sponsorController();
 $controller->save();

class sponsorController {

	function save() {
		$s = new sponsor();

		$fn = $_REQUEST['firstname'];
		$ln = $_REQUEST['lastname'];
		$eml = $_REQUEST['email'];
		$psd = $_REQUEST['password'];
		
		$s->lastname = $ln;
		$s->firstname = $fn;
		$s->email = $eml;
			$s->password = $psd;
		
		$qry = "insert into sponsor(firstname, lastname, email, password) values ('$fn', '$ln', '$eml', '$psd')";
		
		//print $qry;
		
		$this->connect();
		$result = mysql_query($qry);
		if(!$result)
			die( mysql_error() );
		$id = mysql_insert_id();
			
		session_start(); 
		$_SESSION['sponsor'] = serialize($s); 
		header( 'Location: sponsorSaveSuccess.php?id='.$id ) ;
		
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
			echo( "<p>your imformation  saved successfully!</p >" );
			exit();
		}
	}

}





?>