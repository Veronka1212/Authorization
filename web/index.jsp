<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="${pageContext.request.contextPath}/index" method="post">
    <label for="email">
        <div>Почта:</div>
        <input type="email" name="email" id="email" value="${param.email}" required style="background: azure">
    </label><br><br>
    <label for="password">
        <div>Пароль:</div>
        <input type="text" name="password" id="password" required style="background: azure">
    </label><br><br>
    <button type="submit" style="background: lightcyan">Войти</button>
    <a href="${pageContext.request.contextPath}/registration">
        <button type="button" style="background: lightcyan">Зарегистрироваться</button>
    </a>
    <c:if test="${param.error!=null}">
        <div style="color: deeppink">
            <span>Неверный логин или пароль!</span>
        </div>
    </c:if>
</form>


