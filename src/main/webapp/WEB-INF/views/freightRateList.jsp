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

        <c:forEach var="freightRate" items="${freighteRates}">
            <tr>
                <td>${freightRate.id}</td>
                <td>${freightRate.freightCompany}</td>
                <td>${freightRate.freightRateDistance}</td>
                <td>${freightRate.amount}</td>
                <td>${freightRate.currencyCode}</td>
                <td>${freightRate.date}</td>
                <td><a href="/expedition/add/${freightRate.id}">Edit</a></td>
                    <%--<td><a href="/whipround/${whipround.whipRound.id}">Donate/Details</a></td>--%>
            </tr>
        </c:forEach>

    </table>

    <a href="/expedition/add">Add expedition</a>
    <br>
    <a href="/">Back to home</a>

</div>

</body>
</html>