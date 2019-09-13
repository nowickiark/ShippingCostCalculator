<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<body>

<form:form method="POST" action="/extracost/add" modelAttribute="extracost">
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
                    <td><form:label path="currency">Currency</form:label></td>
                    <td><form:input path="currency"/></td>
                </tr>
                <tr>
                    <td><form:label path="dateOfPurchase">Date of Purchase</form:label></td>
                    <td><form:input path="dateOfPurchase"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Add cost"/></td>
                </tr>
        </table>

</form:form>

<br>

<a href="/extracost/add">Add Extra cost</a>

<br>

<a href="/">Back to home</a>

</body>
</html>