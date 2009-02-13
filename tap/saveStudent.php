

<?php
include_once 'model/student.php';

$s = new Student();
$s->fromRequest();
$s->persist();

$s->listAsTab();

$s->listAsTable();
?>