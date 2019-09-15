<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<body>
<h2>Add extra cost</h2>
<spring:url value="/extracost/add" var="addExtraCostUrl" />

<spring:url value="/extracost/add/{id}" var="updateExtraCostUrl" />
<form:form method="POST" action="${update? updateExtraCostUrl : addExtraCostUrl}" modelAttribute="extracost" >
            <table>
                <tr ${update ? "" : "hidden"}>
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
                    <td><form:label path="currencyCode">Currency</form:label></td>
                    <td><form:select path="currencyCode" >
                            <form:option value="" label="Choose currency code"></form:option>
                            <form:options items="${currencyCodeType}"/>
                        </form:select>
                </tr>
                <tr>
                    <td><form:label path="dateOfPurchase">Date of Purchase</form:label></td>
                    <td><form:input type="date" path="dateOfPurchase"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Add cost"/></td>
                </tr>
        </table>

</form:form>

<a href="/expedition/listOfExtraCosts">Go back to list of extra costs</a>

<br>

<a href="/">Back to home</a>

</body>
</html>