<%-- 
    Document   : addReaderForm
    Created on : 19.12.2020, 10:03:10
    Author     : artur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавление читателя</title>
    </head>
    <body>
        <h1>Добавить читателя</h1>
        <form action="createReader" method="POST">
            Имя читателя: <input type="text" name="name" value="${name}"><br> 
            Фамилия: <input type="text" name="lastname" value="${lastname}"><br>
            Номер телефона: <input type="text" name="phone" value="${phone}"><br>
            <input type="submit" name="submit" value="Отправить"><br>           
        </form>
    </body>
</html>
