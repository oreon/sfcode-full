<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
header('Content-Type: text/xml');
print file_get_contents(:"http://www.medicinenet.com/rss/dailyhealth");

?>
