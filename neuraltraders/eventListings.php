<?php

include_once 'base/model/entity.php';
include_once 'model/event.php';

$event = new Event();
$userid = UserManager::getLoggedInUser()->id;

$arr = $event->getEvents($userid);

if(count($arr) > 0){
	echo 'Upcoming Events';
	foreach ($arr as $obj){
		print $obj->renderEvent();
		print "<form action='controller/eventRegistrationController.php'>";
		print "<input type='hidden' name='action' value='register' />";
		print "<input type='hidden' name='eventid' value='$obj->id' />";
		print "<input type='hidden' name='userid' value='$userid' />";
		print"<input type='submit' value='Register for this event' />";
		print"</form><br/><hr/>";
	}
}else{
	echo 'There are no upcoming events';
}

?>




