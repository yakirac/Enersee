<?php
$db = $_GET['dbname'];

$username = "funkymonkey1193";
$password = "wakaza10foot";
$hostname = "localhost"; 

//connection to the database
$dbhandle = mysql_connect($hostname, $username, $password) or die ('Error connecting to MySQL server');

//select 

/*$res = mysql_query("SHOW databases");
while($row = mysql_fetch_array($res, MYSQL_NUM)) {
    echo "$row[0]\n<br>";
}*/
$res = mysql_query("SHOW TABLES FROM $db");
//$databases = array();
$num = mysql_num_rows($res);
for ($i = 0; $i < $num; $i++){
	$json["tname"] = mysql_result($res, $i);
	$tables[] = $json;
}

$json2['tables'] = $tables;

//$json["databases"] = $databases[];

echo json_encode($json2); 

?> 