<?php
session_start(); 
include 'sponsor_class.php'; 
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

print "<div class='message'>Sponsor saved successfully</div>";
$s = unserialize($_SESSION['sponsor']);
if(!isset($s))
 print "nothing in sponsor";
?>

<table border="1">
<tr><td>First Name </td><td><? print $s->firstname ?></td></tr>
<tr><td>LastName</td>  <td><? print $s->lastname ?></td>
<tr><td>email</td>  <td><? print $s->eml ?></td>
 </tr>
 <tr><td>password</td>  <td><? print $s->psd ?></td>
 </tr>
 </table>





