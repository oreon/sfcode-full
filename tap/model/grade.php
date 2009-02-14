<?php 

include_once 'entity.php';
//include_once $_SERVER['DOCUMENT_ROOT'].'/tap/html/htmlControl.php';


class Grade extends Entity {
	
	var $name;

	function getDisplayName(){
		return $this->name;
	}
}

?>