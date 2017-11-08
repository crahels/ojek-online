<%--
  Created by IntelliJ IDEA.
  User: MASTER
  Date: 11/4/2017
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="example.*" %>
<%@ page import="example.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    application.setAttribute( "currentPage", "profile");
    String token;
    HttpSession sesi = request.getSession();
    int userId;
    example.User userOjek = null;
    Driver driverOjek = null;
    java.util.List<String> listLoc = null;
    if (sesi.getAttribute("token") != null) {
        userId = Integer.parseInt(sesi.getAttribute("userId").toString());
        java.util.List<example.User> userList = null;
        java.util.List<example.Driver> driverList = null;
        userOjek = new User();
        driverOjek = new Driver();
        ProfileService serviceProfile = new ProfileService();
        Profile portProfile = serviceProfile.getProfilePort();
        LocationService service = new LocationService();
        Location port = service.getLocationPort();
        try {
            userList = portProfile.getDataUser(userId);
            userOjek = userList.get(0);
            listLoc = port.getUserLocation(userId);
            driverOjek = portProfile.getDriverByID(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        userId = -1;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile - PR-OJEK</title>
    <link href="css/style.css" rel="stylesheet">
    <link rel="shortcut icon" href="favicon.png" type="image/x-icon">
    <link rel="icon" href="favicon.png" type="image/x-icon">
</head>

<%@include file="header.jsp"%>

<body>
<div class="container">
    <h2>My Profile
        <a class="pencil orange right" href="edit_profile.jsp"></a>
    </h2>
    <img class="img-circle" src="images/profile/<%out.print(userId);%>.png" alt="YOUR PROFILE PICTURE">
    <div class="user-info text-center">
        <div class="username">
            <% out.print(userOjek.getUsername());%>
        </div>

        <div class="user-details">
            <% out.print(userOjek.getName());%> </br>
            <%
                if (userOjek.getStatus().equals("0")) {
                    out.println("Driver | <span class=\"text-small orange\">&#9734;</span> <span class=\"orange\">" +
                    driverOjek.getRating() + " ( " + driverOjek.getVote());
                    if (driverOjek.getVote() > 1) {
                        out.print(" votes )");
                    } else {
                        out.print(" vote )");
                    }
                } else {
                    out.print("Non-Driver");
                }
            %>
            <br/>
            <span class="text-small">&#9993;</span> <%out.print(userOjek.getEmail());%> <br/>
            <span class="text-small">&#9743;</span> <%out.print(userOjek.getPhone());%>
        </div>
    </div>
    <%
            if (listLoc.size() > 0) {
    out.print("<h3>" +
    "Preferred Locations:" +
    "<a class='pencil orange text-large right' href='edit_preferred_location.jsp'></a>" +
"</h3>" +
    "<div class='preferred-location-list'>");
        for (String x : listLoc) {
            out.print("<ul>" + x);
        }
        for (String x : listLoc) {
            out.print("</ul>");
        }
        out.print("</div>");
    }
    %>
</div>
</body>
</html>