<%-- 
    Document   : detailsLocation
    Created on : Oct 22, 2018, 6:26:16 AM
    Author     : patty
--%>

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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/mutants">Mutants</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                </ul>    
            </div>
            <h2>
                Location Details
            </h2>
            <p>Location Name: ${location.name}</p>
            <p>Description: ${location.description}</p>
            <p>Street: ${location.street}</p>
            <p>City: ${location.city}</p>
            <p>State: ${location.state}</p>
            <p>Postal Code: ${location.zip}</p>
            <p>Latitude: ${location.latitude}</p>
            <p>Longitude: ${location.longitude}</p>
            <ul>
                Mutants Sighted at this Location
                <c:forEach items="${mutants}" var="mutant">
                    <li>${mutant.name}</li>
                </c:forEach>
            </ul>
            <br/>
            <br/>
            <a href="locations">Return to Locations Page</a>
        </div>
</html>
