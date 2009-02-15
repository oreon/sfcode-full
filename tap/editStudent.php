<?php
include_once 'html/htmlControl.php';
include_once 'model/student.php';
include_once 'controller/sessionWrapper.php';
//include_once 'controller/studentController.php';
?>

<?php include_once 'header.php'; ?>
<body>
<?php

//echo SessionWrapper::getInstance()->pop('messages');
//echo  $s->renderSingleObject() ;	

$s = SessionWrapper::getInstance()->pop('currentObj');

if($s == null){
	print "creating new student";
	$s = new Student();
}

//$controller = StudentController::saveInstance();
//$s = $controller->load();
//print $s->renderForm('controller/studentController.php');

//$s = new Student();
$id =  $_GET['id'];

if(isset($id)) {
	$s->id = $id;
	$s->fromPrimaryKey();
}
//echo  $s->renderSingleObject() ;

print($s->renderForm('controller/studentController.php'));


?>
</body>