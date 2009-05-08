<?php

session_start(); 
include 'grade_class.php'; 
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

print "<div class='message'>grade saved successfully</div>";
$g = unserialize($_SESSION['grade']);
if(!isset($g))
 print "nothing in student";
?>

<table border="1">
<tr><td>Grade Name </td><td><? print $g->gradename ?></td></tr>
 </table>
