<?php
include_once '../model/eventRegistration.php';
include_once '../base/controller/baseController.php';
include_once '../controller/UserManager.php';

class EventRegisterationController extends BaseController {

	function save() {
		$s = new EventRegistration();
		$s->fromRequest();
		$s->persist();
		header( 'Location:../template.php?node=myevents' ) ;
	}
	
	function cancel() {
		$s = new EventRegistration();
		$eventid = $_REQUEST['eventid'];
		$userid = UserManager::getLoggedInUser()->id;
		$s->cancel($eventid,$userid);
		
		header( 'Location:../template.php?node=myevents') ;
	}
	
}

$controller = new EventRegisterationController();
$controller->execute(); 

?>