git pull<%-- 
    Document   : mutants
    Created on : Oct 21, 2018, 12:12:13 PM
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
            <div class="row">
                <div class="col-md-8">
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
                        List of Mutants
                    </h2>
                    <table class="table table-bordered" style="width:70%">
                        <tr>
                            <th>Name</th>
                            <th>Show Details</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                        <c:forEach items="${mutantsList}" var="mutant">
                            <tr>
                                <td> ${mutant.name}</td>


                                <td>
                                    <form action="detailsMutant" method="GET">
                                        <button type="submit" name="detailsMutant" value="${mutant.id}">Show Details</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="editMutantPage" method="POST">
                                        <button type="submit" name="editMutantId" value="${mutant.id}">Edit</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="deleteMutant" method="POST">
                                        <button type="submit" name="deleteMutant" value="${mutant.id}">Delete</button>
                                    </form>
                                </td>

                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col-md-4">

                    <form action="addMutantPage" method="GET">
                        <button style="border:solid 3px black;height:200px" class="btn btn-block" type="submit">Add a Mutant</button>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
