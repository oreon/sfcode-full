<?php
include_once '../model/user.php';
include_once '../base/controller/baseController.php';



class UserController extends BaseController {

	function save() {
		$s = new User();
		$s->fromRequest();
		$s->persist();
		header( 'Location:../registrationSuccess.php?id='.$id ) ;
	}

	

}

$controller = new UserController();
$controller->execute(); 




?>