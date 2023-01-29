<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String error = request.getParameter("error");
    String traitement = request.getParameter("traitement");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/css/bootstrap.css">
    <title>Login</title>
</head>
<body>
    <div class="container w-50 p-3 shadow rounded-3 mt-5">
        <form action="<%=traitement %>.jsp" method="get">
            <input type="text" name="nom" class="form-control">
            <input type="submit" value="Ok" class="btn btn-outline-warning mt-3 px-5">
            <% if (error != null) { %>
                <h2><%=error %></h2>
            <% } %>
        </form>
    </div>
</body>
</html>