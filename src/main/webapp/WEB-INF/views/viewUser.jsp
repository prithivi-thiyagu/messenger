<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">


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
                <a href="addReceiver">Add</a>
                <a href="viewUsers">View</a>
                <a href="#">Delete</a>
               <a href="logout">Logout</a>
                <a href="#">Contact Us</a>
            </nav>
        </div></div>

	<TABLE id="dataTable" width="350px" border="1">
	  <TR>
    <TH>Phone Number</TH>
    <TH>Message</TH>
    <TH>Date To Wish</TH>
   
   </TR>
		
		<c:forEach var="message" items="${msgList}">
	<TR><TD>${message.receiverPhone }</TD>
		<TD>${message.msg }</TD>
		<TD>${message.date }</TD>
		</c:forEach>
		</TR>
		</TABLE>
 <div class="fixed-footer">
        <div class="container">Copyright &copy; 2020 Prithivi Greetings</div>        
    </div>
</body>
</html>