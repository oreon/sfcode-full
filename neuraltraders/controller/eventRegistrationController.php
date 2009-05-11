<?php
include_once '../model/event.php';
include_once '../base/controller/baseController.php';



class EventRegisterationController extends BaseController {

	function save() {
		$s = new Event();
		$s->fromRequest();
		$s->persist();
		header( 'Location:../eventsaveSuccess.php?id='.$id ) ;
	}
		
	

}

$controller = new EventRegisterationController();
$controller->execute(); 

?>