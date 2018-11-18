<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Organizations</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
    </head>
    <body>
        <div class="container">

                <h1>Organizations</h1>
                <hr/>
                <div class="row">
                    <div class="col-md-8">
                        <div class="navbar">
                            <ul class="nav nav-tabs">
                                <li role="presentation"><a href="${pageContext.request.contextPath}/lastTenSightings">Home</a></li>
                                <li role="presentation"><a href="${pageContext.request.contextPath}/powers">Powers</a></li>
                                <li role="presentation"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                                <li role="presentation"><a href="${pageContext.request.contextPath}/mutants">Mutants</a></li>
                                <li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                            </ul>    
                        </div>
                        <h2>
                            List of Organizations
                        </h2>
                        <table class="table table-bordered" style="width: 70%">
                            <tr>
                                <th>Name</th>
                                <th>Show Details</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            <c:forEach items="${orgsList}" var="org">
                                <tr>
                                    <td>${org.name}</td>
                                    <td>
                                        <form action="detailsOrg" method="GET">
                                            <button type="submit" name="detailsOrg" value="${org.id}">Show Details</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="editOrgPage" method="GET">
                                            <button type="submit" name="editOrgId" value="${org.id}">Edit</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="deleteOrg" method="POST">
                                            <button type="submit" name="deleteOrg" value="${org.id}">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="col-md-4">
                        <div>
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                        </div>
                        <form action="addOrgPage" method="GET">
                            <button style="height:100px; border:3px solid black"class="btn btn-block" type="submit">Add an Organization</button>
                        </form>
                    </div>

                </div>
            </div>

    </body>
</html>
