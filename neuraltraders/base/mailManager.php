<?php
require_once("Mail.php");

class MailManager{
	
	public static $smtpinfo;

	static function setup(){
		$smtpinfo["host"] = "mail.neuraltraders.info";
		$smtpinfo["port"] = "26";
		$smtpinfo["auth"] = true;
		$smtpinfo["username"] = "admin+neuraltraders.info";
		$smtpinfo["password"] = "admin";
	}

	function send($subject, $body, $to ){
		MailManager::setup();
		/* mail setup recipients, subject etc */
		$recipients = "singhjess@gmail.com, jagdeepskohli@yahoo.com";
		$headers["From"] = "admin@neuraltraders.info";
		$headers["Subject"] = $subject;
		$mailmsg = $body;
		/* Create the mail object using the Mail::factory method */
		$mail_object =& Mail::factory("smtp", $smtpinfo);
		/* Ok send mail */
		$mail_object->send($recipients, $headers, $mailmsg);

		if (PEAR::isError($mail_object)) {
			echo $mail_object->getMessage();
		} else {
			//echo 'send success ';
		}
	}


}
?>