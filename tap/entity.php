<?php

abstract class Entity{
	var $id;

	function toString(){

	}

	function renderForm($action){
		print("<form action='$action'><table>");
		$class_var_entries = get_class_vars(get_class($this));
		while ($entry = each($class_var_entries)) {
			$name = $entry['key'];
			$value = $this->$name;

			if($name == "id") {
				print"<input type=hidden name='$name' value='$value' />";
				continue;
			}

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


		$rowCount = 0;

		print("<table border=\"1\" >");
		while ($row = mysql_fetch_row($result)) {
			print("<tr>");
			$colCount = 0;
			foreach($row AS $key => $value){
				$linkBegin = "";
				$linkEnd = "";

				if($key == "id") continue;
				if(++$colCount == 1){
					$linkBegin = "<a href='editStudent.php?id=".$row[0]."'>";
					$linkEnd = "</a>";
				}
				print("<td>  $linkBegin $value $linkEnd </td>");
			}
			print("</tr>");
		}
		print("</table>");
	}

	function persist(){
		$this->dbconn();
		
		if($this->id == null){
			printf("inserting record");
			mysql_query($this->getPersistQuery());
		}else{
			printf("updating record");
			mysql_query($this->getUpdateQuery());
		}
	}

	function fromPrimaryKey(){
		$this->dbconn();
		//print("running qry ". $this->getLoadQuery(). "<br> ");
		$result = mysql_query($this->getLoadQuery());
		$row = mysql_fetch_array($result);
		
		$classVars = get_class_vars(get_class($this));

		foreach($classVars AS $varName => $varValue){
			//print($varValue." ".$row[$varName]." ".$varName);
			$this->$varName = $row[$varName];
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

	function getLoadQuery(){
		return "select * from student where id = $this->id";
	}
	
	function getUpdateQuery(){
		
	}

}


?>