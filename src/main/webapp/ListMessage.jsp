<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*"%>
<%@ page import="gbook.*"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Гостевая книга</title>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"">
<style type="text/css">
body {
	color:black ;
	background: silver url(images/bg.jpg);
	font-family: Calligraph, Regular;
	font-size:x-large;
}
.autor {
color: black;
	background: silver url(images/bg.jpg);
	font-family:Calligraph, sans-serif;
	font-size:x-medium;

}
.pub_date {
color: black;
	background: silver url(images/bg.jpg);
	font-family:Calligraph, sans-serif;
	font-size:x-medium;
	text-align: right;
}
.message_text{
color: black;
	background: silver (images/bg.jpg);;
	font-family: Cambria, sans-serif;
	font-size:x-medium;
	text-align: left;
}
</style>
</head>
<body>
<h><center><p style="color:#0000FF">ГОСТЕВАЯ КНИГА:</center></h></p>
<h><a name="top">Список сообщений:</a></h><br>
<p><font size="5" color="#00ff00" face="Calligraph">${message}<br></font></p>


<a href="new-message">Добавление нового сообщения</a><br>

      <c:forEach var="mes" items="${messageList}">
      <p class="autor">
          <td>Автор: ${mes.getAutorName()}</td><br>
      </p>
      <p class="message_text"
          <td>${mes.getMessageDesc()}</td>
      </p>
      <p class="pub_date">
          <td>Дата публикации:  <fmt:formatDate pattern="dd.MM.YYYY HH:mm" value="${mes.getPublicationDate()}" /></td><br>
          <td><a href="#top">Наверх</a></td><br>
          <td>--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</td>
        </p><br>

      </c:forEach>

</body>
</html>





