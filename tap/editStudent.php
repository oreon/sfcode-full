<?php
include_once 'model/student.php';

$s = new Student();
$s->id = $_GET['id'];

print($s->toString());

if($s->id != null)
	$s->fromPrimaryKey();

	
echo  $s->renderSingleObject() ;
	
print("<hr/>");
print($s->renderForm('saveStudent.php'));
print ("grade id is ".$s->grade->id);

?>