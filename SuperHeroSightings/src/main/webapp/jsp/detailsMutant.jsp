<%-- 
    Document   : detailsMutant
    Created on : Oct 22, 2018, 6:46:23 AM
    Author     : patty
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Mutants</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
    </head>
    <body>
        <div class="container">
            <h1>Mutants</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/lastTenSightings">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/powers">Powers</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/mutants">Mutants</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                </ul>    
            </div>
            <h2>
                Mutant Details
            </h2>
            <p>Mutant Name: ${mutant.name}</p>
            <p>Description: ${mutant.description}</p>
            <p>Powers</p>
            <ul>
                <c:forEach items="${mutant.powers}" var="power">
                    <li>${power.description}</li>
                    </c:forEach>
            </ul>
            <p>Organizations</p>
            <ul>
                <c:forEach items="${mutant.orgs}" var="org">
                    <li>${org.name}</li>
                    </c:forEach>
            </ul>
            <p>Locations where ${mutant.name} has been sighted</p>
            <ul>
                <c:forEach items="${locations}" var="location">
                    <p>Location Name: ${location.name}</p>
                    <p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    City: ${location.city}</p>
                    <p>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   State: ${location.state}</p>
                    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Latitude: ${location.latitude}</p>
                    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Longitude: ${location.longitude}</p>
                </c:forEach>
            </ul>
            <br/>
            <br/>
            <a href="mutants">Return to Mutants Page</a>
        </div>
    </body>
</html>
