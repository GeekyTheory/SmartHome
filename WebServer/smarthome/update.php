<?php

 // Author: Mario Pérez Esteso
 // Web: geekytheory.com
 // Mail: mario@geekytheory.com

 // Fast implementation of this code. Need to improve.
$id = $_POST["ID"];
$atr = $_POST["ATR"];
$value = $_POST["VALUE"];

update(connectDataBase(), $id, $atr, $value);

function update ($con, $id, $atr, $value){
	echo "ID: ".$id;
	$sql="UPDATE Devices SET $atr='$value' WHERE ID=$id";
	$result=mysqli_query($con, $sql);
	echo "ID: ".$id." - ATR: ".$atr." - VALUE: ".$value. " - RESULT: ".$result;
}

function connectDataBase(){
	$mysql_hostname = "localhost";
	$mysql_user = "root";
	$mysql_password = "pass..";
	$mysql_database = "inalambricas";
	$link = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password, $mysql_database);
	return $link;
}
?>