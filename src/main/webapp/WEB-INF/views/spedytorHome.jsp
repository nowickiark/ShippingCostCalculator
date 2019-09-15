<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<body>
<div>

    <h1>Spedytor View</h1>
    <br>

    <form method="get" action="/expeditions/all">
        <button type="submit" >Show all expeditions</button>
    </form>

    <form method="get" action="/expeditions/current">
        <button type="submit" >Show all active expeditions</button>
    </form>

    <form method="get" action="/expeditions/driver">
        <button type="submit" >Show expeditions of the driver</button>
    </form>

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