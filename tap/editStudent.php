<?php
include_once 'model/student.php';

$s = new Student();
$s->id = $_GET['id'];

print($s->toString());

if($s->id != null)
	$s->fromPrimaryKey();
//echo  $s->renderSingleObject() ;

print($s->renderForm('controller/studentController.php'));

?>