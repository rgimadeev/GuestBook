<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*"%>
<%@ page import="gbook.*"%>
<html>
<head>
<title>Гостевая книга</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="style2.css">
</head>
<body class="body">
<h><p class="h">Добавление нового сообщения:</p></h><br>
 <form action="new-message" method="POST">
            <table class="table">
                <tr>
                    <td>Автор:</td><td><input type="text" size="25" name="autorName" value="${autorNameTxt}"/></td>
                    <td class="mes"><p>${errorTxt.aut_err}</p>
                    </td>
                </tr>
                <tr>
                    <td>Текст сообщения:</td><td><textarea name="messageDesc" style="width: 450px" rows="10" placeholder="${messageTxt}"></textarea></td>
                    <td class="mes"><p>${errorTxt.text_err}</p>
                     <p>${errorTxt.max_lt}</p>
                   </td>
                </tr>
              </table>
            <br>
            <a  href="messages">Переход к списку сообщений</a>
                <input class="superbutton" type="submit" value="Сохранить" name="Save">
    </form>
</body>
</html>