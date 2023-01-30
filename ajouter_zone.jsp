<%@ page import="zone.Zone" %>
<%
    try {
        Zone zone = new Zone(request.getParameter("nom"), request.getParameter("prix"));
        zone.insert(null);
        response.sendRedirect("formulaire_zone.jsp");
    } catch (Exception e) {
        response.sendRedirect("formulaire_zone.jsp&&error=" + e.getMessage());
    }
%>