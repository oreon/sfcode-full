<?php
session_start();
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

<?php 

 $teacher = new Teacher();
 $teacher = unserialize($_SESSION['currentObj']);

?>

<html>
<table class="formTable">
<thead><tr><td colspan="2"> Teacher </td></tr></thead>
<form action="controller/teacherController.php">
<input type=hidden name='id' value='<? echo $teacher->id ?>' />
<input type="hidden" name="action" value="save" />
<tr><td> First Name </td><td> <input type="text" name="firstName" value=<? echo $teacher->firstName ?> /> </td></tr>
<tr><td> Last Name </td><td> <input type="text" name="lastName" value=<? echo $teacher->lastName ?> /> </td></tr>
<tr><td colspan="2" > <input type="submit" value="Go" /> </td>
</form>
</table>
</html>