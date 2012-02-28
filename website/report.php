<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>EnerSee</title>
    <link href="style.css" rel="stylesheet" type="text/css" />
    
    <!--Load the AJAX API-->
	<script type="text/javascript" src="jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="enersee.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
		//getDatabases();
		google.load("visualization", "1", {packages:["corechart"]});
		
		// Load the Visualization API and the piechart package.
		//var data = new google.visualization.DataTable();
		var rows = 0;
		var dts;
				  //google.setOnLoadCallback(drawChart);
				  function drawChart(table) {
						var data = new google.visualization.DataTable(table);
						///data.addColumn('number', 'Time');
						//data.addColumn('number', 'Power');
						//data.addColumn('number', 'Current');
						//alert("In draw chart #rows = " + rows);
						//data.addRows(59);
						/*var numbers = [];
						var times = [];
						var i;
						for (i = 0; i < rows; i++){
							//var power = 29;
							var sd = table[i];
							//alert("Power = " + sd.power);
							//var num = data.addRow(["12:00", 55]);
							//alert("Number from add row = " + num);
							//power++;
							numbers[i] = sd.power;
							times[i] = sd.time;
						}
						//alert("The power numbers = " + numbers);
						//alert("The times are: " + times);
						//var r = data.addRows(rows);
						//alert("Number of rows: " + r);
						var k;
						for (k = 0; k < r; k++){
							alert("Time = " + times[k] + "Power = " + numbers[k]);
							data.setValue(k, 1, numbers[k]);
						}
						/*data.setValue(0, 0, '00:00');
						data.setValue(0, 1, 1000);
						data.setValue(0, 2, 400);
						data.setValue(1, 0, '06:00');
						data.setValue(1, 1, 1170);
						data.setValue(1, 2, 460);
						data.setValue(2, 0, '12:00');
						data.setValue(2, 1, 860);
						data.setValue(2, 2, 580);
						data.setValue(3, 0, '23:00');
						data.setValue(3, 1, 1030);
						data.setValue(3, 2, 540);
						*/
				
						var chart = new google.visualization.LineChart(document.getElementById('graph_div'));
						chart.draw(data, {width: 500, 
										  height: 340, 
										  title: 'Device Energy Usage', 
										  hAxis: { title: 'Time (seconds)', titleTextStyle: {color: '#9cc52c'}},
										  colors: ['#9cc52c']
										  });
				  }
	
		$(document).ready(function () {
			$('#getDB').click (function(evt) {
				evt.preventDefault();
				getDbValue();
			});
			$('#getTable').click (function(evt) {
				evt.preventDefault();
				var ds = getTableValue();
				//for( var d in ds){
				//	rows++;
				//}
				//alert("Number of rows = " + rows);
				drawChart(ds);
				//$('#graph_div').append("This is the data: " + ds);
			});
		});
    
    
	</script>

</head>

<body onload="getDatabases()">
    <div id="main">
    
    	<div class="container">
        
        	<div id="header">
            
            	<ul id="menu">
                	<li><a href="http://129.187.150.139/Enersee/index.php">Home</a></li>
                    <li><a href="http://129.187.150.139/Enersee/about.php">About</a></li>
                    <li><a href="http://129.187.150.139/Enersee/report.php">Report</a></li>
                    <li><a href="http://129.187.150.139/Enersee/goals.php">Goals</a></li>
                    <li><a href="http://129.187.150.139/Enersee/downloads.php">Downloads</a></li>
                </ul>
            
            </div>
            
            <div id="databases" class="selection_form">
                <div data-role="content">
                <h3>Database Options: </h3>
                <form action="" method="POST" id="" name="dgroup">
                    <div data-role="fieldcontain" class="db" data-theme="e">
                        <fieldset data-role="controlgroup" id="databaseF">
                            
                        </fieldset>
                    </div>
                    <br/>
                    <div>
                        <a href="#" id="getDB" data-role="button">Submit</a>
                    </div>
                    <div id="dbChoice">
                    </div>
                </form>
                </div>
            </div>
            <div id="tables" class="selection_form">
                <div data-role="content">
                <h3>Device Options: </h3>
                <form action="" method="POST" id="" name="tablegroup">
                    <div data-role="fieldcontain" class="tbs" data-theme="e">
                        <fieldset data-role="controlgroup" id="tableF">
                            
                        </fieldset>
                    </div>
                    <br/>
                    <div>
                        <a href="" id="getTable" data-role="button" onclick="">Submit</a>
                    </div>
                </form>
                </div>
            </div>
            <!--The graph will go here-->
            <div id="graph_div" class="chart"></div>
        
        </div>
    
    </div>

    <div id="footer">

    	<div class="container">
        
        	<div class="footer_menu">
            	<ul id="fmenu">
                	<li><a href="http://129.187.150.139/Enersee/index.php">Home</a></li>
                    <li><a href="http://129.187.150.139/Enersee/about.php">About</a></li>
                    <li><a href="http://129.187.150.139/Enersee/report.php">Report</a></li>
                    <li><a href="http://129.187.150.139/Enersee/goals.php">Goals</a></li>
                    <li><a href="http://129.187.150.139/Enersee/downloads.php">Downloads</a></li>
                </ul>
            </div>
        
        	<div class="footer_images">
            	<div class="fimage">
                	<a href="http://www.cdtm.de"><img src="images/logo_cdtm_header.png" alt="CDTM logo" width="200" height="57"/></a>
                </div>
            	<div id="fimage">
                    <a href="http://www.tum.de"><img src="images/TUM.png" alt="TUM logo" width="200" height="57"/></a>
                </div>
            </div>
            
        
        </div>
    
    </div>
</body>
</html>