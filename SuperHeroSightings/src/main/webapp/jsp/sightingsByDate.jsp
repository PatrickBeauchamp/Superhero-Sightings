<%-- 
    Document   : sightingsByDate
    Created on : Oct 28, 2018, 1:16:37 PM
    Author     : patty
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sightings</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
    </head>
    <body>
        <div class="container">

            <h1>Sightings</h1>
            <hr/>
            <div class="row">
                <div class="col-md-8">
                    <div class="navbar">
                        <ul class="nav nav-tabs">
                            <li role="presentation"><a href="${pageContext.request.contextPath}/lastTenSightings">Home</a></li>
                            <li role="presentation"><a href="${pageContext.request.contextPath}/powers">Powers</a></li>
                            <li role="presentation"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                            <li role="presentation"><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                            <li role="presentation"><a href="${pageContext.request.contextPath}/mutants">Mutants</a></li>
                            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                        </ul>    
                    </div>
                    <h2>Sightings By Date</h2>
                    <h3>${message}</h3>
                    <table class="table table-bordered">
                        <tr>

                            <th>Location</th>
                            <th>Mutant(s) Sighted</th>

                        </tr>
                        <c:forEach items="${sightingsList}" var="sighting">
                            <tr>

                                <td>${sighting.location.name}
                                    <ul>
                                        <li>Street: ${sighting.location.street}</li>
                                        <li>City: ${sighting.location.city}</li>
                                        <li>State: ${sighting.location.state}</li>
                                    </ul>
                                </td>

                                <td>
                                    <ul>
                                        <c:forEach items="${sighting.mutants}" var="mutant">
                                            <li>${mutant.name}</li>
                                            </c:forEach>
                                    </ul>
                                </td>

                            </c:forEach>  
                    </table>
                </div>
                <div class="col-md-4">
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <a href="sightings">Return to Sightings List</a>
                </div>

            </div>
        </div>

    </body>
</html>
