<%-- 
    Document   : editBookForm
    Created on : 17.01.2021, 11:53:54
    Author     : artur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Редактирование книги </title>
    </head>
    <body>
        <h1>Изменить атрибуты книги</h1>
        <p>${info}</p>
        <form action="editBook" method="POST">
            <input type="hidden" name="bookId" value="${book.id}"><br>
            Название книги: <input type="text" name="name" value="${book.name}"><br>
            Автор книги <input type="text" name="author" value="${book.author}"><br>
            Год издания книги: <input type="text" name="publishedYear" value="${book.publishedYear}"><br>
            <input type="submit" name="submit" value="Отправить"><br>
        </form>
    </body>
</html>
