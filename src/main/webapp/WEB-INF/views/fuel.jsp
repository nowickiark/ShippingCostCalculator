<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
<h1>Fuelings</h1>

<style>
    table, th, td {
        border: 1px solid black;
    }
    .cornflowerblue {background: cornflowerblue}
    .blanchedalmond{background: blanchedalmond}


</style>

<table>

    <tr>
        <th>Fueling id</th>
<%--        <th>Expedition</th>--%>
        <th>Place of refueling</th>
        <th>Liters</th>
        <th>Cost</th>
        <th>Currency</th>
        <th>Kilometers</th>
        <th>Payment method</th>
        <th>Full</th>
        <th>Edit</th>
    </tr>
<c:forEach var="singlefueling" items="${listOfFuelings}">

    <tr>

            <td>${singlefueling.id}</td> <br>
                <%--        add Expedition--%>
            <td>${singlefueling.placeOfRefueling}</td>
            <td>${singlefueling.liters}</td>
            <td>${singlefueling.cost}</td>
            <td>${singlefueling.currency}</td>
            <td>${singlefueling.kilometers}</td>
            <td>${singlefueling.paymentMethod}</td>
            <td>${singlefueling.refuelingToFull}</td>
            <td><a href="/updatefuel/${singlefueling.id}">Edit</a></td>

    </tr>

</c:forEach>
</table>
<a href="/addfuel">Add refueling</a>
</body>
</html>