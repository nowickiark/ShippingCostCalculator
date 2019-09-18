<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<body>
<div>

    <h3>Please login</h3>

    <form name='loginForm' action="/login" method='POST'>

        <table>
            <tr>
                <td>User:</td>
                <td><input class= type='text' name='username'></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td colspan='2'><input  name="submit" type="submit" value="submit" /></td>
            </tr>
        </table>

    </form>
</div>

</body>
</html>