<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<hr>
<h2>${emails.size()} email/e dodano do bazy</h2>
<table class="table table-striped">
    <tr>
        <th scope="col">email</th>
        <th scope="col">url</th>
    </tr>
    <c:forEach items="${emails}" var="email">
        <tr>
                    <td>${email}</td>
                    <td>${url}</td>
        </tr>
    </c:forEach>
</table>
<%@ include file="footer.jsp" %>
