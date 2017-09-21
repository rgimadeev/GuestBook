<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*" %>
<%@ page import="gbook.*" %>
<html>
<head>
    <title>Гостевая книга</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js" type="text/javascript"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
    <script src="checkMessage.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="GeneralStyleForPages.css">
</head>
<body class="body">
<h class="h"><p>Добавление нового сообщения:</p></h>
<br>
<form id="MessageForm">
    <table class="table">
        <tr>
            <td>Автор:</td>
            <td><input type="text" size="25" id="authorName" name="authorName"/></td>
            <td class="error_text">
                <div id="author_text_error" class="errors"></div>
            </td>
        </tr>
        <tr>
            <td>Текст сообщения:</td>
            <td><textarea name="messageText" id="messageText" style="width: 450px" rows="10"></textarea></td>
            <td class="error_text">
                <div id="message_text_error" class="errors"></div>
                <div id="max_kol_message_text_error" class="errors"></div>
            </td>

        </tr>
    </table>
    <br>
    <a href="messages">Переход к списку сообщений</a>
    <input class="save_button" id="submitButton" type="submit" value="Сохранить">
    <input class="clear_button" type="reset" value="Очистить">
</form>
</body>
</html>