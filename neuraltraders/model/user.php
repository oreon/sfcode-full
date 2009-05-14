<?php
@include_once '../base/model/entity.php';

class User extends Entity{
	var $firstName;
	var $lastName;
	var $email;
	var $phone;
	var $userName;
	var $password;
	var $type;
	

	function getPersistQuery(){
		 $qry =	"insert into neural.user 
		(firstname, 
		lastname, 
		email, 
		phone, 
		username, 
		password
		
		)
		values
		('$this->firstName', 
		'$this->lastName', 
		'$this->email', 
		'$this->phone', 
		'$this->userName', 
		'$this->password'
		)";
		return $qry;
	}

	function getUpdateQuery(){
		return "Update student set firstName='$this->firstName', lastName='$this->lastName',
		 gradeId =". $this->grade->id." where id=$this->id";
	}
	
}
?>