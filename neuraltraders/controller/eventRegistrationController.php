<?php
include_once '../model/eventRegistration.php';
include_once '../base/controller/baseController.php';



class EventRegisterationController extends BaseController {

	function save() {
		$s = new EventRegistration();
		$s->fromRequest();
		$s->persist();
		header( 'Location:../eventsaveSuccess.php?id='.$id ) ;
	}
	
	function cancel() {
		$s = new EventRegistration();
		$eventid = $_REQUEST['eventid'];
		$userid = 3;
		$s->cancel($eventid,$userid);
		
		header( 'Location:../myevents.php?id='.$id ) ;
	}
	
}

$controller = new EventRegisterationController();
$controller->execute(); 

?>