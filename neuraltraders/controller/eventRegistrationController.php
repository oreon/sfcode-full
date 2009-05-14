<?php
include_once '../model/eventRegistration.php';
include_once '../model/event.php';

include_once '../base/controller/baseController.php';
include_once '../controller/UserManager.php';
include_once '../base/messageManager.php';
include_once '../base/mailManager.php';


class EventRegisterationController extends BaseController {

	function register() {
		
		$s = new EventRegistration();
		$s->fromRequest();
		
		if($s->userid == null ){
			MessageManager::put(new Message("Please login or register to proceed with event registration."));
			header( 'Location:../template.php?node=loginOrRegister' ) ;
			return;
		}
		
		$s->persist();
		
		$event = new Event();
		$event->fromId($s->eventid );
		
		$user = new User();
		$user->fromId($s->userid);
		
		$eventNumber = $user->id + 8;
		
		$body .= " This email confirms you for event: $event->name on $event->date : $event->time .\n";
		$body .= " Your confirmation number is TZ-$eventNumber \n.";
		MailManager::send("NeuralTraders Event Confirmation", $body, $user->email );
		
		$msg = new Message("You have successfully registered for the event - $event->name", 'S');
		MessageManager::put($msg);
		
		header( 'Location:../template.php?node=myevents' ) ;
	}
	
	function cancel() {
		$s = new EventRegistration();
		$eventid = $_REQUEST['eventid'];
		$userid = UserManager::getLoggedInUser()->id;
		$s->cancel($eventid,$userid);
		MessageManager::put(new Message("Your registration for the event has been cancelled !"));
		header( 'Location:../template.php?node=myevents') ;
	}
	
}

$controller = new EventRegisterationController();
$controller->execute(); 

?>