// JavaScript Document
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
    		//mydata = data.dataSet;
			mydata = data;
			/*$.each(mydata, function(key, element){
				alert('key: ' + key + '\n' + 'value: ' + element);
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