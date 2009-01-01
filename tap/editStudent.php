<?php
include 'student.php';

$s = new Student("raj","singh");
$s->id = $_GET['id'];

if($s->id != null)
	$s->fromPrimaryKey();
	
print("grade is " .$s->grade->name);	

print($s->renderForm('saveStudent.php'));

?>