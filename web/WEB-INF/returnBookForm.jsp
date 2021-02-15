<%-- 
    Document   : returnBookForm
    Created on : 02.01.2021, 13:03:28
    Author     : artur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Возврат книги в библиотеку</title>
    </head>
    <body>
        <h1>Возврат книги в библиотеку</h1>
        <p>${info}</p>
        <form action="returnBook" method="POST">
            <select name="historyId" multiple="true">
                <option value="">Выберите возвращаемую книгу:</option>
                <c:forEach var="history" items="${listHistoriesWithReadBook}">
                    <option value="${history.id}">"${history.book.name}" читает ${history.reader.name} ${history.reader.lastname} </option>
                </c:forEach>
            </select>
            <br>
            <input type="submit" value="Вернуть книгу">
        </form>
    </body>
</html>