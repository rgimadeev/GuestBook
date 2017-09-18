<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*" %>
<%@ page import="gbook.*" %>
<html>
<head>
    <title>Гостевая книга</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="GeneralStyleForPages.css">
</head>
<body class="form_background">
<h><p class="h">Добавление нового сообщения:</p></h>
<br>
<form action="new-message" method="POST">
    <table>
        <tr>
            <td>Автор:</td>
            <td><input type="text" size="25" name="authorName" value="${authorNameText}"/></td>
            <td class="error_text"><p>${errorText.author_text_error}</p>
            </td>
        </tr>
        <tr>
            <td>Текст сообщения:</td>
            <td><textarea name="messageDesc" style="width: 450px" rows="10" placeholder="${messageText}"></textarea>
            </td>
            <td class="error_text"><p>${errorText.message_text_error}</p>
                <p>${errorText.max_kol_message_text_error}</p>
            </td>
        </tr>
    </table>
    <br>
    <a href="messages">Переход к списку сообщений</a>
    <input class="button" type="submit" value="Сохранить" name="Save">
</form>
</body>
</html>