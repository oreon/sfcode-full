<?php
include_once '../html/htmlControl.php';
include_once '../model/entity.php';
include_once '../model/teacher.php';
include_once '../model/grade.php';
include_once 'baseController.php';
include_once 'sessionWrapper.php';
include_once '../utils/FileUpload.php';

class TeacherController extends BaseController {
	
	var $instance;
	
	static function getInstance(){
		if($instance == null)
			$instance = new TeacherController();
		return $instance;
	}
	
	function save(){
		$s = new Teacher();
		$s->fromRequest();
		$s->persist();
		
		FileUpload::upload('teachers/'.$s->id);
		//header("Location: ../viewTeacher.php");
	}

	function load(){
		$s = new Teacher();
		$s->id = $_GET['id'];

		if($s->id != null)
			$s->fromPrimaryKey();
			
		session_start();
		$_SESSION['currentObj'] = serialize($s);
		
		$t = unserialize($_SESSION['currentObj']);
			
		//echo $t->firstName;
		header("Location: ../editTeacher.php");
		return $s;
	}

}

$controller = new TeacherController();
$controller->execute();

?>