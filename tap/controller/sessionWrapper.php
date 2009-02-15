<?php
session_start();
class SessionWrapper{

	var $instance;
	
	static function getInstance(){
		if($instance == null)
			$instance = new SessionWrapper();
		return $instance;
	}

	function push($varName, $varValue){
		//print ("in push");
		$_SESSION[$varName] = $varValue;
	}

	function pop($varName){
		$var = $_SESSION[$varName];
		$temp = null;
		
		/*if(isset($var)){
			$temp = clone($var);
		}*/
		
		$_SESSION[$varName] = null;
		return $temp;
	}

}

?>