<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar {
            margin-bottom: 0;
            border-radius: 0;
        }

        /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        .row.content {height: 450px}

        /* Set gray background color and 100% height */
        .sidenav {
            padding-top: 20px;
            background-color: #f1f1f1;
            height: 100%;
        }

        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }

        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }
            .row.content {height:auto;}
        }
    </style>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Logo</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/secure/movies">Home</a></li>
                <li><a href="/secure/create.jsp">Create</a></li>
                <li><a href="/secure/users">Users</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/secure/logout"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<%--<div class="container">--%>
    <%--<c:if test="${succes_movie_create_msg != null}">--%>
    <%--<div class="alert alert-success">--%>
        <%--<c:out value="${succes_movie_create_msg}"/>--%>
    <%--</div>--%>
    <%--</c:if>--%>
    <form action="/secure/editUser/update" method="POST" role="form">
        <c:forEach items="${username}"/>
        <c:forEach items="${displayName}"/>
        <c:forEach items="${password}"/>
        <div class="form-group">
            <label for="username">Update Username:</label>
            <input type="text" class="form-group" id="username" name="username" value="<c:out value="${username}"/>">
        </div>

        <div class="form-group">
            <label for="displayName">Update Display Name:</label>
            <input type="text" class="form-group" id="displayName" name="displayName" value="<c:out value="${displayName}"/>">
        </div>
        <div class="form-group">
            <label for="password">Update Password:</label>
            <input type="text" class="form-group" id="password" name="password" value="<c:out value="${password}"/>">
        </div>
        <div class="form-group">
            <label for="id"></label>
            <input type="hidden" class="form-group" id="id" name="id" value="<c:out value="${userID}"/>">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>






    <footer class="container-fluid text-center">
        <p>Welcome To Ayub's Web App</p>
    </footer>

</body>
</html>
