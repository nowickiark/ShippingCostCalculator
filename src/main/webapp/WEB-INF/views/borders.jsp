<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<body>

<form:form method="POST" action="/borders/add" modelAttribute="borders">
    <table>
        <tr>
            <td><form:label path="id">Id</form:label></td>
            <td><form:input path="id" readonly="true" /></td>
        </tr>
        <tr>
            <td><form:label path="countryFrom">Country of departure: </form:label></td>
            <td><form:input path="countryFrom"/></td>
        </tr>
        <tr>
            <td><form:label path="cityFrom">City of departure: </form:label></td>
            <td><form:input path="cityFrom"/></td>
        </tr>
        <tr>
            <td><form:label path="countryTo">Country of arrival: </form:label></td>
            <td><form:input path="countryTo"/> </td>
        </tr>
        <tr>
            <td><form:label path="cityTo">City of arrival: </form:label></td>
            <td><form:input path="cityTo"/> </td>
        </tr>

        <tr>
            <td><input type="submit" value="Add border"/></td>
        </tr>
    </table>
</form:form>



</body>
</html>