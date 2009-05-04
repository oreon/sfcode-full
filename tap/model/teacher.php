<?
include_once 'entity.php';
include 'grade.php';

class Teacher extends Entity {
	var $firstName;
	var $lastName;
	
	function createNew(){
		$s = new Teacher();
		return $s;
	}
	
	
	function toString(){
		return $this->id." ".$this->firstName ." ". $this->lastName." ";
	}

	function getLoadQuery(){
		return $this->getLoadAllQuery()." AND s.id = $this->id";
	}
	
	function getLoadAllQuery(){
		return "select s.id as id, s.firstName as firstName, s.lastName as lastName from teacher s where 1 = 1 ";
	}

	function getPersistQuery(){
		return "Insert into teacher(firstName, lastName)  values('$this->firstName', '$this->lastName')";
	}

	function getUpdateQuery(){
		return "Update teacher set firstName='$this->firstName', lastName='$this->lastName' where id=$this->id";
	}
}

?>