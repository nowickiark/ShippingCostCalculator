<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<body>
<div>

    <table>

        <tr>
            <th>Id</th>
            <th>Truck Plate Number</th>
            <th>Brand</th>
            <th>Model</th>
            <th>Horse Power</th>
            <th>Edit</th>
        </tr>

        <c:forEach var="truck" items="${trucks}">
            <tr>
                <td>${truck.id}</td>
                <td>${truck.plateNumber}</td>
                <td>${truck.brand}</td>
                <td>${truck.model}</td>
                <td>${truck.horsePower}</td>
                <td><a href="/truck/add/${truck.id}">Truck Details</a></td>
            </tr>
        </c:forEach>

    </table>

        <a href="/truck/add">Add Truck</a>

</div>

</body>
</html>