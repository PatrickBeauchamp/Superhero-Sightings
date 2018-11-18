<%-- 
    Document   : addPowerPage
    Created on : Oct 22, 2018, 8:20:11 AM
    Author     : patty
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Powers</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <h1>Powers</h1>
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
                    <h2>Add a Power</h2>
                    <form class="form-horizontal" role="form" method="POST" action="addPower">
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Power Description</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="powerDescription" placeholder="Description of Power" required/>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Add Power"/>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
