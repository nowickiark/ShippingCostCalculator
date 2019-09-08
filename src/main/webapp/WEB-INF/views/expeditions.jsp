<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<body>
<div>

    <table>

        <tr>
            <th>Id</th>
            <th>Truck</th>
            <th>Starting place</th>
            <th>Starting odometer reading</th>
            <th>Starting Day</th>
        </tr>

        <c:forEach var="expedition" items="${expeditions}">
            <tr>
                <td>${expedition.id}</td>
                <td>${expedition.truck.plateNumber}</td>
                <td>${expedition.startingPlace}</td>
                <td>${expedition.startOdometerReading}</td>
                <td>${expedition.startDay}</td>
                <%--<td><a href="/whipround/${whipround.whipRound.id}">Donate/Details</a></td>--%>
            </tr>
        </c:forEach>

    </table>



</div>

</body>
</html>