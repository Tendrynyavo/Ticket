<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String error = request.getParameter("error");
%>
<%@ include file="header.jsp" %>
    <div class="container w-50 p-3 shadow rounded-3 mt-5">
        <h2>Ajouter Event</h2>
        <form action="ajouter_event.jsp" method="get">
            <input type="text" name="nom" class="form-control" placeholder="Nom">
            <input type="text" name="nombre" class="form-control mt-3" placeholder="Nombre">
            <input type="text" name="date" class="form-control mt-3" placeholder="Date">
            <input type="submit" value="Ok" class="btn btn-outline-warning mt-3 px-5">
            <% if (error != null) { %>
                <h2><%=error %></h2>
            <% } %>
        </form>
    </div>
</body>
</html>