<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>

<html>
<body>
<div>

    <h1>Spedytor View</h1>
    <br>
    <a href="/expedition/add">Create new expedition</a>
    <br>
    <a href="/freightRate/add">Add freight rate</a>
    <br>
    <a href="/expeditions/all">Show all expeditions</a>
    <br>
    <a href="/expeditions/current">Show all active expeditions</a>
    <br>
    <form:form method="GET" action="/expeditions/driver" modelAttribute="driver">
        <form:label path="id">Driver</form:label>
        <form:select path="id" >
            <c:forEach var="dr" items="${drivers}">
                <form:option value="${dr.id}">${dr.firstName} ${dr.surname}</form:option>
            </c:forEach>
        </form:select>
        <input type="submit" value="Show"/>
    </form:form>

    <a href="/spedytorHome">Show all</a>

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
                <td><a href="/expedition/freightRateList/${expedition.id}">Show Freight Rates</a></td>
                <td><a href="/freightRate/add/${expedition.id}">Add Freight Rate To This Expedition</a></td>
                <td><a href="/expedition/add/${expedition.id}">Edit</a></td>
                <td><a href="/expedition/close/${expedition.id}">Close</a></td>
                <td><a href="/expedition/freightRateSum/${expedition.id}">Summary</a></td>
                    <%--<td><a href="/whipround/${whipround.whipRound.id}">Donate/Details</a></td>--%>
            </tr>
        </c:forEach>

    </table>

    <a href="/logout">Logout</a>

</div>

</body>
</html>