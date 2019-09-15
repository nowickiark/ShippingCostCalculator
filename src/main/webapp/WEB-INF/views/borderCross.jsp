<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<spring:url value="/borderCross/add" var="addBorderCrossUrl" />

<spring:url value="/borderCross/add/{id}" var="updateBorderCrossUrl" />
<%--action="/extracost/add" modelAttribute="extracost"--%>
<form:form method="POST" action="${update? updateBorderCrossUrl : addBorderCrossUrl}" modelAttribute="borderCross" >
<%--<form:form method="POST" action="/borderCross/add" modelAttribute="borderCross">--%>
    <table>
        <tr ${update ? "" : "hidden"}>
            <td><form:label path="id">Id</form:label></td>
            <td><form:input path="id" readonly="true" /></td>
        </tr>
        <tr>
            <td><form:label path="dateOfBorderCrossing">Date of crossing the border: </form:label></td>
            <td><form:input type="date" path="dateOfBorderCrossing"/></td>
        </tr>
        <tr>
            <td><form:label path="borders">Border: </form:label></td>
            <td><form:select path="borders" >
                <c:forEach var="br" items="${listOfBorders}">
                    <form:option value="${br}">${br.countryFrom},${br.cityFrom}/${br.countryTo}, ${br.cityTo}</form:option>
                </c:forEach>
            </form:select>
            <a href="/borders/add">Add new border</a>
            </td>
        </tr>
        <td><a href="/bordersCross/add"><input type="submit" value="Add"/></a></td>
        </tr>

    </table>
</form:form>

<a href="expedition/listOfBorderCrosses">Go back to list of border crosses</a>

<br>

<a href="/">Go Back Home</a>

</body>
</html>