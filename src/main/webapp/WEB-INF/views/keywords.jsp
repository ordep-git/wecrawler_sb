<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="header.jsp" %>
<hr>
<h2>słowa kluczowe</h2>

<table class="table table-striped">
    <tr>
        <th scope="col">keyword</th>
        <th scope="col">emails</th>
        <th scope="col">del</th>
    </tr>
    <c:forEach items="${keywords}" var="keyword">
        <tr>
            <td>${keyword.keyword}</td>
            <td>${keyword.emails}</td>
            <td>
                <a href="/keyword/delete/${keyword.id}">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>

<%@ include file="footer.jsp" %>
