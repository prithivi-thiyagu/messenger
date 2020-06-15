<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />
<title>Insert title here</title>
<SCRIPT language="javascript">
var nameCnt = 1;
function addRow(tableID) {

	var table = document.getElementById(tableID);
    
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);

	var colCount = table.rows[0].cells.length;

	for(var i=0; i<colCount; i++) {

		var newcell	= row.insertCell(i);
	
		newcell.innerHTML = table.rows[1].cells[i].innerHTML;
		
		switch(newcell.childNodes[0].type) {
			case "text":
					nameCnt = nameCnt+1;
					newcell.childNodes[0].value = "";
					newcell.childNodes[0].name = newcell.childNodes[0].name+nameCnt;
					break;
			case "checkbox":
					newcell.childNodes[0].checked = false;
					break;
			case "select-one":
					newcell.childNodes[0].selectedIndex = 0;
					break;
		}
	}
}

function deleteRow(tableID) {
	try {
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length;

	for(var i=0; i<rowCount; i++) {
		var row = table.rows[i];
		var chkbox = row.cells[0].childNodes[0];
		if(null != chkbox && true == chkbox.checked) {
			if(rowCount <= 1) {
				alert("Cannot delete all the rows.");
				break;
			}
			table.deleteRow(i);
			rowCount--;
			i--;
		}


	}
	}catch(e) {
		alert(e);
	}
}


function validatePhone(phone) { //Validates the phone number
	if (document.sender.requireddate.value =="" )
	{
	alert("Please put your appointment date as DD/MM/YYYY");
	document.appointment.requireddate.focus();
	return false;
	}
    var phoneRegex = /^(\+91-|\+91|0)?\d{10}$/; // Change this regex based on requirement
    return phoneRegex.test(phone);
}


</SCRIPT>
<style>
.mytext {
    width: 300px;
}

    body{        
        padding-top: 60px;
        padding-bottom: 40px;
    }
    .fixed-header, .fixed-footer{
        width: 100%;
        position: fixed;        
        background: #333;
        padding: 10px 0;
        color: #fff;
    }
    .fixed-header{
        top: 0;
    }
    .fixed-footer{
        bottom: 0;
    }
    .container{
        width: 80%;
        margin: 0 auto; /* Center the DIV horizontally */
    }
        nav a{
        color: #fff;
        text-decoration: none;
        padding: 7px 25px;
        display: inline-block;
    }
    
    table {
  border-collapse: collapse;
  width: 100%;
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
  background-color: #42e3f5;
  color: black;
}
</style>
</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
if(session==null)
{
    out.println("<script>parent.location.href='index.jsp'</script>");
}
%>
<div class="fixed-header">
<div class="container">
            <nav>
                <a href="loginValidate">Add</a>
                <a href="viewUsers">View</a>
                <a href="#">Delete</a>
                <a href="logout">Logout</a>
                <a href="#">Contact Us</a>
            </nav>
        </div></div>
        
<form name="sender" action="home" method="post">

	<TABLE id="dataTable" width="350px" border="1">
	  <TR>
	  <TH>Select</TH>
    <TH>Phone Number</TH>
    <TH>Message</TH>
    <TH>Date To Wish</TH>
   
   </TR>
		<TR>
			<TD><INPUT type="checkbox" name="chk"/></TD>
			<TD><INPUT type="text" name="phone" class="mytext" /></TD>
			<TD><INPUT type="text" name=msg class="mytext"/></TD>
			<TD><INPUT type="text" name="date" class="mytext" placeholder="DD/MM/YYYY"/></TD>

		</TR>
	</TABLE>
<div id ="buttons_Val" align="center">
 	<img src="/images/add.jpg" alt="Add Button " onclick="addRow('dataTable')" height="150" width="150" onmouseover="Add"/>
    <img src="/images/delete.png" alt="Delete Button " onclick="deleteRow('dataTable')" height="145" width="227"/>
 	<input type="image" src="/images/submit.png" alt="Submit" width="110" height="120">
</div>

</form>
<div align="center" ><b>
<%Object msg = request.getAttribute("validMsg");

if(msg!=null)
{
out.print(msg.toString());
}
%>
</b></div>
 <div class="fixed-footer">
        <div class="container">Copyright &copy; 2020 Prithivi Greetings</div>        
    </div>
   </body>
</html>