<?php

class StudentController{

	function save(){
		$s = new Student();
		$s->fromRequest();
		$s->persist();
	}

}


?>