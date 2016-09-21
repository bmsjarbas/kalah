<%--
  Created by IntelliJ IDEA.
  User: js
  Date: 9/21/16
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/resources/style.css" var="style"/>
    <link href="${style}" rel="stylesheet"/>
    <title>Kalah</title>
</head>
<body>
<div class="background">
    <div id="main">
        <table class="gameBoard">
            <tr><td colspan="8">Top Player</td></tr>
            <tr>
                <td rowspan="2">0</td>
                <c:forEach var="pit" items="${game.getTopRow()}">
                    <td>
                        <c:out value="${pit}"></c:out>
                    </td>
                </c:forEach>
                <td rowspan="2">0</td>

            </tr>
            <tr>
                <c:forEach var="pit" items="${game.getBottomRow()}">
                    <td>
                        <c:out value="${pit}"></c:out>
                    </td>
                </c:forEach>
            </tr>
            <tr><td colspan="8">Bottom Player</td></tr>
        </table>
    </div>
</div>
</body>
</html>
