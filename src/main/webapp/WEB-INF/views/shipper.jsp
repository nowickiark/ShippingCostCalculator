<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<body>
<h2>Shipper</h2>
<form:form method="POST" action="/expeditions/all" modelAttribute="expeditions" >
    <table>
        <tr>
            <td><form:label path="kilometersTraveled">Kilometers traveled</form:label></td>
            <td><form:input path="kilometersTraveled"/></td>
        </tr>
        <tr>
            <td><form:label path="kilometersTraveledAccordingToOrders">Kilometers traveled according to orders</form:label></td>
            <td><form:input path="kilometersTraveledAccordingToOrders"/></td>
        </tr>
        <tr>
            <td><form:label path="difference">Difference</form:label></td>
            <td><form:input path="difference"/></td>
        </tr>
        <br>
        <tr>
            <td><form:label path="refuelingInPoland">Refueling in Poland</form:label></td>
            <td><form:input path="refuelingInPoland"/></td>
        </tr>
        <tr>
            <td><form:label path="averageFuelCostInPoland">Average fuel cost in Poland</form:label></td>
            <td><form:input path="averageFuelCostInPoland"/></td>
        </tr>
        <br>
        <tr>
            <td><form:label path="refueling abroad">Refueling abroad</form:label></td>
            <td><form:input path="refuelingAbroad"/></td>
        </tr>
        <tr>
            <td><form:label path="averageFuelCostAbroad">Average fuel cost abroad</form:label></td>
            <td><form:input path="averageFuelCostAbroad"/></td>
        </tr>
        <br>
        <tr>
            <td><form:label path="totalRefueling">Total refueling</form:label></td>
            <td><form:input path="totalRefueling"/></td>
        </tr>
        <tr>
            <td><form:label path="averageFuelCost">Average fuel cost</form:label></td>
            <td><form:input path="averageFuelCost"/></td>
        </tr>
        <br>
        <tr>
            <td><form:label path="burning">Burning (liters/100 km)</form:label></td>
            <td><form:input path="burning"/></td>
        </tr>
        <br>
        <tr>
            <td><form:label path="totalFreightRates">Total freight rates</form:label></td>
            <td><form:input path="totalFreightRates"/></td>
        </tr>
        <tr>
            <td><form:label path="averageRate">Average rate</form:label></td>
            <td><form:input path="averageRate"/></td>
        </tr>
        <br>
        <tr>
            <td><form:label path="driverPaymentPerKm">Driver payment per km</form:label></td>
            <td><form:input path="driverPaymentPerKm"/></td>
        </tr>
        <tr>
            <td><form:label path="driverPaymentForParking">Driver payment for parking</form:label></td>
            <td><form:input path="driverPaymentForParking"/></td>
        </tr>
        <br>
        <tr>
            <td><form:label path="extraCosts">Extra costs</form:label></td>
            <td><form:input path="extraCosts"/></td>
        </tr>
        <tr>
            <td><form:label path="repairAndPartsCosts">Repair and parts costs</form:label></td>
            <td><form:input path="repairAndPartsCosts"/></td>
        </tr>
        <br>
        <tr>
            <td><form:label path="totalCosts">Total costs</form:label></td>
            <td><form:input path="totalCosts"/></td>
        </tr>
        <br>
        <tr>
            <td><form:label path="balance">Balance</form:label></td>
            <td><form:input path="balance"/></td>
        </tr>
        <br>
        <tr>
            <td><input type="submit" value="Expeditions"/></td>
        </tr>
    </table>

</form:form>

<br>

<a href="/">Back to home</a>

</body>
