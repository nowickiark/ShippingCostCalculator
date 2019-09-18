<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<body>

<form:form method="POST" action="/expedition/add" modelAttribute="expedition">
    <table>
        <tr>
            <td><form:label path="id">Id</form:label></td>
            <td><form:input path="id" readonly="true" /></td>
        </tr>
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
            <td><a href="/truck/add">Add truck</a></td>
        </tr>
        <tr>
            <td><form:label path="startOdometerReading">Start Odometer Reading</form:label></td>
            <td><form:input  path="startOdometerReading"/></td>
        </tr>
        <tr>
            <td><form:label path="startDay"> Starting day</form:label></td>
            <td><form:input type="date" path="startDay"/></td>
        </tr>
        <tr>
            <td><form:label path="cashBeginingZl">Amount of cash in zloty</form:label></td>
            <td><form:input path="cashBeginingZl"/></td>
        </tr>
        <tr>
            <td><form:label path="cashBeginingEur">Amount of cash in euro</form:label></td>
            <td><form:input path="cashBeginingEur"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Add"/></td>
        </tr>

    </table>
</form:form>

<a href="/">Go Back Home</a>

</body>
</html>