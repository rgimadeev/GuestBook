<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*"%>
<%@ page import="gbook.*"%>
<html>
<head>
<title>Base</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
body {
	color: black;
	background: silver;
	font-family: Verdana, sans-serif;
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
<h><p>Добавление нового сообщения:</p></h><br>
<form action="<c:url value="/new-message"/>" method="POST">
            <table>
                <tr>
                    <td>Автор:</td><td><input type="text" size="25" name="autorName" /></td>
                </tr>
                <tr>
                    <td>Текст сообщения:</td><td><textarea name="messageDesc" style="width: 500px" rows="10"></textarea></td>

                </tr>
            </table>
            <br>
            &nbsp;&nbsp;&nbsp;<a  href="messages">Переход к списку сообщений</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" value="Сохранить" name="Save">
                <input type="reset" value="Очистить">


</body>
</html>