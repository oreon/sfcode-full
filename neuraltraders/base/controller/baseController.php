<?php 

class BaseController {
	
	function execute(){
		$method = $_REQUEST['action'];
		if(!isset($method))
			die ("no method specified");
	
		$cls = new ReflectionClass($this);
		$func = $cls->getMethod($method);
		$args;
		$func->invoke($this);
	}
	
	function returnToReferer(){
		$ref = $_SERVER['HTTP_REFERER'];
		header( "Location:$ref" ) ;
	}
}

?>