<?php
//include "Mail.php";

$from = "admin@neuraltraders.info";
$to = "singhjess@gmail.com";

print "to is ".$to;

$subject = "Hi!"; //type in subject here

$host = "mail.neuraltraders.info"; // Your domain
$username = "admin"; // Your user / full email address
$password = "admin"; // Password to your email address

$body = "hi there".$to;
$headers .= 'From: $from' . "\r\n";

mail($to, $subject, $body, $headers);


print $body;


$headers = array ('From' => $from,
'To' => $to,
'Subject' => $subject);

$smtp = Mail::factory('smtp',
array ('host' => $host,
'auth' => true,
'username' => $username,
'password' => $password));

print 'sending mail to '.$to;

$mail = $smtp->send($to, $headers, $body);

if (PEAR::isError($mail)) {
	 echo $mail->getMessage(); 
} else {
	echo 'send success ';	
}	
	
	
?>