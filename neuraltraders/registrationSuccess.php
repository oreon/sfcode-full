<?php
include_once 'model/user.php';
$user = new User();
?>
Dear <?php print $user->firstName ?>, thanks for registering with NeuralTraders. An email has been sent to you please click the 
link in the email to activate your account.