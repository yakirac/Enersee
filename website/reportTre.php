<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>EnerSee</title>
    <link href="style.css" rel="stylesheet" type="text/css" />
    
    <!--Load the AJAX API-->
	<script type="text/javascript" src="jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="enersee.js"></script>
    <!--<script type="text/javascript" src="https://www.google.com/jsapi"></script>-->
    <script src="raphael.js"></script>
    <script src="g.raphael.js"></script>
   	<script src="g.dot.js"></script>
   	<script src="g.line.js"></script>

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