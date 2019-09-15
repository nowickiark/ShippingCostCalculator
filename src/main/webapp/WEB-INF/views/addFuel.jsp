<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>
<body>
<h2>Add refueling</h2>

<spring:url value="/addfuel" var="addFuelingUrl" />

<spring:url value="/updatefuel" var="updateFuelingUrl" />

<form:form method="POST" action="${update? updateFuelingUrl : addFuelingUrl}" modelAttribute="fuel">
    <table>

        <tr ${update ? "" : "hidden"}>
            <td><form:label path="id" >Id</form:label></td>
            <td><form:input path="id" readonly="${update}"/></td>
        </tr>


        <tr>
            <td><form:label path="liters" >Liters</form:label></td>
            <td><form:input type="number" path="liters"/></td>
        </tr>
        <tr>
            <td><form:label path="placeOfRefueling">Place of refueling</form:label></td>
            <td><form:input path="placeOfRefueling"/></td>
        </tr>
        <tr>
            <td><form:label path="cost">Costs</form:label></td>
            <td><form:input type="number" path="cost"/></td>
        </tr>
        <tr>
            <td><form:label path="currencyCode" >Currency</form:label></td>
            <td><form:select path="currencyCode" >
                <form:option value="" label="Choose currency code"></form:option>
                <form:options items="${currencyCodeType}"/>
                </form:select>
        </tr>
        <tr>
            <td><form:label path="kilometers" >Kilometers</form:label></td>
            <td><form:input type="number" path="kilometers"/></td>
        </tr>
        <tr>
            <td><form:label path="paymentMethod" >Payment method</form:label></td>
            <td><form:input path="paymentMethod"/></td>
        </tr>
        <tr>
            <td><form:label path="refuelingToFull" >Full</form:label></td>
            <td><form:checkbox path="refuelingToFull"/></td>
        </tr>
        <tr>
<%--            <td><form:label path="expedition" >Expedition</form:label></td>--%>
            <td><form hidden:input path="expedition" readonly="true"/></td>
        </tr>
        <tr>
            <td><form:label path="dateOfFueling">Date of Fueling</form:label></td>
            <td><form:input type="date" path="dateOfFueling"/></td>
        </tr>

            <td><input type="submit" value="Add"/></td>
        </tr>

    </table>
</form:form>
<a href="/expedition/listOfFuels"/>Go back to fuelings</a>

<br>

<a href="/">Go Back Home</a>

</body>
</html>