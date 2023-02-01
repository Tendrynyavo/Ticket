<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="event.Evenement" %>
<%@ page import="zone.Zone" %>
<%
    String error = request.getParameter("error");
    Evenement[] events = Evenement.getAllEvenements();
    Zone[] zones = Zone.getAllZones();
%>
<%@ include file="header.jsp" %>
    <div class="container w-50 p-3 shadow rounded-3 mt-5">
        <h2>Ajouter Promotion</h2>
        <form action="ajouter_promotion.jsp" method="get">
            <input type="text" name="pourcentage" class="form-control mt-3" placeholder="Pourcentage">
            <input type="text" name="debut" class="form-control mt-3" placeholder="Debut">
            <input type="text" name="fin" class="form-control mt-3" placeholder="Fin">
            <select class="form-select mt-3" name="event">
                <% for (Evenement evenement : events) { %>
                <option value="<%=evenement.getIdEvenement() %>"><%=evenement.getNom() %></option>
                <% } %>
            </select>
            <select class="form-select mt-3" name="zone">
                <% for (Zone zone : zones) { %>
                <option value="<%=zone.getIdZone() %>"><%=zone.getNom() %></option>
                <% } %>
            </select>
            <input type="submit" value="Ok" class="btn btn-outline-warning mt-3 px-5">
            <% if (error != null) { %>
                <h2><%=error %></h2>
            <% } %>
        </form>
    </div>
</body>
</html>