<%@ page import="client.Client" %>
<%
    try {
        Client client = new Client(request.getParameter("nom"));
        client.insert(null);
        response.sendRedirect("index.jsp");
    } catch (Exception e) {
        response.sendRedirect("liste_client.jsp?error="+e.getMessage());
    }
%>