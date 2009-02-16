<?php include_once 'header.php'; ?>
<body>
<div id="main" class="box">

<?php 
$current='students';
include_once 'tabs.php'; ?>
<div id="page" class="box">
<div id="page-in" class="box">

<div id="strip" class="box noprint"><!$-- RSS feeds -->
<p id="rss"><strong>RSS:</strong> <a href="#">articles</a> / <a href="#">comments</a></p>
<hr class="noscreen" />

<!-- Breadcrumbs -->
<p id="breadcrumbs">You are here: <a href="#">Home</a> &gt; <a href="#">Category</a>
&gt; <strong>Page</strong></p>
<hr class="noscreen" />

</div>
<!-- /strip -->
<div id="content"><?php
include_once 'model/student.php';

$s = new Student();
$s->listAsTable();
?> <a href="editStudent.php"> Create New </a></div>
</div>
</div>

</body>
