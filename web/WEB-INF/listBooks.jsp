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
        <h1>Книги библиотеки:</h1>
        <form action="editBookForm" method="POST">
            <select name="bookId" multiple="true">
                <option value="">Список книг</option>
                <c:forEach var="book" items="${listBooks}">
                    <option value="${book.id}">"${book.name}". ${book.author}. ${book.publishedYear}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Изменить выбранную книгу">
        </form>
    </body>
</html>
