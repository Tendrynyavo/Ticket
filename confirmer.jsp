<%@ page import="reservation.Reservation" %>
<%
    try {
        Reservation reservation = Reservation.getReservationById(request.getParameter("id"));
        reservation.confirme();
        response.sendRedirect("liste_reservation.jsp");
    } catch (Exception e) {
        response.sendRedirect("liste_reservation.jsp?error="+e.getMessage());
    }
%>