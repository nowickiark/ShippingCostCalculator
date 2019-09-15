<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<body>

<h2>Add Journey</h2>

<spring:url value="/journey/add" var="addJourneyUrl"/>

<spring:url value="/journey/update" var="updateJourneyUrl"/>

<form:form method="POST" action="${update? updateJourneyUrl : addJourneyUrl}" modelAttribute="journey">

    <table>
        <tr>
            <td><form:label path="id">Id</form:label></td>
            <td><form:input path="id" readonly="true" /></td>
        </tr>
        <tr>
            <td><form:label path="startDate">Starting date</form:label></td>
            <td><form:input type="datetime-local" path="startDate"/></td>
        </tr>
        <tr>
            <td><form:label path="endDate">Starting date</form:label></td>
            <td><form:input type="datetime-local" path="endDate"/></td>
        </tr>
        <tr>
            <td><form:label path="route">Route</form:label></td>
            <td><form:input  path="route"/></td>
        </tr>
        <tr>
            <td><form:label path="routeDestination">Route destination</form:label></td>
            <td><form:input path="routeDestination"/></td>
        </tr>
        <tr>
            <td><form:label path="meterReadingDeparture">Meter reading - departure</form:label></td>
            <td><form:input path="meterReadingDeparture"/></td>
        </tr>
        <tr>
            <td><form:label path="meterReadingArrival">Meter reading - arrival</form:label></td>
            <td><form:input path="meterReadingArrival"/></td>
        </tr>
        <tr>
            <td><form:label path="mileage">Mileage</form:label></td>
            <td><form:input path="mileage"/></td>
        </tr>
        <tr>
            <td><form:label path="comments">Comments</form:label></td>
            <td><form:input path="comments"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Add"/></td>
        </tr>

    </table>
</form:form>

<br>
<a href="/">Go Back Home</a>

</body>
</html>