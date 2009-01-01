<?php

abstract class HtmlControl{
	var $id;
	var $name; // array of entities
	var $styleClass;
}

class HtmlSelect extends HtmlControl {

	var $data; // array of entities
	var $value; // the current value
	
	function __construct($data= null, $name = null ){
		$this->data = $data;
		$this->$name = $name;
	}

	function render(){
		$ret .= "<select id='$this->id'>";
		for ($i = 0; $i < count($this->data); $i++){
			$entity = $this->data[$i];
			//print("disp name ".$entity->displayName());
			$ret .= "<option value='$entity->id' >".$entity->getDisplayName()."</option>";
		}
		$ret .= "</select>";

		return $ret;
	}
}

class HtmlInput extends HtmlControl {

	
	var $value; // the current value
	var $hidden; //boolean weather this val will be hidden

	function getId(){
		return $this->id== null ? $this->name: $this->id;
	}
	
	function render(){
		$ret .= "<input id='$this->getId()' name='$this->name' value='$this->value' />";
		return $ret; 
	}
}

?>