<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>

<html>
<body>
<h2>Add Driver</h2>

<spring:url value="/driver/add" var="addDriverUrl"/>

<spring:url value="/driver/update" var="updateDriverUrl"/>

<form:form method="POST" action="${update? updateDriverUrl : addDriverUrl}" modelAttribute="driver">
    <table>
        <tr ${update ? "" :"hidden"}>
            <td><form:label path="id">Id</form:label></td>
            <td><form:input path="id" readonly="${update}"/></td>
        </tr>
        <tr>
            <td><form:label path="firstName">First name</form:label></td>
            <td><form:input type="text" path="firstName"/></td>
        </tr>
        <tr>
            <td><form:label path="surname">Surname</form:label></td>
            <td><form:input type="text" path="surname"/></td>
        </tr>
        <tr>
            <td><form:label path="phoneNumber">Phone number</form:label></td>
            <td><form:input type="number" path="phoneNumber"/></td>
        </tr>
        <tr>
            <td><form:label path="personalIdNumber">Personal ID number</form:label></td>
            <td><form:input type="number" path="personalIdNumber"/></td>
        </tr>

        <tr>


            <td><input type="submit" value="AddDriver"/></td>
        </tr>
    </table>
</form:form>

<br>

<a href="/drivers"/>Go back to drivers</a>

</body>
</html>
