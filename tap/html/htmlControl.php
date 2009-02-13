<?php

abstract class HtmlControl{
	var $id;
	var $name; // array of entities
	var $styleClass;
	
	function getId(){
		return $this->id== null ? $this->name: $this->id;
	}
}

class HtmlSelect extends HtmlControl {

	var $data; // array of entities
	var $value; // the current value
	
	function __construct($data= null, $name = null, $value = null ){
		$this->data = $data;
		$this->name = $name;
		$this->value = $value;
	}

	function render(){
		print("name is :".$this->name." nnnn");
		$ret .= "<select name=\"".$this->name."\" ><option value=\"null\">Select</option>";
		
		for ($i = 0; $i < count($this->data); $i++){
			$entity = $this->data[$i];
			$selected = $entity->id == $this->value?"selected='yes'":"";
			//print("disp name ".$entity->displayName());
			$ret .= "<option value='$entity->id'  $selected >".$entity->getDisplayName()."</option>";
		}
		$ret .= "</select>";

		return $ret;
	}
}

class HtmlInput extends HtmlControl {

	
	var $value; // the current value
	var $hidden; //boolean weather this val will be hidden

	
	function render(){
		$ret .= "<input id='$this->getId()' name='$this->name' value='$this->value' />";
		return $ret; 
	}
}

?>