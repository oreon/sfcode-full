<?php
print("You entered ".$_GET['username'] ." ". $_GET['password']); 

$un = $_GET['username'];
if($un == '' ) {
	print("username can't be empty"); 
}
//insert into users(username,password) values('neh','neh')
//$conn = mysql_connect();
?>