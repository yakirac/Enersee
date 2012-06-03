<?php
$db = $_GET['dbname'];
$tb = $_GET['tname'];

$username = "funkymonkey1193";
$password = "wakaza10foot";
$hostname = "localhost"; 

//connection to the database
$dbhandle = mysql_connect($hostname, $username, $password) or die ('Error connecting to the database');


mysql_select_db("$db") ;


//execute the SQL query and return records
//$result = mysql_query("SELECT TIME_TO_SEC(t) AS tts, d, P_L1 FROM $tb");
$result = mysql_query("SELECT t, d, P_L1 FROM $tb");

$num = mysql_num_rows($result);

$dt = array();

//Column Information
$col_ids = array("t", "P_L1");
$col_labels = array("Time", "Power");
$col_types = array("string", "number");

//Populate column information
for($y = 0; $y < count($col_ids); $y++){
	$dt["cols"][$y]["id"] = $col_ids[$y];
	$dt["cols"][$y]["label"] = $col_labels[$y];
	$dt["cols"][$y]["type"] = $col_types[$y];
	
}

//Get data from result and populate table
$rc = 0;

/*if($num > 0){
	while($row = mysql_fetch_array($result)){
		$d = $row['tts'];
		//echo $d;
		$dt["rows"][$rc]["c"][0]["v"] = $d;
		$d = $row['P_L1'];
		$d = floatval($d);
		$dt["rows"][$rc]["c"][1]["v"] = $d;
		$rc++;
	}
}

echo json_encode($dt);*/

if($num > 0) {
	for ($i = 0; $i < $num; $i++){
		$json["time"] = mysql_result($result, $i, "t");
		$json["date"] = mysql_result($result, $i, "d");
		$json["power"] = mysql_result($result, $i, "P_L1");
		$dataSet[] = $json;
	}
	$json2['dataSet'] = $dataSet;
}

echo json_encode($json2);


?>