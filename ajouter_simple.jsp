<%@ page import="client.Client" %>
<%
    Client client = (Client) session.getAttribute("client");
    try {
        client.reserverSimple(request.getParameter("nombre"));
        response.sendRedirect("reservation_simple.jsp");
    } catch (Exception e) {
        response.sendRedirect("reservation_simple.jsp?error=" + e.getMessage());
    }
%>