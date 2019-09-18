<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<body>
<div>

    <table>

        <tr>
            <th>Id: </th>
            <th>Country of departure: </th>
            <th>City of departure: </th>
            <th>Country of arrival: </th>
            <th>City of arrival: </th>
        </tr>

        <c:forEach var="border" items="${listOfBorders}">
            <tr>
                <td>${border.id}</td>
                <td>${border.countryFrom}</td>
                <td>${border.cityFrom}</td>
                <td>${border.countryTo}</td>
                <td>${border.cityTo}</td>
                <td><a href="/borders/add/${border.id}">Edit</a></td>

        </c:forEach>

    </table>

    <a href="/borders/add">Add border</a>

</div>

</body>
</html>