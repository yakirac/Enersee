<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Enersee Report</title>
<!--Load the AJAX API-->
	<script type="text/javascript" src="jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="enersee.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
		//getDatabases();
		google.load("visualization", "1", {packages:["corechart"]});
		
		// Load the Visualization API and the piechart package.
				
				  //google.setOnLoadCallback(drawChart);
				  function drawChart() {
					var data = new google.visualization.DataTable();
					data.addColumn('string', 'Year');
					data.addColumn('number', 'Sales');
					data.addColumn('number', 'Expenses');
					data.addRows(4);
					data.setValue(0, 0, '2004');
					data.setValue(0, 1, 1000);
					data.setValue(0, 2, 400);
					data.setValue(1, 0, '2005');
					data.setValue(1, 1, 1170);
					data.setValue(1, 2, 460);
					data.setValue(2, 0, '2006');
					data.setValue(2, 1, 860);
					data.setValue(2, 2, 580);
					data.setValue(3, 0, '2007');
					data.setValue(3, 1, 1030);
					data.setValue(3, 2, 540);
			
					var chart = new google.visualization.LineChart(document.getElementById('graph_div'));
					chart.draw(data, {width: 400, height: 240, title: 'Company Performance'});
				  }
	
		$(document).ready(function () {
			$('#getDB').click (function(evt) {
				evt.preventDefault();
				getDbValue();
			});
			$('#getTable').click (function(evt) {
				evt.preventDefault();
				var ds = getTableValue();
				drawChart();
				$('#graph_div').append("This is the data: " + ds);
			});
		});
    
    
	</script>
</head>

<body onload="getDatabases()">
	
	<div id="databases">
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
    <div id="tables">
    	<div data-role="content">
		<h3>Table Options: </h3>
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
    <div id="graph_div"></div>
</body>
</html>