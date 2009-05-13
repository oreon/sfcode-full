<?php
require_once "Mail.php";

/**************************************************
EDIT the following variables for your own use
***************************************************/
$from = "Sender <admin@neuraltraders.info>";
$to = "Recipient <singhjess@gmail.com>";

$subject = "Hi!"; //type in subject here

$host = "mail.neuraltraders.info"; // Your domain
$username = "admin"; // Your user / full email address
$password = "admin"; // Password to your email address



$body = "hi there".$to;

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
/**************************************************
ERROR MESSAGE
***************************************************/
?>

<p> <? echo $mail->getMessage(); ?> </p>
<?
/**************************************************/
} else {
/**************************************************
SUCCESS MESSAGE
***************************************************/
?>
<p>Message successfully sent!</p>
<?
/**************************************************/
}
?>