<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Szukajka maili</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="bg-light p-5 rounded mt-3">
        <p>
            <a href='<c:url value="/"/>'>
                <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
            </a>
            <a href='<c:url value="/form"/>'>znajdź maile | </a>
            <a href='<c:url value="/listall"/>'> baza maili |</a>
            <a href='<c:url value="/keywords"/>'> słowa kluczowe</a>
        </p>