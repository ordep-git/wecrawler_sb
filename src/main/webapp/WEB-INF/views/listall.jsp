<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp" %>
<hr>
<h2>baza maili</h2>

<%--<form:select path="keyword" >--%>
<%--    <form:option value="-" label="--Please Select--"/>--%>
<%--    <form:options items="${keywords}"/>--%>
<%--</form:select>--%>

<table class="table table-striped">
    <tr>
        <th scope="col">id</th>
        <th scope="col">email</th>
        <%--        <th scope="col">url</th>--%>
        <th scope="col">del</th>
    </tr>
    <c:forEach items="${emails}" var="email" begin="1" varStatus="count">
        <tr>
            <td><c:out value="${count.index}"/></td>
                <%--            <td>${email.id}</td>--%>
            <td>${email.email}</td>
                <%--            <td>${email.printUrls()}</td>--%>
            <td>
                <a href="/email/delete/${email.id}">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>

<%--<form:form method="post" modelAttribute="keywords">--%>
<%--<form:select path="keyword" items="${keywords}"/>--%>
<%--    <input type="submit" value="Add">--%>
<%--</form:form>--%>

<%@ include file="footer.jsp" %>
