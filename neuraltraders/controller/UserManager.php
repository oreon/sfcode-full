<?php

include_once '../base/controller/baseController.php';
include_once '../model/user.php';

class UserManager extends BaseController {
	
	static function getLoggedInUser(){
		
	}
	
	function authenticate(){
		$username = $_REQUEST['userName'];
		$password = $_REQUEST['password'];
		
		$qry = "select * from user where username= '$username' or email='$username' and password = '$password'";
		$user = new User();
		$arr = $user->loadObjectsFromQuery($qry);
		if( is_array($arr) && count($arr) > 0 ){
			//print count($arr);
			header( 'Location:../template.php?node=welcome' ) ;
		}else{
			$ref = $_SERVER['HTTP_REFERER'].'?errMsg=invalidUser';
			header( "Location:$ref" ) ;
		}
		
	}
}

$userManager = new UserManager();
$userManager->execute();
?>