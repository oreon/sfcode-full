<?include_once 'entity.php';?>


<?php

include 'grade.php';

class Student extends Entity {
	var $firstName;
	var $lastName;
	var $grade;

	function __construct($fn = null, $ln = null){
		$this->firstName = $fn;
		$this->lastName = $ln;
		$this->grade = new Grade();
	}
	
	function createNew(){
		$s = new Student();
		return $s;
	}

	function toString(){
		return $this->id." ".$this->firstName ." ". $this->lastName." ".$this->grade->id;
	}

	function getLoadQuery(){
		return $this->getLoadAllQuery()." AND s.id = $this->id";
	}
	
	function getLoadAllQuery(){
		return "select s.id as id, s.firstName as firstName, s.lastName as lastName, g.id as grade___id, g.name as grade___name  from 
			student s left join grade g on ( s.gradeId = g.id) where 1 = 1 ";
	}

	function getPersistQuery(){
		return "Insert into student(firstName, lastName) values('$this->firstName', '$this->lastName')";
	}

	function getUpdateQuery(){
		return "Update student set firstName='$this->firstName', lastName='$this->lastName',
		 gradeId =". $this->grade->id." where id=$this->id";
	}

}


?>