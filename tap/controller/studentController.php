<?php
include_once '../model/student.php';
include_once '../model/grade.php';

class StudentController{

	function execute(){
		$method = $_GET['action'];
		if(!isset($method))
			die ("no method specified");
	
		$cls = new ReflectionClass($this);
		$func = $cls->getMethod($method);
		$args;
		$func->invoke($this);
	}

	function save(){
		$s = new Student();
		$s->fromRequest();
		$s->persist();
		header("Location: ../saveStudent.php");
	}

	function load(){

	}

}

$controller = new StudentController();
$controller->execute();

?>