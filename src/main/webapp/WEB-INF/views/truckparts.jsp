<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<body>

<form:form method="POST" action="/truckparts/add" modelAttribute="truckparts">
    <table>
        <tr>
            <td><form:label path="id">Id</form:label></td>
            <td><form:input path="id" readonly="true "/></td>
        </tr>
        <tr>
            <td><form:label path="description">Description</form:label></td>
            <td><form:input path="description"/></td>
        </tr>
        <tr>
            <td><form:label path="cost">Cost</form:label></td>
            <td><form:input path="cost"/></td>
        </tr>
        <tr>
            <td><form:select path="currencyCode" >
            <form:option value="" label="Choose currency code"></form:option>
            <form:options items="${currencyCodeType}"/>
            </form:select>
        </tr>
        <tr>
            <td><form:label path="dateOfPurchase">Date of Purchase </form:label></td>
            <td><form:input type="date" path="dateOfPurchase"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Add truck part"/></td>
        </tr>
    </table>

</form:form>

<br>

<a href="/expedition/listOfTruckParts">Back to list</a>

<br>

<a href="/">Back to home</a>

</body>
