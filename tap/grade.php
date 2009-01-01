<?php 

include_once 'entity.php';
include_once 'htmlSelect.php';

class Grade extends Entity{
	
	var $name;
	
	function getLoadAllQuery(){
		return "select * from grade";
	}
	
	function displayName(){
		return $this->name;
	}
}

$grade = new Grade();
$arr = $grade->loadObjectsFromQuery($grade->getLoadAllQuery());
print_r($arr);
$select = new HtmlSelect($arr, 'ddd');
print( $select->render() );


?>