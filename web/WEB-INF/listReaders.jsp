<%-- 
    Document   : listReaders
    Created on : 19.12.2020, 10:04:48
    Author     : artur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список читателей</title>
    </head>
    <body>
        <h1>Список читателей библиотеки:</h1>
        <c:forEach var="reader" varStatus="status" items="${listReaders}">
                <li>${status.index + 1}. "${reader.name}". ${reader.lastname}. ${reader.phone}</li>
            </c:forEach>
    </body>
</html>
