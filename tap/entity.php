<?php

class Entity{
	var $id;

	function toString(){

	}

	function renderForm($action){
		print("<form action='$action'><table>");
		$class_var_entries = get_class_vars(get_class($this));
		while ($entry = each($class_var_entries)) {
			$name = $entry['key'];
			$value = $this->$name;

			if($name == "id") continue;

			print("<tr><td>".$name. " </td> ");
			print("<td><input type=text name='$name' value='$value' /></tr>");
		}
		//print("<input type='text'' name='$name' value='$value' />");
		print("<tr><td><input type='submit' value='Submit' /></td></tr>");
		print("</table>");
		print("</form>");
	}


	function fromRequest(){
		$classVars = get_class_vars(get_class($this));
		foreach($classVars AS $varName => $varValue){
			foreach($_GET AS $key => $value) {
				if($key == $varName){
					$this->$varName = $value;
					break;
				}
			}
		}
	}

	function listAsTable(){
		$this->dbconn();
		$query = ("Select * from student");
		$result = mysql_query($query);
		
		print("<table>");
		while ($name_row = mysql_fetch_row($result)) {
			print("<tr>");
			foreach($name_row AS $key => $value)
				print("<td> $value </td>");
			print("</tr>");
		}
		print("</table>");
	}

	function persist(){
		$this->dbconn();
		mysql_query($this->getPersistQuery());

		if($id == null){
			printf("inserting record");
		}else{
			printf("updating record");
		}
	}

	function dbconn() {
		$dbUser = "root";
		$dbPass = "root";
		$dbName = "tapovandb";
		$dbHost = "localhost";
		if (!($link=mysql_connect($dbHost, $dbUser, $dbPass))) {
			//error_log(mysql_error(), 3, “/tmp/phplog.err”);
		}
		if (!mysql_select_db($dbName, $link)) {
			//error_log(mysql_error(), 3, “/tmp/phplog.err”);
		}
	}

	function getPersistQuery(){

	}

}


?>