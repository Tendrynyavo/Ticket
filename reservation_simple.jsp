<%@ page import="client.Client" %>
<%@ page import="event.Evenement" %>
<%@ page import="zone.Zone" %>
<%@ page import="place.Place" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Client client = (Client) session.getAttribute("client");
    Evenement evenement = client.getEvenement();
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
        <div class="container w-50 p-3 rounded-3 mt-5">
            <form action="ajouter_simple.jsp" method="get">
                <input type="text" name="nombre" class="form-control" placeholder="Nombre">
                <% if (error != null) { %>
                <h3 class="mt-3"><%=error %></h3>
                <% } %>
                <div class="row mt-4">
                    <input type="submit" value="Ok" class="btn btn-dark mt-3 px-5">
                </div>
            </form>
            <%
                int difference = evenement.getDifference();
            %>
            <h3 class="mt-3">Il en reste <%=difference %> place<% if (difference > 1) out.print("s");  %></h3>
            <div class="row mt-4">
                <div class="col-md-4">
                    <a href="liste_reservation.jsp"><button class="btn btn-dark mt-3 px-5">Liste des réservation</button></a>
                </div>
                <div class="col-md-4">
                    <a href="reservation.jsp"><button class="btn btn-dark mt-3 px-5">Réserver Billet</button></a>
                </div>
                <div class="col-md-4">
                    <a href="liste_reservation.jsp"><button class="btn btn-dark mt-3 px-5">Liste de vos billets</button></a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>