<%@ page import="reservation.Reservation" %>
<%@ page import="client.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Client client = (Client) session.getAttribute("client");
    client.chargerReservations();
    Reservation[] reservations = client.getReservations();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/css/bootstrap.css">
    <title>Document</title>
</head>
<body>
    <div class="container w-75">
        <table class="table">
            <tr>
                <th>Date</th>
                <th>Evenement</th>
                <th>Date Limite</th>
                <th></th>
            </tr>
            <% for (Reservation reservation : reservations) { %>
            <tr>
                <td><%=reservation.getDate() %></td>
                <td><%=reservation.getEvenement().getNom() %></td>
                <td><%=reservation.getLimite() %></td>
                <td><a href="confirmer.jsp?id=<%=reservation.getIdReservation() %>"><button class="btn btn-outline-dark">Confirmer</button></a></td>
            </tr>
            <% } %>
        </table>
    </div>
</body>
</html>