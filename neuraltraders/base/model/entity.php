<?php


/**
 * Base class for classes that are persistable 
 */
abstract class Entity{
	var $id;

	function getId(){
		return $id;
	}

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

	function dump($obj){
		print_r($obj);
		print("<br>");
	}

	function dumpMe(){
		print_r($this);
		print("<br>");
	}

	function renderFieldNameValue($obj, $singleRow){
		$class_var_entries = get_class_vars(get_class($this));
			
		//print("<hr/>");
		while ($entry = each($class_var_entries)) {
			$name = $entry['key'];
			$value = $obj->$name;
				
			$renderValue = $value;
			if($name == "id") {
				print "<input type=hidden name='$name' value='$value' />";
				continue;
			}

			if ( $this->isEntity($value)  ){
				//$this->dump($value);
				$valId = $value->id;
				$dispName = $value->name;
				//print($valId." :vid: ".$dispName );
				$renderValue = "<a href=\"viewGrade.php?id=$valId\">$dispName</a>";
				echo("<td>  $renderValue</td>");
				continue;
			}

			if(!$singleRow){
				print("<tr>");
				print "<td>".$name. " </td> ";
			}
			print("<td>$renderValue</td>");
			if(!$singleRow)
			print("</tr>");
		}
	}

	function listAsTable($editUrl = null){

		if(!isset($editUrl)){
			$editUrl = 'edit'.get_class($this);
		}
			
		$arr = $this->loadObjectsFromQuery($this->getLoadAllQuery());

		$rowCount = 0;

		print("<table width=\"90%\" border=\"1\" >");

		foreach ($arr as $obj) {
			print("<tr>");
			$id = $obj->id;
			print("<td> <input type='checkbox' value='$id' ></td>");
			$this->renderFieldNameValue($obj, true);
			//print("<td> <a href='controller/teacherController.php?action=load&id=$id&forward=$editUrl.php' > Edit </a></td>");
			print("<td><a href='editStudent.php?action=load&id=$id'>Edit </td>");
			print("<td> <a href='viewStudent.php?id=$id' > View </a></td>");
			print ("</tr>");
		}

		print("</table>");
	}

	function renderForm($action){
		print("<form action='$action'><table>");
		print"<input type=\"hidden\" name=\"action\" value=\"save\" />";
		$class_var_entries = get_class_vars(get_class($this));

		while ($entry = each($class_var_entries)) {
			$name = $entry['key'];
			$value = $this->$name;

			if ($this->isEntity($value) ){
				$arr = $value->loadObjectsFromQuery($value->getLoadAllQuery());
				print("<tr><td>".$name. " </td> ");
				//print ("id is :: ".$value->id);
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


	function persist(){
		$this->dbconn();

		if($this->id == null){ //new record
			//printf("inserting record");
			$this->executeQry( $this->getPersistQuery() );
			$this->id = mysql_insert_id();
		}else{
			$this->executeQry($this->getUpdateQuery());
		}
	}

	function executeQry($qry){
		$this->dbconn();
		$result = mysql_query($qry);
		if(!$result)
		die (mysql_error());
		return $result;
	}

	/*
	 *
	 */
	function fromPrimaryKey(){
		$this->dbconn();
		//print("running qry ". $this->getLoadQuery(). "<br> ");
		$result = $this->executeQry($this->getLoadQuery());

		$row = mysql_fetch_array($result);
		$this->loadObjectFromDatabaseRow($this, $row);
	}


	/*Create an
	 *
	 */
	function loadObjectFromDatabaseRow($obj, $row){
		$classVars = get_class_vars(get_class($obj));

		foreach($classVars AS $varName => $varValue){

			foreach($row AS $key => $value){

				if(strpos($key, '___') !== false){ //load associations
					list($assocName, $assocMember) = split('___', $key);
					//print("<br> setting:  $assocName $assocMember $value" );
					$obj->$assocName->$assocMember = $value;
					continue;
				}
				
				//print strcasecmp ($key, $varName).' '. $key .' ->'. $varName.'<br>';
					
				if( strcasecmp ($key, $varName) == 0 && ! ( $this->isEntity($obj->$varName) ) ){
					//print "setting $key $varName :: ".$varName." ->". $row[$key].'<br>';
					$obj->$varName = $row[$key];
				}

			}
		}

		return $obj;
	}

	function loadObjectsFromQuery($qry){
		$this->dbconn();
		$result = $this->executeQry($qry);
		$rowCount = 0;

		while ($row = mysql_fetch_array($result)) {
			$obj = $this->createNew();
				
			$obj = $this->loadObjectFromDatabaseRow($obj, $row);
			//print 'fn '.$obj->firstName;
			$arr[] = $obj;
		}

		return $arr;
	}

	function fromRequest(){
		$classVars = get_class_vars(get_class($this));
		foreach($classVars AS $varName => $varValue){
			foreach($_REQUEST AS $key => $value) {
				//print($key ." ".$varName." $value <br/>");
				if( strcasecmp($key, $varName) ==0){
					$this->$varName = $value;
					break;
				}
				if(strpos($key, '___') !== false){
					list($assocName, $assocMember) = split('___', $key);
					//if(!isset($this->$assocName))
					//	$this->$assocName = new Grade();
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
		$dbName = "neural";
		$dbHost = "localhost";

		if (!($link=mysql_connect($dbHost, $dbUser, $dbPass))) {
			print mysql_error($link);
		}

		if (!mysql_select_db($dbName, $link)) {
			print mysql_error();
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
		return "should impl disp name";
	}



}


?>