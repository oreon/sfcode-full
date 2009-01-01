

<?php
include 'student.php';

$s = new Student("","");
$s->fromRequest();
$s->persist();
$s->listAsTable();

?>