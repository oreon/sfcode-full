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

}


?>