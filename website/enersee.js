// JavaScript Document

$(document).ready(function () {
	$('#getDB').click (function(evt) {
		evt.preventDefault();
		getDbValue();
	});
	$('#getTable').click (function(evt) {
		$('#graph_div').empty();
		evt.preventDefault();
		var ds = getTableValue();
		
		var days = getTheDays(ds);
		var times = getTheTimes(ds);
		var powers = getThePower(ds);
		//for( var d in ds){
		//	rows++;
		//}
		//alert("Number of rows = " + rows);
		/*$.each(ds, function(key, element){
			alert('key: ' + key + '\n' + 'time: ' + element.time + '\ndate: ' + element.date + '\npower: ' + 									element.power);
		});*/
		//drawChart(ds);
		//$('#graph_div').append("We are getting the data: " + ds);
		var r = Raphael("graph_div"),
                  xs = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 
                  19, 20, 21, 22, 23, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 
                  16, 17, 18, 19, 20, 21, 22, 23, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 
                  13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
                  10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 0, 1, 2, 3, 4, 5, 
                  6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 0, 1, 
                  2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 
                  23, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 
                  21, 22, 23],
                  ys = [7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 
                  6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 5, 5, 
                  5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 4, 4, 4, 
                  4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 
                  3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 
                  2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
                  1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                  data = [294, 300, 204, 255, 348, 383, 334, 217, 114, 33, 44, 26, 41, 39, 52, 
                  17, 13, 2, 0, 2, 5, 6, 64, 153, 294, 313, 195, 280, 365, 392, 340, 184, 87, 
                  35, 43, 55, 53, 79, 49, 19, 6, 1, 0, 1, 1, 10, 50, 181, 246, 246, 220, 249, 
                  355, 373, 332, 233, 85, 54, 28, 33, 45, 72, 54, 28, 5, 5, 0, 1, 2, 3, 58, 167, 
                  206, 245, 194, 207, 334, 290, 261, 160, 61, 28, 11, 26, 33, 46, 36, 5, 6, 0, 0, 
                  0, 0, 0, 0, 9, 9, 10, 7, 10, 14, 3, 3, 7, 0, 3, 4, 4, 6, 28, 24, 3, 5, 0, 0, 0, 
                  0, 0, 0, 4, 3, 4, 4, 3, 4, 13, 10, 7, 2, 3, 6, 1, 9, 33, 32, 6, 2, 1, 3, 0, 0, 
                  4, 40, 128, 212, 263, 202, 248, 307, 306, 284, 222, 79, 39, 26, 33, 40, 61, 54, 
                  17, 3, 0, 0, 0, 3, 7, 70, 199],
                  axisy = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
                  axisx = ["12am", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", 
                  "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"];
                  
                  
                  /*r.dotchart(10, 10, 620, 260, times, days, powers, {symbol: "o", max: 10, heat: true, 
                  axis: "0 0 1 1", axisxstep: 23, axisystep: 6, axisxlabels: axisx, axisxtype: " ", 
                  axisytype: " ", axisylabels: axisy}).hover(function () {
                  this.marker = this.marker || r.tag(this.x, this.y, this.value, 0, this.r + 2).insertBefore(this);
                  this.marker.show();
                  }, function () {
                     this.marker && this.marker.hide();
                  });*/
                  
                  var lines = r.linechart(330, 10, 300, 220, [times], [powers], 
                  { nostroke: false, axis: "0 0 1 1", axisxstep: 20, axisystep: 10, minx: 0, maxx: 20, symbol: "circle", smooth: true }).hoverColumn(function () {
                    this.tags = r.set();

                    for (var i = 0, ii = this.y.length; i < ii; i++) {
                        this.tags.push(r.tag(this.x, this.y[i], this.values[i], 160, 10).insertBefore(this).attr([{ fill: "#fff" }, 
                        { fill: this.symbols[i].attr("fill") }]));
                    }
                }, function () {
                    this.tags && this.tags.remove();
                });
                lines.symbols.attr({ r: 6 });
	});
});

var selectedD;
var selectedT;
var data;
var tableCounter = 0;
function getDatabases () {
	var url = "http://129.187.150.139/Enersee/showDatabases.php";
	$.getJSON(url, function(data) {
		var dbs = data.databases;
		var form = document.getElementById("databaseF");
		for (var obj in dbs){
			var b = dbs[obj];
			var db = document.createElement("input");
			db.setAttribute("type", "radio");
			db.setAttribute("name", "database");
			db.setAttribute("value", b.dname);
			form.appendChild(db);
			var ilabel = document.createElement("label");
			ilabel.setAttribute("for", b.dname);
			ilabel.innerHTML = b.dname;
			form.appendChild(ilabel);
			var brk = document.createElement("br");
			form.appendChild(brk);
		}
	});
}


function getDbValue(){
	for (var i = 0; i < document.dgroup.database.length; i++){
		if(document.dgroup.database[i].checked){
			selectedD = document.dgroup.database[i].value;
			//document.getElementById("dbChoice").innerHTML = selectedD;
			//alert("In get db values " + selectedD);
		}
	}
	
	setTableValues(selectedD);
	
}
function getTableValue(){
	if(tableCounter == 1){
		selectedT = document.tablegroup.elements["table"].value;
		//alert("length is 1. table value " + selectedT);
	}else {
		for (var i = 0; i < document.tablegroup.table.length; i++){
			if(document.tablegroup.table[i].checked){
				selectedT = document.tablegroup.table[i].value;
				//document.getElementById("dbChoice").innerHTML = selectedD;
				//alert("In get table values " + selectedT);
			}
		}
	}
	
	var dataS = setDataValues(selectedD, selectedT);
	
	return dataS;
	
}



function setTableValues(selected){
	var durl = "http://129.187.150.139/Enersee/showTables.php?dbname=" + selected;
	//alert("before json funtion " + selected);
	document.getElementById("tableF").innerHTML="";
	$.getJSON(durl, function (data) {
		//alert("In json function in set tables");
		var tbs = data.tables;
		var form = document.getElementById("tableF");
		for (var obj in tbs){
			var t = tbs[obj];
			var tb = document.createElement("input");
			tb.setAttribute("type", "radio");
			tb.setAttribute("name", "table");
			//var tn = t.tname;
			//alert("Table name is: " + tn);
			tb.setAttribute("value", t.tname);
			form.appendChild(tb);
			var ilabel = document.createElement("label");
			ilabel.setAttribute("for", t.tname);
			ilabel.innerHTML = t.tname;
			form.appendChild(ilabel);
			var brk = document.createElement("br");
			form.appendChild(brk);
			tableCounter++;
		}

	});
}

function setDataValues(db, table){
	//alert("Database value is: " + db + "\n Table value is: " + table);
	var durl = "http://129.187.150.139/Enersee/getData.php?dbname=" + db + "&tname=" + table ;
	//alert("before json funtion " + selected);
	//document.getElementById("tableF").innerHTML="";
	var jdata;
	/*$.getJSON(durl, function (data) {
		jdata = data.dataSet;
		/*for( var d in jdata){
			var nd = jdata[d];
			var div = document.getElementById("graph_div");
			var p = document.createElement("p");
			p.innerHTML = nd;
			div.appendChild(p);
		}*
	});*/
	//var mydata = [];
	var mydata;
	$.ajax({
 		url: durl,
 		async: false,
  		dataType: 'json',
  		success: function (data) {
    		mydata = data.dataSet;
			//mydata = data;
			/*$.each(mydata, function(key, element){
				alert('key: ' + key + '\n' + 'time: ' + element.time + '\ndate: ' + element.date + '\npower: ' + element.power);
			});*/
  		}
	});

	//alert("Outside of ajax: " + mydata); // has value of data from suceess
	
	return mydata;
}

function getD(){
	return selectedD;
}

function getT(){
	return selectedT;
}

function checkNumbers(theNum){
	var num = theNum;
	if(theNum < 10){
		num = '0' + theNum;
	}
	return num;
}

function checkTime(theTime){
	var patt = new RegExp(":");
	var rf = patt.test(theTime);
	var hours;
	var minutes;
	var seconds;
	if(rf == false){
		var d = new Date();
		d.setTime(theTime);
		hours = checkNumbers(d.getHours());
		minutes = checkNumbers(d.getMinutes());
		seconds = checkNumbers(d.getSeconds());
		theTime = '' + hours + ':' + minutes + ':' + seconds + ''; 
	}
	var tnumber;
	if(theTime > "00:00:00" && theTime < "01:00:00"){
			tnumber = 0;
			//System.out.println("In 0-1");
		}
		if(theTime > "01:00:00" && theTime < "02:00:00"){
			tnumber = 1;
			//System.out.println("In 1-2");
		}
		if(theTime > "02:00:00" && theTime < "03:00:00"){
			tnumber = 2;
			//System.out.println("In 2-3");
		}
		if(theTime > "03:00:00" && theTime < "04:00:00"){
			tnumber = 3;
			//System.out.println("In 3-4");
		}
		if(theTime > "04:00:00" && theTime < "05:00:00"){
			tnumber = 4;
			//System.out.println("In 4-5");
		}
		if(theTime > "05:00:00" && theTime < "06:00:00"){
			tnumber = 5;
			//System.out.println("In 5-6");
		}
		if(theTime > "06:00:00" && theTime < "07:00:00"){
			tnumber = 6;
			//System.out.println("In 6-7");
		}
		if(theTime > "07:00:00" && theTime < "08:00:00"){
			tnumber = 7;
			//System.out.println("In 7-8");
		}
		if(theTime > "08:00:00" && theTime < "09:00:00"){
			tnumber = 8;
			//System.out.println("In 8-9");
		}
		if(theTime > "09:00:00" && theTime < "10:00:00"){
			tnumber = 9;
			//System.out.println("In 9-10");
		}
		if(theTime > "10:00:00" && theTime < "11:00:00"){
			tnumber = 10;
			//System.out.println("In 10-11");
		}
		if(theTime > "11:00:00" && theTime < "12:00:00"){
			tnumber = 11;
			//System.out.println("In 11-12");
		}
		if(theTime > "12:00:00" && theTime < "13:00:00"){
			tnumber = 12;
			//System.out.println("In 12-13");
		}
		if(theTime > "13:00:00" && theTime < "14:00:00"){
			tnumber = 13;
			//System.out.println("In 13-14");
		}
		if(theTime > "14:00:00" && theTime < "15:00:00"){
			tnumber = 14;
			//System.out.println("In 14-15");
		}
		if(theTime > "15:00:00" && theTime < "16:00:00"){
			tnumber = 15;
			//System.out.println("In 15-16");
		}
		if(theTime > "16:00:00" && theTime < "17:00:00"){
			tnumber = 16;
			//System.out.println("In 16-17");
		}
		if(theTime > "17:00:00" && theTime < "18:00:00"){
			tnumber = 17;
			//System.out.println("In 17-18");
		}
		if(theTime > "18:00:00" && theTime < "19:00:00"){
			tnumber = 18;
			//System.out.println("In 18-19");
		}
		if(theTime > "19:00:00" && theTime < "20:00:00"){
			tnumber = 19;
			//System.out.println("In 19-20");
		}
		if(theTime > "20:00:00" && theTime < "21:00:00"){
			tnumber = 20;
			//System.out.println("In 20-21");
		}
		if(theTime > "21:00:00" && theTime < "22:00:00"){
			tnumber = 21;
			//System.out.println("In 21-22");
		}
		if(theTime > "22:00:00" && theTime < "23:00:00"){
			tnumber = 22;
			//System.out.println("In 22-23");
		}
		if(theTime > "23:00:00" && theTime < "00:00:00"){
			tnumber = 23;
			//System.out.println("In 23-0");
		}
		
		return tnumber;
}

function getTheDays(tData){
	var days = [];
	
	$.each(tData, function(key, element){
		var theDate = element.date;
		var dt = new Date();
		dt.setFullYear(theDate.substring(0, 4), theDate.substring(5, 7), theDate.substring(8));
		//alert('The day is: ' + dt.getDay());
		days[key] = dt.getDay();
	});
	
	return days;
}

function getTheTimes(tData){
	var times = [];
	
	$.each(tData, function(key, element){
		var t = checkTime(element.time);
			
		//alert('The original time is: ' + element.time + '\nThe new time is: ' + t);
		//alert('The original time is: ' + element.time + '\nThe new time is: ' + t);
		if(key == 0){
			times[key] = 0;
		}else {
			times[key] = t;
		}
		
	});
	
	return times;
}

function getThePower(tData){
	var pvalues = [];
	
	$.each(tData, function(key, element){
		//alert('The power is: ' + element.power);
		if(key == 0){
			pvalues[key] = 0;
		}else {
			pvalues[key] = element.power;
		}
	});
	
	return pvalues;
}