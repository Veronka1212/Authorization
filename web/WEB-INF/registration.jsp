<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Регистрация</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="name">
        <div>Имя:</div>
        <input type="text" name="name" id="name" required style="background: azure">
    </label><br><br>
    <label for="emailId">
        <div>Почта:</div>
        <input type="text" name="email" id="emailId" required style="background: azure">
    </label><br><br>
    <label for="passwordId">
        <div>Пароль:</div>
        <input type="text" name="password" id="passwordId" required style="background: azure">
    </label><br><br>
    <c:if test="${not empty requestScope.errorsDao}">
        <jsp:useBean id="errorsDao" scope="request" type="java.lang.String"/>
        <span style="color: fuchsia">${errorsDao}</span>
    </c:if>
    <br><br>
    <label for="role"></label><select name="role" id="role" style="background: azure">
    <c:forEach var="roles" items="${requestScope.roles}">
        <option value="${roles}">${roles}</option>
    </c:forEach>
</select>
    <br><br>
    <button type="submit" style="background: lightcyan">Регистрация</button>
    <br>
    <script>document.write("<a href=\"" + document.referrer + "\">Вернуться</a>");</script>
    <br>
    <c:if test="${not empty requestScope.errors}">
        <br><br>
        <c:forEach var="error" items="${requestScope.errors}">
            <span style="color: fuchsia">${error.message}</span>
            <br><br>
        </c:forEach>
    </c:if>
</form>
</body>
</html>
