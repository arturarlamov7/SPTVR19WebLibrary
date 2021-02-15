<%-- 
    Document   : takeBookForm
    Created on : 22.12.2020, 11:13:41
    Author     : artur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Выдать книгу</title>
    </head>
    <body>
        <h1>Выдать книгу</h1>
        <form action="takeOnBook" method="POST"
            <select name="bookId">
                <option value="#">Выберите книгу: </option>
                <c:forEach var="book" varStatus="status" items="${listBooks}">
                    <option value="${book.id}"> ${status.index + 1}. "${book.name}". ${book.author}. ${book.publishedYear}</option>
                </c:forEach> 
            </select>
            <br>
            <input type="submit" value="Взять книгу">
        </form>
    </body>
</html>
