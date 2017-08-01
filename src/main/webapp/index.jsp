<%@ page contentType="text/html;charset=utf-8" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*"%>
<%@ page import="gbook.*"%>
<html>
<head>
<title>Base</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<style type="text/css">
body,table,hr {
	color: black;
	background: silver;
	font-family: Verdana, sans-serif;
	font-size: x-small;
}
</style>
</head>
<body>
<center>
<table border=1>
	<tr>
		<th>AutorName</th>
		<th>TextMessage</th>
		<th>PublicationDate</th>
	</tr>
<P><jsp:useBean id="userbase" scope="session"
	class="gbook.BaseBean" />

<%
      ArrayList message =userbase.getMessage();
  	for (Iterator i = message.iterator(); i.hasNext();) {
			Message mes = (Message) i.next();
			%>
            <td width="200"><%=mes.getAutorName()%></td>
    	    <td width="200"><%=mes.getMessageDesc()%></td>
            <td width="200"><%=mes.getPublicationDate()%></td>
             </tr>
	<%
		}
	%>
</body>
</html>




