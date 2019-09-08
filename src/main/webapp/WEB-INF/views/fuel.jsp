<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
<h2>Fuelings</h2>
<c:forEach var="listOfFuelings" items="${listOfFuelings}">
    <tr>

        <td>Fueling id: ${listOfFuelings.id}</td> <br>
        <td>Place of refueling: ${listOfFuelings.placeOfRefueling}</td><br>
        <td>Liters: ${listOfFuelings.liters}</td><br>
        <td>Cost: ${listOfFuelings.cost}</td><br>
        <td>Currency: ${listOfFuelings.currency}</td><br>
        <td>Kilometers: ${listOfFuelings.kilometers}</td><br>
        <td>Full: ${listOfFuelings.refuelingToFull}</td><br>
        <td><a href="/updatefuel">Edit refueling</a></td>   //tu mi jeszcze nie działa updatefuel

    </tr>
    <br>
</c:forEach>
<a href="/addfuel">Add refueling</a>
</body>
</html>