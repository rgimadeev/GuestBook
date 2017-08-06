<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*"%>
<%@ page import="gbook.*"%>
<html>
<head>
<title>Base</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"">
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
</head>
<body>
<h><p style="color:#0000FF">Добавление нового сообщения:</p></h><br>
 <form action="new-message" method="POST">
            <table class="table">
                <tr>
                    <td>Автор:</td><td><input type="text" size="25" name="autorName" value="${autorNameTxt}"/></td><td class="mes"><c:out value="${requestScope.autorMes}"/></td>
                </tr>
                <tr>
                    <td>Текст сообщения:</td><td><textarea name="messageDesc" style="width: 450px" rows="10" placeholder="${messageTxt}"></textarea></td><td class="mes"><c:out value="${requestScope.textMes}"/><c:out value="${requestScope.maxMes}"/></td>
                </tr>
              </table>
            <br>
            &nbsp;&nbsp;&nbsp;<a  href="messages">Переход к списку сообщений</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="superbutton" type="submit" value="Сохранить" name="Save">
                <input class="superbutton" type="reset" value="Очистить">
    </form>

</body>
</html>