<?php 

include_once 'entity.php';
include_once 'htmlSelect.php';

class Grade extends Entity{
	
	var $name;

	function getDisplayName(){
		return $this->name;
	}
}

?>