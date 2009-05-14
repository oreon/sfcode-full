<?php
include_once '../model/event.php';
include_once '../base/controller/baseController.php';
include_once '../base/messageManager.php';




class EventController extends BaseController {

	function save() {
		$s = new Event();
		$s->fromRequest();
		$s->persist();
		
		MessageManager::put(new Message("Successfully created the event id $s->id", 'S'));
		$this->returnToReferer();
	}
		
	

}

$controller = new EventController();
$controller->execute(); 

?>