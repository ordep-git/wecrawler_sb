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
                <%--    <form:errors path="url" cssClass="error"/>--%>
                <input type="submit" value="start"/>
            </form>
        </td>
    </tr>
    <tr><td></td></tr>
    <tr>
        <td>
            <form action="/searchlinks">
                <label for="url">KEYWORDS (google):</label>
                <input id="keywords" type="text" name="keywords"/>
                <input type="submit" value="start"/>
            </form>
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

