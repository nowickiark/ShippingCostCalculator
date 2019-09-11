<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<body>
<div>

    <table>

        <tr>
            <th>Id: </th>
            <th>Date of departure: </th>
            <th>Border details: </th>

        </tr>

        <c:forEach var="borderCross" items="${listOfBorderCrosses}">
            <tr>
                <td>${borderCross.id}</td>
                <td>${borderCross.dateOfBorderCrossing}</td>
                <td>${borderCross.borders.countryFrom}</td>
                <td>${borderCross.borders.cityFrom}</td>
                <td>${borderCross.borders.countryTo}</td>
                <td>${borderCross.borders.cityTo}</td>
                <td><a href="/borderCross/add/${borderCross.id}">Edit</a></td>
        </c:forEach>



    </table>

    <a href="/borderCross/add">Add border crossing</a>

</div>

</body>
</html>