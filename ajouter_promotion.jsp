<%@ page import="payement.Promotion" %>
<%
    try {
        Promotion promotion = new Promotion(request.getParameter("debut"), request.getParameter("fin"), request.getParameter("pourcentage"), request.getParameter("zone"), request.getParameter("event"));
        promotion.insert(null);
        response.sendRedirect("formulaire_promotion.jsp");
    } catch (Exception e) {
        response.sendRedirect("formulaire_promotion.jsp?error=" + e.getMessage());
    }
%>