<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<form:form method="POST" action="/expedition/Add" modelAttribute="expedition">
    <table>
        <tr>
            <td><form:label path="startingPlace">Starting Place</form:label></td>
            <td><form:input path="startingPlace"/></td>
        </tr>
        <tr>
            <td><form:label path="truck">Truck</form:label></td>
            <%--<td><form:input path="truck"/></td>--%>
            <td><form:select path="truck" >
                    <c:forEach var="tr" items="${trucks}">
                        <form:option value="${tr}">${tr.plateNumber}</form:option>
                    </c:forEach>
            </td></form:select>
        </tr>
        <tr>
            <td><form:label path="startOdometerReading">Start Odometer Reading</form:label></td>
            <td><form:input path="startOdometerReading"/></td>
        </tr>
        <tr>
            <td><form:label path="startDay"> Starting day</form:label></td>
            <td><form:input path="startDay"/></td>
        </tr>
        <tr>
            <td><form:label path="CashBeginingZl">Amount of cash in zloty</form:label></td>
            <td><form:input path="CashBeginingZl"/></td>
        </tr>
        <tr>
            <td><form:label path="CashBeginingEur">Amount of cash in euro</form:label></td>
            <td><form:input path="CashBeginingEur"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Add"/></td>
        </tr>

    </table>
</form:form>

</body>
</html>