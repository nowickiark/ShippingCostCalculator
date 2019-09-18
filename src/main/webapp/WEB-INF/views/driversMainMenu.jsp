<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<body>

<h2>Driver Main Menu</h2>

<spring:url value="/borderCross/add" var="addBorderCrossFormUrl" />
<button class="btn btn-primary" onclick="location.href='${addBorderCrossFormUrl}'">Add Border Cross</button>
<br>
<br>

<spring:url value="/addfuel" var="addFuelFormUrl" />
<button class="btn btn-primary" onclick="location.href='${addFuelFormUrl}'">Add Fuel</button>
<br>
<br>

<spring:url value="/extracost/addd" var="addExtraCostFormUrl" />
<button class="btn btn-primary" onclick="location.href='${addExtraCostFormUrl}'">Add Extra Cost</button>

</body>
</html>
