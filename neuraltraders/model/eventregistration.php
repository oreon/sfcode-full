<?php
include_once '/base/model/entity.php';

class EventRegistration extends Entity{
	var $userid;
	var $eventid;
	var $status;
	
	function getPersistQuery(){
		
		$qry = "insert into neural.eventregistraion 
			(userid, 
			eventid
			)
			values
			('$this->userid', 
			'$this->eventid' 
			)";
	}
	
	
}
?>