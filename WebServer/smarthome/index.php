<?php

 // Author: Mario Pérez Esteso
 // Web: geekytheory.com
 // Mail: mario@geekytheory.com
 
 // Fast implementation of this code. Need to improve.

 // PHP variable to store the host address
 $db_host  = "localhost";
 // PHP variable to store the username
 $db_uid  = "root";
 // PHP variable to store the password
 $db_pass = "pass..";
 // PHP variable to store the Database name  
 $db_name  = "inalambricas"; 
        // PHP variable to store the result of the PHP function 'mysql_connect()' which establishes the PHP & MySQL connection  
 $db_con = mysql_connect($db_host,$db_uid,$db_pass) or die('could not connect');
 mysql_select_db($db_name);
 $sql = "SELECT * FROM Devices";
 $result = mysql_query($sql);
 while($row=mysql_fetch_assoc($result)) {
 	$output[]=$row;
 }
 print(json_encode($output));
 mysql_close(); 
?>