<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Locations</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
    </head>
    <body>

        <div class="container">

            <h1>Locations</h1>
            <hr/>


            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/lastTenSightings">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/powers">Powers</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/mutants">Mutants</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                </ul>    
            </div>
            <h2>
                List of Locations
            </h2>
                
                <form action="addLocationPage" method="GET">
                    <button style="border: 3px solid black" class="btn btn-block" type="submit">Add a Location</button>
                </form>
          

            <table class="table table-bordered">
                <tr>
                    <th>Location Name</th>
                    <th>Latitude</th>
                    <th>Longitude</th>
                    <th>Show Details</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <c:forEach items="${locationsList}" var="location">
                    <tr>
                        <td>${location.name}</td>
                        <td>${location.latitude}</td>
                        <td>${location.longitude}</td>
                        <td>
                            <form action="detailsLocation" method="GET">
                                <button type="submit" name="detailsLocation" value="${location.id}">Show Details</button>
                            </form>
                        </td>
                        <td>
                            <form action="editLocationPage" method="GET">
                                <button type="submit" name="editLocation" value="${location.id}">Edit</button>
                            </form>
                        </td>
                        <td>
                            <form action="deleteLocation" method="POST">
                                <button type="submit" name="deleteLocation" value="${location.id}">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            

        </div>


    </body>
</html>
