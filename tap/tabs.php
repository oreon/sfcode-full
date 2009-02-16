
<!-- Main menu (tabs) -->
<?php 

//$current = $_REQUEST['current'];

$arr = array('index','students','grades','exams', 'teachers','subjects');

?>
<div id="tabs" class="noprint">

<h3 class="noscreen">Navigation</h3>
<ul class="box">
<?php
foreach($arr as $a){
	$active = ($a == $current)?"active":"";
	echo "<li id=\"$active\"><a href=\"$a.php\">$a<span class=\"tab-l\"></span><span class=\"tab-r\"></span></a></li>";
}
?>
</ul>

<hr class="noscreen" />
</div>
<!-- /tabs -->
