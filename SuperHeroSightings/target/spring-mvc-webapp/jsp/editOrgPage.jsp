<%-- 
    Document   : editOrgPage
    Created on : Oct 24, 2018, 10:32:59 AM
    Author     : patty
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Organizations</title>
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
                    <h1>Organizations</h1>
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
                    <h2>Edit an Organization</h2>
                    <form class="form-horizontal" role="form" method="POST" action="editOrg">
                        <input type="hidden" name="orgId" value="${org.id}"/>
                        <div class="form-group">
                            <label for="add-name" class="col-md-4 control-label">Organization Name: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="name" placeholder="${org.name}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="description" placeholder="${org.description}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-street" class="col-md-4 control-label">Street Address: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="street" placeholder="${org.street}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-city" class="col-md-4 control-label">City: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="city" placeholder="${org.city}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-state" class="col-md-4 control-label">State: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="state" placeholder="${org.state}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-zip" class="col-md-4 control-label">Postal Code: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="zip" placeholder="${org.zip}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-phone" class="col-md-4 control-label">Phone: </label>
                            <div class="col-md-8">
                                <input type="tel" class="form-control" name="phone" placeholder="${org.phone}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-email" class="col-md-4 control-label">Email: </label>
                            <div class="col-md-8">
                                <input type="email" class="form-control" name="email" placeholder="${org.email}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Edit Organization"/>
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
