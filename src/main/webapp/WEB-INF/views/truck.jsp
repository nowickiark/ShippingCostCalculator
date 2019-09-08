<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<body>

<form:form method="POST" action="/truck/add" modelAttribute="truck">
    <table>
        <tr>
            <td><form:label path="id">Id</form:label></td>
            <td><form:input path="id" readonly="true" /></td>
        </tr>
        <tr>
            <td><form:label path="plateNumber">Truck Plate Number</form:label></td>
            <td><form:input path="plateNumber"/></td>
        </tr>
        <tr>
            <td><form:label path="brand">Brand</form:label></td>
            <td><form:input path="brand"/></td>
        </tr>
        <tr>
            <td><form:label path="model">Model</form:label></td>
            <td><form:input path="model"/></td>
        </tr>
        <tr>
            <td><form:label path="horsePower">Horse Power</form:label></td>
            <td><form:input path="horsePower"/></td>
        </tr>

        <tr>
            <td><input type="submit" value="AddTruck"/></td>
        </tr>
    </table>
</form:form>



</body>
</html>