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
        <div class="container row mt-5">
            <% for (Zone zone : evenement.getZones()) { %>
            <div class="col-md-3 shadow row mx-5 rounded-3 p-3">
                <h1>Zone <%=zone.getNom() %></h1>
                <% for (Place place : zone.getPlaces()) { %>
                <div class="col-md-3">
                    <h2 class="text-<% if (!place.isLibre()) { out.print("success"); } else if (place.isConfirme()) { out.print("danger"); } else { out.print("black-50"); }%>"><%=place.getNumero() %></h2>
                </div>
                <% } %>
            </div>
            <% } %>
        </div>
        <div class="container w-50 p-3 rounded-3 mt-5">
            <form action="reserver.jsp" method="get">
                <input type="text" name="numeros" class="form-control" placeholder="Numeros">
                <input type="text" name="date" class="form-control mt-3" placeholder="Date">
                <% if (error != null) { %>
                <h3 class="mt-3"><%=error %></h3>
                <% } %>
                <div class="row mt-4">
                    <input type="submit" value="Ok" class="btn btn-dark mt-3 px-5">
                </div>
            </form>
        </div>
    </div>
</body>
</html>