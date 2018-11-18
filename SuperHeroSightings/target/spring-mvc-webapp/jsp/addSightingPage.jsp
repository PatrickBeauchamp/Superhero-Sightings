<%-- 
    Document   : addSightingPage
    Created on : Oct 23, 2018, 12:51:58 PM
    Author     : patty
--%>

<<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h2>Add a Sighting</h2>
                <form class="form-horizontal" role="form" method="POST" action="addSighting">
                <div class="form-group">
                    <label for="add-date" class="col-md-4 control-label">Date of Sighting: </label>
                    <div class="col-md-8">
                        <input type="date" class="form-control" name="date" placeholder="Date" required/>
                    </div>
                </div>
                
                <div class="form-group">
                    <p>If the mutant you sighted is not in the drop-down, you must add that mutant first.</p>
                    <label for="add-mutant" class="col-md-4 control-label">Add Mutant: </label>
                    <div class="col-md-8">
                        <select class="selectpicker" multiple data-live-search="true" name="mutants" required>
                            <c:forEach items="${mutants}" var="mutant">
                                <option value="${mutant.id}">${mutant.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <p>If the location of your sighting is not in the drop-down, you must add that location first. </p>
                    <label for="add-location" class="col-md-4 control-label">Add Location: </label>
                    <div class="col-md-8">
                        <select class="selectpicker" data-live-search="true" name="location" required>
                            <c:forEach items="${locations}" var="location">
                                <option value="${location.id}">${location.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>



                <p class="danger">${message}</p>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Add Sighting"/>
                    </div>
                </div>

            </form>
        </div>
    </body>
</html>
