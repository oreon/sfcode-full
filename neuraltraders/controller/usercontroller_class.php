<?php
include_once '../model/user.php';
include_once '../base/controller/baseController.php';
include_once 'UserManager.php';



class UserController extends BaseController {

	function save() {
		$s = new User();
		$s->fromRequest();
		$s->persist();
		header( 'Location:../template.php?node=registrationSuccess&id='.$id ) ;
	}

	function authenticate(){
		$username = $_REQUEST['userName'];
		$password = $_REQUEST['password'];

		$qry = "select * from user where ( username= '$username' or email='$username') and password = '$password'";
		//print $qry;
		$user = new User();
		$arr = $user->loadObjectsFromQuery($qry);
		//print_r ( $arr );
		
		if( is_array($arr) && count($arr) > 0 ){
			//print $arr[0]->userName;
			UserManager::setLoggedInUser($arr[0]);
			header( 'Location:../template.php?node=welcome' ) ;
		}else{
			$ref = $_SERVER['HTTP_REFERER'].'&errMsg=invalidUser';
			header( "Location:$ref" ) ;
		}
	}
	
	function logout(){
		session_start();
		session_destroy();
		header( 'Location:../template.php?node=main' ) ;
	}

}

$controller = new UserController();
$controller->execute();




?>