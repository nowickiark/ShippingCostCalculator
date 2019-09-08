<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
<h2>Fuelings</h2>
<c:forEach var="singlefueling" items="${listOfFuelings}">
    <tr>

        <td>Fueling id: ${singlefueling.id}</td> <br>
        <td>Place of refueling: ${singlefueling.placeOfRefueling}</td><br>
        <td>Liters: ${singlefueling.liters}</td><br>
        <td>Cost: ${singlefueling.cost}</td><br>
        <td>Currency: ${singlefueling.currency}</td><br>
        <td>Kilometers: ${singlefueling.kilometers}</td><br>
        <td>Full: ${singlefueling.refuelingToFull}</td><br>
        <td><a href="/updatefuel/${singlefueling.id}">Edit refueling</a></td> 

    </tr>
    <br>
</c:forEach>
<a href="/addfuel">Add refueling</a>
</body>
</html>