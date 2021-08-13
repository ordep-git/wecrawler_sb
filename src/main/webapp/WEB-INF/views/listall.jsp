<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp" %>
<hr>
<h2>baza maili
 <span class="badge bg-secondary rounded-pill">${emails.size()}
 </span>
</h2>
<table class="table table-striped">
    <tr>
        <th scope="col">id</th>
        <th scope="col">email</th>
        <th scope="col">del</th>
    </tr>
    <c:forEach items="${emails}" var="email" begin="0" varStatus="count">
        <tr>
            <td><c:out value="${count.index+1}"/></td>
            <td>${email.email}</td>
            <td>
                <a href="/email/delete/${email.id}">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="footer.jsp" %>
