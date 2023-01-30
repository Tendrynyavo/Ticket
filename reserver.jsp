<%@ page import="place.Place" %>
<%@ page import="client.Client" %>
<%
    Client client = (Client) session.getAttribute("client");
    try {
        client.reserver(request.getParameter("numeros"), request.getParameter("date"));
        response.sendRedirect("reservation.jsp");
    } catch (Exception e) {
        response.sendRedirect("reservation.jsp?error=" + e.getMessage());
    }
%>