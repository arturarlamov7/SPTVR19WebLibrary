<%-- 
    Document   : addBookForm
    Created on : 05.12.2020, 10:41:18
    Author     : artur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Добавить книгу</h1>
        <form action="createBook" method="POST">
            Название книги: <input type="text" name="name" value=""><br>
            Автор книги: <input type="text" name="author" value=""><br>
            Год издания книги: <input type="text" name="publishedYear" value=""><br>
            <input type="submit" name="submit" value="Отправить"><br>
        </form>
    </body>
</html>
