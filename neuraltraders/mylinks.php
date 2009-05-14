<h3>My Resources</h3>
<ul>
<li><a href="template.php?node=myEvents">My Events</a></li> 
<li><a href="template.php?node=profile">My Profile </a></li> 
<?php print '<a href="controller/usercontroller_class.php?action=logout">Logout '.UserManager::getLoggedInUser()->userName.'</a>'; ?> 

</ul>