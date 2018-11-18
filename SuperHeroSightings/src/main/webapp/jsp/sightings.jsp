<%-- 
    Document   : sightings
    Created on : Oct 21, 2018, 12:12:24 PM
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
                        <h2>List of Sightings</h2>
                        <table class="table table-bordered">
                            <tr>
                                <th>Date</th>
                                <th>Location</th>
                                <th>Mutant(s) Sighted</th>
                                <th>Show Details</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            <c:forEach items="${sightingsList}" var="sighting">
                                <tr>
                                    <td>${sighting.date}</td>
                                    <td>${sighting.location.name}</td>
                                    <td>
                                        <ul>
                                        <c:forEach items="${sighting.mutants}" var="mutant">
                                            <li>${mutant.name}</li>
                                        </c:forEach>
                                        </ul>
                                    </td>
                                    <td>
                                        <form action="detailsSighting" method="GET">
                                            <button type="submit" name="detailsSighting" value="${sighting.id}">Show Details</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="editSightingPage" method="GET">
                                            <button type="submit" name="editSightingId" value="${sighting.id}">Edit</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="deleteSighting" method="POST">
                                            <button type="submit" name="deleteSighting" value="${sighting.id}">Delete</button>
                                        </form>
                                    </td>
                                </c:forEach>  
                        </table>
                    </div>
                    <div class="col-md-4">
                        <br/>
                        <br/>
                        <br/>
                        <br/>
                        <form action="addSightingPage" method="GET">
                            <button style="border: 3px solid black" class="btn btn-block" type="submit">Add a Sighting</button>
                        </form>
                        <br/>
                        <br/>
                        <br/>
                        <br/>
                        <form action="sightingsByDate" method="GET">
                            Enter date to search for:<input class="form-control" type="date" name="date"/>
                            <button style="border: 3px solid black" class="btn btn-block" type="submit">Get Sightings By Date</button>
                        </form>
                    </div>

                </div>
            </div>

    </body>
</html>
