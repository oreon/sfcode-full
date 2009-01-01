<?include 'entity.php';?>

<?php

class Student extends Entity {
	var $firstName;
	var $lastName;
	

	function __construct($fn, $ln){
		$this->firstName = $fn;
		$this->lastName = $ln;
		//print("const:". $this->firstName ." ". $this->lastName);
	}

	function toString(){
		return $this->firstName ." ". $this->lastName;
	}
	
	function getPersistQuery(){
		return "Insert into student(firstName, lastName) values('$this->firstName', '$this->lastName')";
	}
	
	function getUpdateQuery(){
		return "Update student set firstName='$this->firstName', lastName='$this->lastName' where id=$this->id";
	}

}


?>