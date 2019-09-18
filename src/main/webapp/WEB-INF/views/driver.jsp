<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<body>
<h2>Drivers</h2>
<c:forEach var="onedriver" items="${listOfDrivers}">
    <tr>

        <td>Driver id: ${onedriver.id}</td>
        <br>
        <td>First name: ${onedriver.firstName}</td>
        <br>
        <td>Surname: ${onedriver.surname}</td>
        <br>
        <td>Phone number: ${onedriver.phoneNumber}</td>
        <br>
        <td>Personal ID number: ${onedriver.personalIdNumber}</td>
        <br>
        <br>
        <td><a href="/driver/update/${onedriver.id}">Edit driver</a></td>
        <br>

    </tr>
    <br>
</c:forEach>
<a href="/driver/add">Add driver</a>

</body>
</html>