<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<hr>
<h2>${emails.size()} emaili dodano do bazy</h2>
<table class="table table-striped">
    <tr>
        <th scope="col">email</th>
        <th scope="col">url</th>
    </tr>
    <c:forEach items="${emails}" var="email">
        <tr>
            <td>${email.email}</td>
            <td>${email.printUrls()}</td>
        </tr>
    </c:forEach>
</table>
<%@ include file="footer.jsp" %>
