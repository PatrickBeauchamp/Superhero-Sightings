<%-- 
    Document   : addMutantPage
    Created on : Oct 23, 2018, 7:15:03 AM
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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/mutants">Mutants</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                </ul>    
            </div>
            <h2>
                Add a Mutant
            </h2>
            <form class="form-horizontal" role="form" method="POST" action="addMutant">
                <div class="form-group">
                    <label for="add-name" class="col-md-4 control-label">Name: </label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="name" placeholder="Name" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-description" class="col-md-4 control-label">Description: </label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="description" placeholder="Description" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-power" class="col-md-4 control-label">Add Power: </label>
                    <div class="col-md-8">
                        <select class="selectpicker" multiple data-live-search="true" name="powers" required>
                            <c:forEach items="${powers}" var="power">
                                <option value="${power.id}">${power.description}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-org" class="col-md-4 control-label">Add Organization: </label>
                    <div class="col-md-8">
                        <select class="selectpicker" multiple data-live-search="true" name="orgs" required>
                            <c:forEach items="${orgs}" var="org">
                                <option value="${org.id}">${org.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>



                <p class="danger">${message}</p>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Add Mutant"/>
                    </div>
                </div>

            </form>
        </div>
    </body>
</html>
