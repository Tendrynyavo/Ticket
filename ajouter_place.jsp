<%@ page import="place.Place" %>
<%
    try {
        Place place = new Place(request.getParameter("nom"));
        place.insert(null);
        response.sendRedirect("formulaire_place.jsp");
    } catch (Exception e) {
        response.sendRedirect("formulaire_place.jsp?error=" + e.getMessage());
    }
%>