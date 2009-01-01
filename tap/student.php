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
		return "Insert into student(firstName, lastName) values('?', '?')";
	}

}

$s = new Student("raj","singh");
//$s->firstName = "mohan";
//print_r(get_object_vars($s));
print($s->renderForm('saveStudent.php'));


?>