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
}
?>