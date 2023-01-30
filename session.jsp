<%@ page import="client.Client" %>
<%@ page import="event.Evenement" %>
<%
    Client client = Client.getClientById(request.getParameter("client"));
    Evenement event = Evenement.getEventById(request.getParameter("event"));
    client.setEvenement(event);
    session.setAttribute("client", client);
    response.sendRedirect("reservation.jsp");
%>