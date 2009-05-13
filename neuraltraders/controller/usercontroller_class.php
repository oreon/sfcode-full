<?php
include_once '../model/user.php';
include_once '../base/controller/baseController.php';
include_once 'UserManager.php';
include_once '../base/message.php';
include_once '../base/messageManager.php';



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

		$msg = new Message('Username/Email or password mismatch','S');
		MessageManager::put($msg);
		$ref = $_SERVER['HTTP_REFERER'].'&errMsg=invalidUser';
		header( "Location:$ref" ) ;
		return;

		$qry = "select * from user where ( username= '$username' or email='$username') and password = '$password'";
		//print $qry;
		$user = new User();
		$arr = $user->loadObjectsFromQuery($qry);
		//print_r ( $arr );

		if( is_array($arr) && count($arr) > 0 ){
			//print $arr[0]->userName;
			UserManager::setLoggedInUser($arr[0]);
			header( 'Location:../template.php?node=welcome' ) ;
		}else{
			$ref = $_SERVER['HTTP_REFERER'].'&errMsg=invalidUser';
			header( "Location:$ref" ) ;
		}
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