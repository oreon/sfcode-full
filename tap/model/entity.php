<?php

abstract class Entity{
	var $id;

	function __construct(){

	}

	function createNew(){
		$obj = clone($this);
		$obj->clearAll();
		return $obj;
	}

	function clearAll(){
		$classVars = get_class_vars(get_class($this));
		foreach($classVars AS $varName => $varValue){
			$this->varName = null;
		}
	}

	function toString(){
		$classVars = get_class_vars(get_class($this));
		foreach($classVars AS $varName => $varValue){
			print( $varName."->".$this->varName.":" );
		}
	}

	function renderSingleObjectAsTableRow(){
		
	}
	
	//will return an assoc array of fields and names 
	function getAssocArray(){
		
	}
	
	function renderSingleObject($singleRow = false){
		print("<form><table>");
		$this->renderFieldNameValue($this, $singleRow);
		print("<tr><td><input type='submit' value='Edit' /></td></tr>");
		print("</table>");
		print("</form>");

	}
	
	function renderFieldNameValue($obj, $singleRow){
		$class_var_entries = get_class_vars(get_class($this));
			
		while ($entry = each($class_var_entries)) {
			$name = $entry['key'];
			$value = $obj->$name;
			
			if($name == "id") {
				print "<input type=hidden name='$name' value='$value' />";
				continue;
			}

			if ($this->isEntity($value) ){				
				$value = $value->getDisplayName();
			}

			if(!$singleRow){
				print("<tr>");
				print "<td>".$name. " </td> ";
			}
			print("<td>$value </td>");
			if(!$singleRow)
				print("</tr>");
		}
	}
	
	function listAsTab(){
		$this->dbconn();
		$query = $this->getLoadAllQuery();
		$result = mysql_query($query);

		$rowCount = 0;

		print("<table border=\"1\" >");
		
		while ($row = mysql_fetch_array($result)) {
			print("<tr>");
			$obj = $this->createNew();
			$obj = $this->loadObjectFromDatabaseRow($obj, $row);
			$id = $obj->id;
			print("<td> <input type='checkbox' value='$id' ></td>");
			$this->renderFieldNameValue($obj, true);
			print("<td> <a href='editStudent.php?id=$id' > Edit </a></td>");
			print("<td> <a href='viewStudent.php?id=$id' > View </a></td>");
			print ("</tr>");
		}
		
		print("</table>");
	}

	function renderForm($action){
		print("<form action='$action'><table>");
		$class_var_entries = get_class_vars(get_class($this));
		
		while ($entry = each($class_var_entries)) {
			$name = $entry['key'];
			$value = $this->$name;

			if ($this->isEntity($value) ){
				$arr = $value->loadObjectsFromQuery($value->getLoadAllQuery());
				print("<tr><td>".$name. " </td> ");
				$select = new HtmlSelect($arr, $name."___id", $value->id);
				print("<td> ".$select->render() ."</td></tr>");
				continue;
			}

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
	
	
	
	




	function listAsTable(){
		$this->dbconn();
		$query = $this->getLoadAllQuery();
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

				$renderValue = $value;

				if($this->isEntity($value)){
					$renderValue = $value->getDisplayName();
				}

				if(++$colCount == 1){ //first column should be made a link
					$linkBegin = "<a href='editStudent.php?id=".$row[0]."'>";
					$linkEnd = "</a>";
					$renderValue =  $linkBegin.$value.$linkEnd ;
				}
				print("<td>  $renderValue  </td>");


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
			printf("updating record ".$this->getUpdateQuery());
			mysql_query($this->getUpdateQuery());
		}
	}

	/*
	 *
	 */
	function fromPrimaryKey(){
		$this->dbconn();
		print("running qry ". $this->getLoadQuery(). "<br> ");
		$result = mysql_query($this->getLoadQuery());
		$row = mysql_fetch_array($result);
		$this->loadObjectFromDatabaseRow($this, $row);
	}
	
	
	/*
	 *
	 */
	function loadObjectFromDatabaseRow($obj, $row){
		$classVars = get_class_vars(get_class($obj));
		
		foreach($classVars AS $varName => $varValue){

			foreach($row AS $key => $value){
				
				if($key == $varName){
					$obj->$varName = $row[$varName];
					$temp =  $row[$varName];
					continue;
				}
				if(strpos($key, '___') !== false){ //load associations
					list($assocName, $assocMember) = split('___', $key);
					//if(!isset($this->$assocName))
						$obj->$assocName = new Grade();
					$obj->$assocName->$assocMember = $value;
				}
					
			}
		}
		
		return $obj;
	}

	function loadObjectsFromQuery($qry){
		$this->dbconn();
		print("running qry ". $this->getLoadAllQuery(). "<br> ");
		$result = mysql_query($qry);

		$arr;

		while( $row = mysql_fetch_array($result)){
			$obj = $this->createNew();

			$classVars = get_class_vars(get_class($this));

			foreach($classVars AS $varName => $varValue){
				//print($varValue." ".$row[$varName]." ".$varName);
				$obj->$varName = $row[$varName];
			}

			$arr[] = $obj;
		}

		return $arr;
	}

	function fromRequest(){
		$classVars = get_class_vars(get_class($this));
		foreach($classVars AS $varName => $varValue){
			foreach($_GET AS $key => $value) {
				if($key == $varName){
					$this->$varName = $value;
					continue;
				}
				if(strpos($key, '___') !== false){
					list($assocName, $assocMember) = split('___', $key);
					if(!isset($this->$assocName))
					$this->$assocName = new Grade();
						
					//print("<br>  $key $value $assocMember $assocName");
					$this->$assocName->$assocMember = $value;
				}
			}
		}
	}

	function isEntity($value){
		$res =  (is_object($value) && is_subclass_of($value, 'Entity') );
		return $res;
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

	}

	function getLoadAllQuery(){
		return "select * from ".get_class($this);
	}

	function getUpdateQuery(){

	}

	function getDisplayName(){

	}



}


?>