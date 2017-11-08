<%@ page import="example.OrderGojekService" %>
<%@ page import="example.OrderGojek" %>
<%@ page import="example.Driver" %>
<%--
  Created by IntelliJ IDEA.
  User: MARCELLINO
  Date: 05/11/2017
  Time: 5:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@include file="UserController.jsp"%>--%>
<%
    application.setAttribute("currentPage","order");
    application.setAttribute("currentSubPage","driver");

    HttpSession sesi = request.getSession();
    int userId;
    String pickingPoint;
    String destination;
    String preferredDriver;

    OrderGojekService service = new OrderGojekService();
    OrderGojek port = service.getOrderGojekPort();
    java.util.List<example.Driver> driver = null;
    java.util.List<example.Driver> otherDriver = null;
    if (sesi.getAttribute("token") != null) {
        String token = sesi.getAttribute("token").toString();
        userId = Integer.parseInt(sesi.getAttribute("userId").toString());
        pickingPoint = sesi.getAttribute("pickingPoint").toString();
        destination = sesi.getAttribute("destination").toString();
        preferredDriver = sesi.getAttribute("preferredDriver").toString();
        try {
            driver = port.getPreferredDrivers(true, userId, pickingPoint, destination, preferredDriver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            otherDriver = port.getPreferredDrivers(false, userId, pickingPoint, destination, preferredDriver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    else {
        userId = -1;
        pickingPoint = "";
        destination = "";
        preferredDriver = "";
    }

%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Order - Select Driver - PR-OJEK</title>
    <link href="css/style.css" rel="stylesheet">
    <link rel="shortcut icon" href="favicon.png" type="image/x-icon">
    <link rel="icon" href="favicon.png" type="image/x-icon">
</head>

<%@include file="header.jsp"%>

<body>
<div class="container dark-grey">
    <%@include file="order_header.jsp"%>
    <div class="select-driver-border">
        <h1>Preferred Drivers:</h1>
        <%
				if(driver.size() > 0) {
				    for (Driver x : driver) {
						out.println(" <table class='table-select-driver'>" +
        "<tr>" +
            "<td>" +
                "<img class='img-driver-pic' src='images/profile/" + x.getProfPic() + "' alt='DRIVER PICTURE'>" +
            "</td>" +
            "<td>" +
                "<p class='driver-name'>" + x.getUserName() + "</p>" +
                "<p class='star'><span class='orange'>&#10025;" + x.getRating() + "</span>(" + x.getVote ());
                    if(x.getVote() > 1) {
                        out.print(" votes)");
                    } else {
                        out.print(" vote)");
                    }
                    out.print("</p>" +
                "<a href='complete_order.jsp?pickingPoint=" + pickingPoint + "&userName=" + x.getUserName() + "&destination=" + destination + "&preferredDriver=" + x.getUserName() + "&driverId=" + x.getUserId() + "'><input class='button-i-choose-you right' type='button' value='I CHOOSE YOU!!'> </a>" +
            "</td>" +
        "</tr>" +
        "</table>");
        }
        } else {
        out.print("<p class='align-center nothing-to-display-margin'>Nothing to display :(</p>");
        } %>
    </div>

    <div class="select-driver-border dark-grey">
        <h1>Other Drivers:</h1>
        <%
				if(otherDriver.size() > 0) {
					for (example.Driver x : otherDriver) {
						out.print("<table class='table-select-driver'>" +
        "<tr>" +
            "<td>" +
                "<img class='img-driver-pic' src='images/profile/" + x.getProfPic() + "' alt='DRIVER PICTURE'>" +
            "</td>" +
            "<td>" +
                "<p class='driver-name'>" + x.getUserName() + "</p>" +
                "<p class='star'><span class='orange'>&#10025; " + x.getRating() + "</span> (" + x.getVote());
                    if (x.getVote() > 1) {
                    out.print(" votes)");
                    } else {
                    out.print(" vote)");
                    }
                    out.print("</p>" +
                "<a href='complete_order.jsp?pickingPoint=" + pickingPoint + "&userName=" + x.getUserName() + "&destination=" + destination + "&preferredDriver=" + x.getUserUsername() + "&driverId=" + x.getUserId() + "'><input class='button-i-choose-you right' type='submit' value='I CHOOSE YOU!!'></a>" +
            "</td>" +
        "</tr>" +
        "</table>");
        }
        } else {
        out.print("<p class='align-center nothing-to-display-margin'>Nothing to display :(</p>");
        } %>
    </div>
</div>
</body>
</html>

