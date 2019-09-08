<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>
<body>
<h2>Add refueling</h2>
<form:form method="POST" action="/addfuel" modelAttribute="fuel">
    <table>
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
            <td><form:label path="currency" >Currency</form:label></td>
            <td><form:input path="currency"/></td>
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
<%--        </tr>--%>
<%--        <form:hidden path="fuel.id"></form:hidden>--%>
<%--        <tr>--%>
            <td><input type="submit" value="Add"/></td>
        </tr>

    </table>
</form:form>
</body>
</html>