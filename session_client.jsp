<%@ page import="client.Client" %>
<%
    session.setAttribute("client", Client.getClientById(request.getParameter("id")));
    response.sendRedirect("reservation.jsp");
%>