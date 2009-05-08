<?php
session_start(); 
include 'student_class.php'; 
?>
<style>
 .message{
 	background-color: #ffd;
 	border: 1px soild red;
 	font-size: 0.9em;
 	font-family: verdana;
 }

</style>

<?php

print "<div class='message'>Student saved successfully</div>";
$s = unserialize($_SESSION['student']);
if(!isset($s))
 print "nothing in student";
?>

<table border="1">
<tr><td>First Name </td><td><? print $s->firstName ?></td></tr>
<tr><td>LastName</td>  <td><? print $s->lastName ?></td>
<tr><td>DOB</td>  <td><? print $s->dob ?></td>
 </tr>
 <tr><td>Gender</td>  <td><? print $s->gender ?></td>
 </table>





