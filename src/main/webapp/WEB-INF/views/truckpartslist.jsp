<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<body>
<div>

    <table>

        <tr>
            <th>Id</th>
            <th>Expedition Id</th>
            <th>Description</th>
            <th>Cost</th>
            <th>Currency Code</th>
            <th>Date of Purchase</th>
        </tr>

        <c:forEach var="truckparts" items="${truckparts}">
            <tr>
                <td>${truckparts.id}</td>
                <td>${truckparts.expedition.id}</td>
                <td>${truckparts.description}</td>
                <td>${truckparts.cost}</td>
                <td>${truckparts.currencyCode}</td>
                <td>${truckparts.dateOfPurchase}</td\
                <td><a href="/truckparts/add/${truckparts.id}">Truckparts/Details</a></td>
            </tr>
        </c:forEach>

    </table>

    <a href="/truckparts/add">Add truck part</a>

    <br>

    <a href="/">Back to home</a>

</div>
</body>
</html>