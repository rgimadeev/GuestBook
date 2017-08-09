<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*"%>
<%@ page import="gbook.*"%>
<html>
<head>
<title>Гостевая книга</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<style type="text/css">

body {
color:#00dddd;
	background: silver url(images/bg2.png);
	font-family: Calligraph, sans-serif;
	font-size:x-large;
}
table {
color: black;
	background: silver url(images/bg2.png);
	font-family: Calligraph, sans-serif;
	font-size:large;
}
div.d{
charset:text/html charset=utf-8

}
td.mes {
color:red;
font-family: Cambria, sans-serif;
font-size:medium;
}
.superbutton {
width:90px;
height:30px;
border-radius:30px;
background:#459DE5;
color:#fff;
font-size:15px;
cursor:pointer;
}
.superbutton:hover{
background:#358DE5;
}
. superbutton:focus{
outline:none;
}
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js" type="text/javascript"></script>
 <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
<script src="checkMessage.js" type="text/javascript" ></script>
</head>
<body>
<h><p style="color:#0000FF">Добавление нового сообщения:</p></h><br>
 <form id="MessageForm">
            <table class="table">
                <tr>
                    <td>Автор:</td><td><input type="text" size="25" id="autorName" name="autorName"/></td>
                    <td class="mes"><div class="d" id="autorErr"></div>
                    </td>
                </tr>
                <tr>
                    <td>Текст сообщения:</td><td><textarea name="messageDesc" id="messageDesc"  style="width: 450px" rows="10"></textarea></td>
                    <td class="mes"><div id="TextErr"></div></td>
                </tr>
              </table>
            <br>
            &nbsp;&nbsp;&nbsp;<a  href="messages">Переход к списку сообщений</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="superbutton" id="submitButton" type="submit" value="Сохранить" >
    </form>
</body>
</html>