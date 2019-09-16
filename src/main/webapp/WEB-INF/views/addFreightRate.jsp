<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<form:form method="POST" action="/expedition/add" modelAttribute="freightRate">
    <table>
        <tr>
            <td><form:label path="id">Id</form:label></td>
            <td><form:input path="id" readonly="true" /></td>
        </tr>
        <tr>
            <td><form:label path="freightCompany">Freight Company</form:label></td>
            <td><form:input path="freightCompany"/></td>
        </tr>
        <tr>
            <td><form:label path="expedition">Expedition</form:label></td>
                <%--<td><form:input path="truck"/></td>--%>
            <td><form:select path="expedition" >
                <c:forEach var="ex" items="${expeditions}">
                    <form:option value="${ex}">${ex.driver} / ${ex.startDay}</form:option>
                </c:forEach>
            </td></form:select>
            <td><a href="/expedition/add">Add Expedition</a></td>
        </tr>
        <tr>
            <td><form:label path="amount">Amount</form:label></td>
            <td><form:input  path="amount"/></td>
        </tr>
        <tr>
            <td><form:label path="currencyCode" >Currency</form:label></td>
            <td><form:select path="currencyCode" >
                    <form:option value="" label="Choose currency code"></form:option>
                    <form:options items="${currencyCodeType}"/>
                </form:select>
        </tr>
        <tr>
            <td><form:label path="freightDistance">Freight rate distance</form:label></td>
            <td><form:input path="freightDistance"/></td>
        </tr>
        <tr>
            <td><form:label path="date"> Starting day</form:label></td>
            <td><form:input type="date" path="date"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Add"/></td>
        </tr>

    </table>
</form:form>

<a href="/spedytorHome">Go Back Home</a>

</body>
</html>