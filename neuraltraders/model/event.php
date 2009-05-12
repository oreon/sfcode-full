<?php

include_once '../base/model/entity.php';

class Event extends Entity{
	var $name;
	var $date;
	var $time;
	var $price;
	var $venue;
	var $maxseat;
	var $description;



	function getPersistQuery(){
		$qry =	 "insert into neural.event
		(name,
		date,
		time,
		price,
		venue,
		maxseat,
		description
		)
		values
		('$this->name',
		'$this->date',
		'$this->time',
		'$this->price',
		'$this->venue',
		'$this->maxseat',
	 '$this->description'
	 )";
		return $qry;
	}

	function getEvents(){
		$qry = "select * from event where  date > curdate()";
		$arr = $this->loadObjectsFromQuery($qry);
		return $arr;
	}

	function getUserEvents($userid) {
		$qry = "select e.* from eventRegistration er, event e where userid = $userid and e.id = er.eventid and status = 'ok';";
		$arr = $this->loadObjectsFromQuery($qry);
		return $arr;
	}

	function renderEvent($event){
		print "<table border='1'>"	;
		print "<tr><td><b> $event->name, $event->date : $event->time </b></td></tr>" ;
		print "<tr><td><b> $event->description </td></tr>" ;
		print "</table>";
	}
}
?>