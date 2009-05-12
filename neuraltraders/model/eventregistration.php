<?php
include_once '../base/model/entity.php';

class EventRegistration extends Entity{
	var $userid;
	var $eventid;
	var $status;

	function getPersistQuery(){

		$qry = "insert into neural.eventregistration
		(userid,
		eventid
		)
		values
		('$this->userid',
		'$this->eventid'
		)";
			
		return $qry;
	}

	function cancel($eventid, $userid){
		$qry = "update eventregistration set status = 'cancel' where eventid= $eventid and userid = $userid";
		
		$this->executeQry($qry);
	}


}
?>