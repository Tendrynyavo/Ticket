<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="zone.Zone" %>
<%@ page import="event.Evenement" %>
<%@ page import="place.Place" %>
<%
    Zone[] zones = null;
    Evenement[] events = null;
    Place[] places = null;
    try {
        events = Evenement.getAllEvenements();
        zones = Zone.getAllZones();
        places = Place.getAllPlaces();
    } catch (Exception e) {
        out.print(e.getMessage());
    }
%>
<%@ include file="header.jsp" %>
    <div class="container w-50 p-3 shadow rounded-3 mt-5">
        <form action="ajouter_placement.jsp" method="get">
            <div class="">
                <select class="form-select" name="zone">
                    <% for (Zone zone : zones) { %>
                    <option value="<%=zone.getIdZone() %>"><%=zone.getNom() %></option>
                    <% } %>
                </select>
            </div>
            <div class="mt-4">    
                <select class="form-select" name="event">
                    <% for (Evenement event : events) { %>
                    <option value="<%=event.getIdEvenement() %>"><%=event.getNom() %></option>
                    <% } %>
                </select>
            </div>
            <div class="mt-4">    
                <select class="form-select" name="place">
                    <% for (Place place : places) { %>
                    <option value="<%=place.getIdPlace() %>"><%=place.getNumero() %></option>
                    <% } %>
                </select>
            </div>
            <input type="submit" value="Ok" class="btn btn-outline-warning mt-3 px-5">
        </form>
    </div>
</body>
</html>