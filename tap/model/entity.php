<?php



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
	
	function listAsTable(){
		
		$arr = $this->loadObjectsFromQuery($this->getLoadAllQuery());
	
		$rowCount = 0;

		print("<table border=\"1\" >");
		
		foreach ($arr as $obj) {
			print("<tr>");
			$id = $obj->id;
			print("<td> <input type='checkbox' value='$id' ></td>");
			$this->renderFieldNameValue($obj, true);
			print("<td> <a href='editStudent.php?action=load&id=$id' > Edit </a></td>");
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

		if($this->id == null){
			//printf("inserting record");
			mysql_query($this->getPersistQuery());
		}else{
			//printf("updating record ".$this->getUpdateQuery());
			mysql_query($this->getUpdateQuery());
		}
	}

	/*
	 *
	 */
	function fromPrimaryKey(){
		$this->dbconn();
		//print("running qry ". $this->getLoadQuery(). "<br> ");
		$result = mysql_query($this->getLoadQuery());
		if(!$result){
			print  " error is ".mysql_error();
		}
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
					//if(!isset($this->$assocName))
					//	$obj->$assocName = new Grade();
					//print("<br> setting:  $assocName $assocMember $value" );
					$obj->$assocName->$assocMember = $value;
					continue;
				}
							
				if( $key == $varName && ! ( $this->isEntity($obj->$varName) ) ){
					//print "setting $key $varName :: ".$varName." ->". $row[$varName];
					$obj->$varName = $row[$varName];
				}

			}
		}
		
		return $obj;
	}

	function loadObjectsFromQuery($qry){
		$this->dbconn();
		//$query = $this->getLoadAllQuery();
		$result = mysql_query($qry);
		$rowCount = 0;
	
		while ($row = mysql_fetch_array($result)) {
			$obj = $this->createNew();
			$obj = $this->loadObjectFromDatabaseRow($obj, $row);
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