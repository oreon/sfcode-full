<?php
include 'student.php';

$s = new Student("raj","singh");
$s->id = $_GET['id'];
//print("id is ".$_GET['id']." objid: ".$s->id);

if($s->id != null)
	$s->fromPrimaryKey();
print($s->renderForm('saveStudent.php'));

?>