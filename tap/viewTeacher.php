<?php
include_once 'html/htmlControl.php';
include_once 'model/teacher.php';
?>

<style>
	table.formTable{
		background-color:#ffd;
		border:1px solid #f92;
	}
	
	 .formTable thead{
		background-color:#f92;
		color: #ffe;
	}

</style>

<html>

<?php 
$s = new Teacher();
$s->listAsTable();
?> 
<a href="editTeacher.php"> Create New </a>


</html>