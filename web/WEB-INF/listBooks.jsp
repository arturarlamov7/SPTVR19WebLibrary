<%-- 
    Document   : listBooks
    Created on : 17.12.2020, 15:59:27
    Author     : artur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список книг</title>
    </head>
    <body>
        <h1>Список книг библиотеки:</h1>    
        <c:forEach var="book" varStatus="status" items="${listBooks}">
                <li>${status.index + 1}. "${book.name}". ${book.author}. ${book.publishedYear}</li>
            </c:forEach>      
    </body>
</html>