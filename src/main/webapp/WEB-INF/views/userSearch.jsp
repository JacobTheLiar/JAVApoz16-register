<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<body>
<spring:url value="/users" var="searchUserUrl" />

<form method="GET" action="${searchUserUrl}">
    <table>
        <tr>
            <td><label path="firstName">First name</label></td>
            <td><input path="firstName" name="firstName"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="checkbox" name="matchExact">match exact</td>
        </tr>
        <tr>
            <td><input type="submit" value="search"/></td>
        </tr>
    </table>
</form>

<a href="/users">Go back to users list</a>

</body>
</html>