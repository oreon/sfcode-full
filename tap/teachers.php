<?php 
	include_once 'model/teacher.php';
	$teacher = new Teacher();
	$teachers = $teacher->loadObjectsFromQuery($teacher->getLoadAllQuery());
	
	foreach ($teachers as $teacher){
		echo "$teacher->firstName <img src='' /> </br>";
	}

?>