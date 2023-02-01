<%@ page import="event.Evenement" %>
<%
    try {
        Evenement evenement = new Evenement(request.getParameter("nom"), request.getParameter("nombre"),request.getParameter("date"));
        evenement.insert(null);
        response.sendRedirect("formulaire_event.jsp");
    } catch (Exception e) {
        response.sendRedirect("formulaire_event.jsp?error=" + e.getMessage());
    }
%>