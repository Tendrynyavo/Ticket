<%@ page import="reservation.Reservation" %>
<%@ page import="client.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Client client = (Client) session.getAttribute("client");
    client.check();
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
    <div class="container w-75 mt-5">
        <h1>Reservation de <%=client.getNom() %></h1>
        <table class="table mt-4">
            <tr>
                <th>Date</th>
                <th>Evenement</th>
                <th>Date Limite de confirmation</th>
                <th>Place</th>
                <th>Prix</th>
                <th></th>
            </tr>
            <% for (Reservation reservation : reservations) { %>
            <tr>
                <td><%=reservation.getDate() %></td>
                <td><%=reservation.getEvenement().getNom() %></td>
                <td><%=reservation.getLimite() %></td>
                <td><%=reservation.getPlacesString() %></td>
                <td><%=reservation.getPrix() %></td>
                <td><a href="confirmer.jsp?id=<%=reservation.getIdReservation() %>"><button class="btn btn-outline-dark">Confirmer</button></a></td>
            </tr>
            <% } %>
        </table>
        <a href="reservation.jsp"><button class="btn btn-dark">Retour</button></a>
    </div>
</body>
</html>