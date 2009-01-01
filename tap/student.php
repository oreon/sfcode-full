<?include_once 'entity.php';?>


<?php

include 'grade.php';

class Student extends Entity {
	var $firstName;
	var $lastName;
	var $grade ;


	function __construct($fn = null, $ln = null){
		$this->firstName = $fn;
		$this->lastName = $ln;
		$this->grade = new Grade();
		//print("const:". $this->firstName ." ". $this->lastName);
	}

	function toString(){
		return $this->firstName ." ". $this->lastName;
	}

	function getLoadQuery(){
		return "select s.id as id, s.firstName as firstName, s.lastName as lastName, g.id as grade___id, g.name as grade___name  from 
			student s, grade g where s.gradeId = g.id and s.id = 5";
	}

	function getPersistQuery(){
		return "Insert into student(firstName, lastName) values('$this->firstName', '$this->lastName')";
	}

	function getUpdateQuery(){
		return "Update student set firstName='$this->firstName', lastName='$this->lastName' where id=$this->id";
	}

}


?>