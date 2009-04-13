<?php
header('Content-Type: text/xml');
p
print(file_get_contents(:"http://www.medicinenet.com/rss/dailyhealth"));
?>
