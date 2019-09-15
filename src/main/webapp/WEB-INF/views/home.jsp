<%@ page session="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<body>

<table>
    <tr>
        <td><a href="/expedition/listOfExtraCosts">Extra Cost</a></td>

        <td><a href="extracost/add">Add Extra Cost</a></td>
    </tr>

    <tr>

<a href="/expedition/listOfTruckParts">Truck Parts</a>

<br>

<a href="/expedition/listOfFuels">Fuel</a>
        <td><a href="/expedition/listOfFuels">Fuel</a></td>

        <td><a href="/addfuel">Add Fuel</a></td>
    </tr>

       <tr>
        <td><a href="/expedition/listOfBorderCrosses">Border Crosses</a></td>

        <td><a href="/borderCross/add">Add Border Crosses</a></td>
    </tr>

    <tr>
        <td><a href="/journeys">Show all my journeys</a></td>

        <td><a href="/journey/add">Add new journey</a></td>
    </tr>

    <tr>
        <td><a href="/expeditions">Show all my expeditions</a></td>

        <td><a href="/expedition/add">Add new expedition</a></td>
    </tr>

    <tr>
        <td><a href="/logout">Logout</a></td>
    </tr>

</table>
</body>
</html>