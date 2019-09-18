<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<body>
<div>

    <h1>Freight Rates for expedition id ${freightRates[0].expedition.id}</h1>

    <table>

        <tr>
            <th>Id</th>
            <th>Company</th>
            <th>Distance</th>
            <th>Amount</th>
            <th>Currency</th>
            <th>Date</th>
        </tr>

        <c:forEach var="freightRate" items="${freightRates}">
            <tr>
                <td>${freightRate.id}</td>
                <td>${freightRate.freightCompany}</td>
                <td>${freightRate.freightDistance}</td>
                <td>${freightRate.amount}</td>
                <td>${freightRate.currencyCode}</td>
                <td>${freightRate.date}</td>
                <td><a href="/freightRate/edit/${freightRate.id}">Edit</a></td>
            </tr>
        </c:forEach>

    </table>

    <a href="/freightRate/add/${freightRates[0].expedition.id}">Add new Freight Rate To This Expedition</a>
    <br>
    <a href="/">Back to home</a>

</div>

</body>
</html>