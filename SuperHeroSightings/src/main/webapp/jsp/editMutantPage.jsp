<%-- 
    Document   : editMutantPage
    Created on : Oct 28, 2018, 12:02:23 PM
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
                Edit Mutant
            </h2>
            <form class="form-horizontal" role="form" method="POST" action="editMutant">
                <div class="form-group">
                    <label for="add-name" class="col-md-4 control-label">Name: </label>
                    <div class="col-md-8">
                        <input type="hidden" value="${mutant.id}" name="editMutantId"/>
                        <input type="text" class="form-control" name="name" placeholder="${mutant.name}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-description" class="col-md-4 control-label">Description: </label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="description" placeholder="${mutant.description}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-power" class="col-md-4 control-label">Edit Power(s): </label>
                    <div class="col-md-8">
                        <select class="selectpicker" multiple data-live-search="true" name="powers">
                            <c:forEach items="${powers}" var="power">
                                <option value="${power.id}">${power.description}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-org" class="col-md-4 control-label">Edit Organization(s): </label>
                    <div class="col-md-8">
                        <select class="selectpicker" multiple data-live-search="true" name="orgs">
                            <c:forEach items="${orgs}" var="org">
                                <option value="${org.id}">${org.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>



                <p class="danger">${message}</p>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Edit Mutant"/>
                    </div>
                </div>

            </form>
        </div>
    </body>
</html>
