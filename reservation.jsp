<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="client.Client" %>
<%
    Client client = (Client) session.getAttribute("client");
    out.print(client.getNom());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation</title>
</head>
<body>
    <form action="reserver.jsp" method="get">
        <input type="text" name="numeros">
    </form>
</body>
</html>