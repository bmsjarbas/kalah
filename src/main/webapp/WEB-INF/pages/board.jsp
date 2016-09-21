<%--
  Created by IntelliJ IDEA.
  User: js
  Date: 9/21/16
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kalah</title>
</head>
<body>
    <table>
        <tr>
            <c:forEach var="pit" items="${game.getTopRow()}">
                <td>
                    <c:out value="${pit}"></c:out>
                </td>
            </c:forEach>
        </tr>
        <tr>
            <c:forEach var="pit" items="${game.getBottomRow()}">
                <td>
                    <c:out value="${pit}"></c:out>
                </td>
            </c:forEach>
        </tr>
    </table>
</body>
</html>
