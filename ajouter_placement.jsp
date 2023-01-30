<%@ page import="place.Placement" %>
<%
    try {
        Placement placement = new Placement(request.getParameter("event"), request.getParameter("zone"), request.getParameter("place"));
        placement.insert(null);
        response.sendRedirect("placement.jsp");
    } catch (Exception e) {
        response.sendRedirect("placement.jsp?error=" + e.getMessage());
    }
%>