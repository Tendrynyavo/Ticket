<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="client.Client" %>
<%
    Client[] clients = Client.getAllClients();
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
        <form action="session_client.jsp" method="get">
            <div class="">
                <select class="form-select" name="id">
                    <option selected>Selectionner un client</option>
                    <% for (Client client : clients) { %>
                    <option value="<%=client.getIdClient() %>"><%=client.getNom() %></option>
                    <% } %>
                </select>
            </div>
            <div class="mt-4">    
                <select class="form-select" name="id">
                    <option selected>Selectionner un evenement</option>
                    <% for (Client client : clients) { %>
                    <option value="<%=client.getIdClient() %>"><%=client.getNom() %></option>
                    <% } %>
                </select>
            </div>
            <input type="submit" value="Ok" class="btn btn-outline-warning mt-3 px-5">
        </form>
        <a href="formulaire.jsp?traitement=ajouter_client"><button class="btn btn-outline-warning mt-3 px-5">Nouveau client</button></a>
        <a href="formulaire.jsp?traitement=ajouter_client"><button class="btn btn-outline-warning mt-3 px-5">Nouveau evenement</button></a>
    </div>
</body>
</html>