<?php
include_once '../model/user.php';
include_once '../base/controller/baseController.php';
include_once 'UserManager.php';
include_once '../base/message.php';
include_once '../base/messageManager.php';
include_once '../base/mailManager.php';



class UserController extends BaseController {

	function save() {
		$s = new User();
		$s->fromRequest();
		$s->persist();

		$msg = new Message('Thanks for registering '.$s->firstName,'S');
		MessageManager::put($msg);
		
		$this->sendRegMail($s);

		header( 'Location:../template.php?node=registrationSuccess&id='.$id ) ;
	}

	function sendRegMail($user){
		//mailer = new MailManager();
		$msg .= "Thanks for registering with NeuralTraders. \n";
		$msg .= "We look forward to a symbiotic relationship and helping you take your trading to the next level with our training and automated tools. \n";
		$msg .= "Regards, \n";
		$msg .= "Neural Traders Team";
		MailManager::send("NeuralTraders Registration", $msg, $user->email );
	}

	function authenticate(){
		$username = $_REQUEST['userName'];
		$password = $_REQUEST['password'];

		$qry = "select * from user where ( username= '$username' or email='$username') and password = '$password'";
		$user = new User();
		$arr = $user->loadObjectsFromQuery($qry);

		if( is_array($arr) && count($arr) > 0 ){
			UserManager::setLoggedInUser($arr[0]);
			header( 'Location:../template.php?node=welcome' ) ;
		}else{
			$msg = new Message('Username/Email or password mismatch');
			MessageManager::put($msg);
			$this->returnToReferer();
		}
	}

	function mailPassword(){
		$email = $_REQUEST['email'];
		$qry = "select *  from user where email = '$email'";
		$user = new User();	
		$arr = $user->loadObjectsFromQuery($qry);
		
		if( is_array($arr) && count($arr) > 0 ){
			$user = $arr[0];
			
			$body .= " Username:$user->userName ";
			$body .= " Password:$user->password ";
			
			MailManager::send("Your NeuralTraders Credentials", $body, $user->email );
			
			$msgText = 'Your credentials have been sent to  - '.$email;
			$msg = new Message($msgText, 'S');
			MessageManager::put($msg);
		}else {
			$msgText = 'We could not find any user with email - '.$email;
			$msg = new Message($msgText);
			MessageManager::put($msg);
		}
		
		$this->returnToReferer();
		
		
		
	}
	
	function logout(){
		session_start();
		session_destroy();
		header( 'Location:../template.php?node=main' ) ;
	}

}

$controller = new UserController();
$controller->execute();




?>