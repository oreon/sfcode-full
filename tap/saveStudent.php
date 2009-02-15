<?php include_once 'header.php'; ?>
<body>

<?php
include_once 'model/student.php';

$s = new Student();
$s->listAsTable();
?>



<a href="editStudent.php" > Create New </a>
	

</body>