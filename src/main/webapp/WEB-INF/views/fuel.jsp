<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>


<body>
<div>
    <h1>Fuelings</h1>
</div>

<link href="/style.css" rel="stylesheet"/>
<div>
    <table class="table-dark">
    <tr>
        <th>Fueling id</th>
        <th>Expedition id</th>
        <th>Place of refueling</th>
        <th>Liters</th>
        <th>Cost</th>
        <th>Currency</th>
        <th>Kilometers</th>
        <th>Payment method</th>
        <th>Date</th>
        <th>Full</th>
        <th>Edit</th>
    </tr>
    <c:forEach var="singlefueling" items="${listOfFuelings}">

    <tr>

            <td>${singlefueling.id}</td>
            <td>${singlefueling.expedition.id}</td>
            <td>${singlefueling.placeOfRefueling}</td>
            <td>${singlefueling.liters}</td>
            <td>${singlefueling.cost}</td>
            <td>${singlefueling.currencyCode}</td>
            <td>${singlefueling.kilometers}</td>
            <td>${singlefueling.paymentMethod}</td>
            <td>${singlefueling.dateOfFueling}</td>
            <td>${singlefueling.refuelingToFull}</td>
            <td><a style="text-decoration: none" href="/updatefuel/${singlefueling.id}" class="text-green">Edit</a></td>
    </tr>

</c:forEach>
</table>
</div>
<div class="container">
    <a style="text-decoration: none" href="/addfuel" class="btn">ADD REFUELING</a>
<%--    <a href="/addfuel" class="text-blue">Add refueling</a>--%>

    <a style="text-decoration: none" href="/" class="btn">GO BACK HOME</a>
<%--    <a href="/" class="text-blue">Go Back Home</a>--%>
</div>


</body>
</html>