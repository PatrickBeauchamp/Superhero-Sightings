<%-- 
    Document   : detailsSighting
    Created on : Oct 22, 2018, 7:58:46 AM
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
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/lastTenSightings">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/powers">Powers</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/mutants">Mutants</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                </ul>    
            </div>
            <h2>Sighting Details</h2>
            <p>Mutants Sighted</p>
            <ul>

                <c:forEach items="${sighting.mutants}" var="mutant">
                    <li>${mutant.name}</li>
                    </c:forEach>
            </ul>
            <p>Date of Sighting: ${sighting.date}</p>
            <p>Location Name: ${sighting.location.name}</p>
            <p>Description: ${sighting.location.description}</p>
            <p>Street: ${sighting.location.street}</p>
            <p>City: ${sighting.location.city}</p>
            <p>State: ${sighting.location.state}</p>
            <p>Postal Code: ${sighting.location.zip}</p>
            <p>Latitude: ${sighting.location.latitude}</p>
            <p>Longitude: ${sighting.location.longitude}</p>
            <br/>
            <br/>
            <a href="sightings">Return to Sightings Page</a>
    </body>
</html>
