<?php

class SessionManager {
	
	static function putObject($name, $obj ){
		@session_start();
		//print $obj->userName;
		$_SESSION[$name] = serialize($obj);
	}
	
	static function getObject($name){
		@session_start();
		$obj = unserialize($_SESSION[$name]);
		//print ((User)$obj);
		//print $name.' xxx ' .$obj->userName;
		return $obj;
	}
	
}

?>