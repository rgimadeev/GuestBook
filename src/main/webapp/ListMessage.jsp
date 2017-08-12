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
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body class="body">
<h class="h1"><p>ГОСТЕВАЯ КНИГА:</p></h>
<h class="h2"><a name="top">Список сообщений:</a></h><br>
<p class="p"><c:if test="${param.sendMes==1}">
    <c:out value="Сообщение было отправлено"/>
</c:if><br></p>


<a href="new-message">Добавление нового сообщения</a><br>

      <c:forEach var="mes" items="${messageList}">
      <p class="autor">
          <td>Автор: ${mes.getAutorName()}</td><br>
      </p>
      <p class="message_text">
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





