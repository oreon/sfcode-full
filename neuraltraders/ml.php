<?php
include("base/mailManager.php");

//mailer = new MailManager();
$msg .= "Thanks for registering with NeuralTraders. \n";
$msg .= "We look forward to a symbiotic relationship and helping you take your trading to the next level with our training and automated tools. \n";
$msg .= "Regards, \n";
$msg .= "Neural Traders Team";
MailManager::send("NeuralTraders Registration", $msg, 'singhjess@gmail.com' );

?>