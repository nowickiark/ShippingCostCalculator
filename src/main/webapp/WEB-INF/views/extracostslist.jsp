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
            <th>Currency</th>
            <th>Date of Purchase</th>
        </tr>

        <c:forEach var="extracost" items="${extracosts}">
            <tr>
                <td>${extracost.id}</td>
                <td>${extracost.expedition.id}</td>
                <td>${extracost.description}</td>
                <td>${extracost.cost}</td>
                <td>${extracost.currency}</td>
                <td>${extracost.dateOfPurchase}</td\
                <td><a href="/extracost/add/${extracost.id}">Extracost/Details</a></td>
            </tr>
        </c:forEach>

    </table>

    <a href="/extracost/add">Add Extra cost</a>

    <br>

    <a href="/">Back to home</a>

</div>
</body>
</html>