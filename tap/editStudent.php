<?php
include 'student.php';

$s = new Student("raj","singh");
$s->id = $_GET['id'];

print($s->toString());

if($s->id != null)
	$s->fromPrimaryKey();

print($s->renderForm('saveStudent.php'));

?>