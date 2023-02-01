<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="client.Client" %>
<%@ page import="event.Evenement" %>
<%
    Client[] clients = null;
    Evenement[] events = null;
    try {
        events = Evenement.getAllEvenements();
        clients = Client.getAllClients();
    } catch (Exception e) {
        out.print(e.getMessage());
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/css/bootstrap.css">
    <title>Login</title>
</head>
<body>
    <div class="container w-50 p-3 shadow rounded-3 mt-5">
        <form action="session.jsp" method="get">
            <div class="">
                <select class="form-select" name="client">
                    <% for (Client client : clients) { %>
                    <option value="<%=client.getIdClient() %>"><%=client.getNom() %></option>
                    <% } %>
                </select>
                <select class="form-select mt-3" name="event">
                    <% for (Evenement evenement : events) { %>
                    <option value="<%=evenement.getIdEvenement() %>"><%=evenement.getNom() %></option>
                    <% } %>
                </select>
            </div>
            <div class="mt-4">   
            </div>
            <input type="submit" value="Ok" class="btn btn-outline-warning mt-3 px-5">
        </form>
        <a href="formulaire.jsp?traitement=client"><button class="btn btn-outline-warning mt-3 px-5">Nouveau client</button></a>
        <a href="formulaire_zone.jsp"><button class="btn btn-outline-warning mt-3 px-5">Back Office</button></a>
    </div>
</body>
</html>