<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="header.jsp" %>
<hr>
<h2>znajdź maile</h2>

<table class="table table-striped">
    <tr>
        <td>
            <form action="/search">
                <label for="url">URL:</label>
                <input id="url" type="text" name="url"/>
                <input type="submit" value="start"/><br>
            </form>
            <span class="input-group-addon" id="basic-addon3">https://example.com/users/</span>
        </td>
    </tr>
    <tr>
        <td></td>
    </tr>
    <tr>
        <td>
            <form action="/searchlinks">
                <label for="url">KEYWORDS (google):</label>
                <input id="keywords" type="text" name="keywords"/>
                <label for="url">Ile stron przeszukać:</label>
                <select name="numberOfPages">
                    <c:forEach var="i" begin="1" end="10">
                        <option><c:out value="${i}"/></option>
                    </c:forEach>
                </select>
                <input type="submit" value="start"/>
            </form>
            <div class="alert alert-warning" role="alert">
                <span class="glyphicon glyphicon-warning-sign" aria-hidden="true"
                      icon-size> To może chwilę potrwać</span>
            </div>
        </td>
    </tr>
</table>

<%--<form:form action="/search">--%>
<%--<form:label path="url">URL: </form:label>--%>
<%--    <form:input path="url"/>--%>
<%--    <form:errors path="url" cssClass="error"/>--%>
<%--<button type="submit">Wyślij</button>--%>
<%--</form:form>--%>
<%@ include file="footer.jsp" %>

