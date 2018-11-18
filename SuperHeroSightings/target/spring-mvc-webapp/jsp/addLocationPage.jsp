<%-- 
    Document   : addLocationPage
    Created on : Oct 22, 2018, 9:16:56 AM
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
                Add a Location
            </h2>
            <form class="form-horizontal" role="form" method="POST" action="addLocation">
                <div class="form-group">
                    <label for="add-name" class="col-md-4 control-label">Location Name: </label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="name" placeholder="Location Name" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-description" class="col-md-4 control-label">Description: </label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="description" placeholder="Description" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-street" class="col-md-4 control-label">Street Address: </label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="street" placeholder="Street Address" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-city" class="col-md-4 control-label">City: </label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="city" placeholder="City" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-state" class="col-md-4 control-label">State: </label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="state" placeholder="State" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-state" class="col-md-4 control-label">Postal Code: </label>
                    <div class="col-md-8">
                        <input type="number" class="form-control" name="zip" placeholder="zip" required/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="add-latitude" class="col-md-4 control-label">Latitude: </label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="latitude" placeholder="Latitude" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-longitude" class="col-md-4 control-label">Longitude: </label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="longitude" placeholder="Longitude" required/>
                    </div>
                </div>
                <p class="danger">${message}</p>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Add Location"/>
                    </div>
                </div>

            </form>
        </div>
    </body>
</html>
