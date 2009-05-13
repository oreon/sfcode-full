<?php


@include_once '../model/user.php';
@include_once '../base/controller/baseController.php';
@include_once 'SessionManager.php';


class UserManager  {
	
	static  $NAME='USER';
	
	static function getLoggedInUser(){
		return SessionManager::getObject('USER');
	}
	
	static function setLoggedInUser($user){
		SessionManager::putObject('USER', $user);
	}
	
}

?>