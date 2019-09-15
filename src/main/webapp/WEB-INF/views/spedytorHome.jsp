<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<body>
<div>

    <h1>Spedytor View</h1>
    <br>

    <a href="/expeditions/all">Show all expeditions</a>
    <br>
    <a href="/expeditions/current">Show all active expeditions</a>
    <br>
    <a href="/expeditions/driver">Show expeditions of the driver</a>

    <br>

    <table>

        <tr>
            <th>Id</th>
            <th>Driver</th>
            <th>Truck</th>
            <th>Starting place</th>
            <th>Starting odometer reading</th>
            <th>Starting Day</th>
        </tr>

        <c:forEach var="expedition" items="${expeditions}">
            <tr>
                <td>${expedition.id}</td>
                <td>${expedition.driver.firstName}</td>
                <td>${expedition.truck.plateNumber}</td>
                <td>${expedition.startingPlace}</td>
                <td>${expedition.startOdometerReading}</td>
                <td>${expedition.startDay}</td>
                <td><a href="/expedition/add/${expedition.id}">Edit</a></td>
                    <%--<td><a href="/whipround/${whipround.whipRound.id}">Donate/Details</a></td>--%>
            </tr>
        </c:forEach>

    </table>

    <a href="/logout">Logout</a>

</div>

</body>
</html>