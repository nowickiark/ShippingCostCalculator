<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<body>
<div>

    <table>

        <tr>
            <th>Id</th>
            <th>Starting date</th>
            <th>Ending date</th>
            <th>Route</th>
            <th>Route destination</th>
            <th>Meter reading - departure</th>
            <th>Meter reading - arrival</th>
            <th>Mileage</th>
            <th>Comments</th>
        </tr>

        <c:forEach var="onejourney" items="${listOfJourneys}">
            <tr>
                <td>${onejourney.id}</td>
                <td>${onejourney.startDate}</td>
                <td>${onejourney.endDate}</td>
                <td>${onejourney.route}</td>
                <td>${onejourney.routeDestination}</td>
                <td>${onejourney.meterReadingDeparture}</td>
                <td>${onejourney.meterReadingArrival}</td>
                <td>${onejourney.mileage}</td>
                <td>${onejourney.comments}</td>
                <td><a href="/journey/update/${onejourney.id}">Edit journey</a></td>
            </tr>
        </c:forEach>

    </table>

    <a href="/journey/add">Add journey</a>
    <br>

    <a href="/">Back to home</a>

</div>

</body>
</html>