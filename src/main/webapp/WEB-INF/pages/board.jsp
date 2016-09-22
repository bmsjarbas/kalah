<%--
  Created by IntelliJ IDEA.
  User: js
  Date: 9/21/16
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/resources/style.css" var="style"/>
    <link href="${style}" rel="stylesheet"/>
    <title>Kalah</title>
</head>
<body>
<spring:url value="/game" var="gameActionUrl" />
<div class="background">
    <div id="main">
        <form:form action="${gameActionUrl}" method="put">
            <table id="gameBoard" class="gameBoard">
                <tbody>
                <tr>
                    <td colspan="8">${game.getTopRowPlayerName()}</td>
                </tr>
                <tr id="top-row" ${ !game.isTopRowEnabled() ? 'class="row-disabled"' : ''}>
                    <td rowspan="2">${game.getTopPlayerStore()}</td>
                    <c:forEach var="pit" items="${game.getTopRow()}" varStatus="loop">
                        <td>
                            <input type="radio" ${ !game.isTopRowEnabled() || pit eq 0 ? 'disabled="disabled"' : ''}name="pits" value="${loop.index}"/><br><br>
                            <c:out value="${pit}"></c:out>
                        </td>
                    </c:forEach>

                    <td rowspan="2">${game.getBottomPlayerStore()}</td>
                </tr>
                <tr id="bottom-row" ${ !game.isBottomRowEnabled() ? 'class="row-disabled"' : ''}>
                    <c:forEach var="pit" items="${game.getBottomRow()}" varStatus="loop">
                        <td>
                            <input type="radio" ${ !game.isBottomRowEnabled() || pit eq 0 ? 'disabled="disabled"' : ''} name="pits" value="${loop.index}"/><br><br>
                            <c:out value="${pit}"></c:out>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td colspan="8">${game.getBottomRowPlayerName()}</td>
                </tr>
                </tbody>
            </table>
            <div style="text-align: center">
                <input type="submit" id="move" class="btn" value="Do">
            </div>
        </form:form>

    </div>
</div>

<script src="https://code.jquery.com/jquery-3.1.0.min.js"
        integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>

</body>
</html>
