<%@ page import="client.Client" %>
<%@ page import="event.Evenement" %>
<%@ page import="zone.Zone" %>
<%@ page import="place.Place" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Client client = (Client) session.getAttribute("client");
    Evenement evenement = client.getEvenement();
    evenement.charger();
    client.check();
    String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/css/bootstrap.css">
    <title>Reservation</title>
</head>
<body>
    <div class="container mt-4">
        <h1 class="text-center"><%=evenement.getNom() %></h1>
        <div class="container row mt-5">
            <% for (Zone zone : evenement.getZones()) { %>
            <div class="col-md-3 shadow row mx-5 rounded-3 p-3">
                <h1>Zone <%=zone.getNom() %></h1>
                <% for (Place place : zone.getPlaces()) { %>
                <div class="col-md-3">
                    <h2 class="text-<%=place.getColor() %>"><%=place.getNumero() %></h2>
                </div>
                <% } %>
            </div>
            <% } %>
        </div>
        <div class="container w-50 p-3 rounded-3 mt-5">
            <form action="reserver.jsp" method="get">
                <input type="text" name="numeros" class="form-control" placeholder="Numeros">
                <% if (error != null) { %>
                <h3 class="mt-3"><%=error %></h3>
                <% } %>
                <div class="row mt-4">
                    <input type="submit" value="Ok" class="btn btn-dark mt-3 px-5">
                </div>
            </form>
            <div class="row mt-4">
                <div class="col-md-4">
                    <a href="liste_reservation.jsp"><button class="btn btn-dark mt-3 px-5">Liste des réservation</button></a>
                </div>
                <div class="col-md-4">
                    <a href="reservation_simple.jsp"><button class="btn btn-dark mt-3 px-5">Réserver Billet Simple</button></a>
                </div>
                <div class="col-md-4">
                    <a href="liste_reservation.jsp"><button class="btn btn-dark mt-3 px-5">Liste de vos billets</button></a>
                </div>
            </div>
            <div class="row">
                <a href="index.jsp"><button class="btn btn-dark mt-3 px-5" style="margin-left: 215px;">Deconnecter</button></a>
            </div>
        </div>
    </div>
</body>
</html>