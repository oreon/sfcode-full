<?php
include_once '../html/htmlControl.php';
include_once '../model/entity.php';
include_once '../model/student.php';
include_once '../model/grade.php';
include_once 'baseController.php';
include_once 'sessionWrapper.php';

class StudentController extends BaseController {
	
	var $instance;
	
	static function getInstance(){
		if($instance == null)
			$instance = new StudentController();
		return $instance;
	}
	

	function save(){
		$s = new Student();
		$s->fromRequest();

		$ref =  $_SERVER['HTTP_REFERER'];

		if(!isset($s->firstName) || strlen($s->firstName) == 0 ){
			session_start();
			SessionWrapper::getInstance()->push('messages', "Please Enter a value for first Name");
			SessionWrapper::getInstance()->push('currentObj', $s);
			//printf("<script>location.href='$ref?action=validate'</script>");
			header("Location: ../editStudent.php");
			return;
		}

		$s->persist();
		//print "Record saved successuflly ";
		printf("<script>location.href='../saveStudent.php'</script>");
		//header("Location: ../saveStudent.php");
	}

	function load(){
		$s = new Student();
		$s->id = $_GET['id'];

		if($s->id != null)
			$s->fromPrimaryKey();
			
		session_start();
		SessionWrapper::getInstance()->push('currentObj', $s);
		return $s;
		//$_SESSION[] = $s;
		//print_r($_SESSION);
		//printf("<script>location.href='../editStudent.php'</script>");
	}

}

$controller = new StudentController();
$controller->execute();

?>