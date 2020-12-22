<%-- 
    Document   : index
    Created on : 05.12.2020, 12:08:50
    Author     : artur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Библиотека Arlamov</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <div>Библиотека</div>
        <p>${info}</p> 
        <br>
        <a href="addBook">Добавить новую книгу</a></br>
        <a href="addReader">Добавить читателя</a></br>
        <a href="listBooks">Список книг</a></br>
        <a href="listReaders">Список читателей</a></br>
        <a href="takeOnBookForm">Выдать книгу читателю</a></br>
        <a href="returnBookForm">Вернуть книгу в библиотеку</a><br>
    </body>
</html>
