<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*"%>
<%@ page import="gbook.*"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Base</title>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"">
<style type="text/css">
body {
	color: black;
	background: silver;
	font-family: Verdana, fantasy;
	font-size:large;
}
table {
color: black;
	background: silver;
	font-family: Verdana, sans-serif;
	font-size:medium;
}
</style>
</head>
<body>
<h><a name="top">Список сообщений:</a></h><br>
<p style="color:#00ff00"><c:out value="${requestScope.message}"/><br></p>
<a href="new-message">Добавление нового сообщения</a>
 <table>
      <c:forEach var="mes" items="${messageList}">
        <tr>
          <td>Автор: ${mes.getAutorName()}&nbsp;&nbsp;</td>
          <td>Текст сообщения: ${mes.getMessageDesc()}</td>
          <td><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Дата публикации: ${mes.getPublicationDate()} </td>
          <td><br><br><br><br> <p><a href="#top">Наверх</a></p></td>
        </tr>
      </c:forEach>
    </table>
</body>
</html>





