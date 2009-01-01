<?php
include 'student.php';

$s = new Student("raj","singh");
//$s->persist();
print($s->renderForm('saveStudent.php'));

?>