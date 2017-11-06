<%--
  Created by IntelliJ IDEA.
  User: MARCELLINO
  Date: 06/11/2017
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    application.setAttribute("currentPage","order");
    application.setAttribute("currentSubPage","complete");
    String pickingPoint = request.getParameter("pickingPoint");
    String destination = request.getParameter("destination");
    String preferredDriver = request.getParameter("preferredDriver");
    int driverId = Integer.parseInt(request.getParameter("driverId"));

    HttpSession sesi = request.getSession();
    String token;
    sesi.setAttribute("token","dummy"); /* need to be replaced */
    token = sesi.getAttribute("token").toString();
    if (token == null) {
        response.sendRedirect("login.jsp");
    }
    int userId = 0; /* dummy */
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Order - Complete Order - PR-OJEK</title>
    <link href="css/style.css" rel="stylesheet">
    <link rel="shortcut icon" href="favicon.png" type="image/x-icon">
    <link rel="icon" href="favicon.png" type="image/x-icon">
</head>

<%@include file="header.jsp"%>

<body>
<div class="container">
    <%@include file="order_header.jsp"%>
    <h3>How Was It?</h3>
    <img src="images/profile/default.png" class="img-circle"></img>
    <p class="username-driver">@<?php echo $driver->username?></p>
    <p class="name-driver"><?php echo $driver->name?></p>

    <form action="" method="post" onsubmit="return validateCompleteOrder()">
        <input type="hidden" id="picking_point" name="picking_point" value=<?php echo $_GET['picking_point'];?>>
        <input type="hidden" id="destination" name="destination" value=<?php echo $_GET['destination'];?>>
        <input type="hidden" id="driver_id" name="driver_id" value=<?php echo $_GET['driver_id'];?>>
        <div class="stars">
            <input class="stars star-5" id="star-5" type="radio" name="star" value=5>
            <label class="stars star-5" for="star-5"></label>
            <input class="stars star-4" id="star-4" type="radio" name="star" value=4>
            <label class="stars star-4" for="star-4"></label>
            <input class="stars star-3" id="star-3" type="radio" name="star" value=3>
            <label class="stars star-3" for="star-3"></label>
            <input class="stars star-2" id="star-2" type="radio" name="star" value=2>
            <label class="stars star-2" for="star-2"></label>
            <input class="stars star-1" id="star-1" type="radio" name="star" value=1>
            <label class="stars star-1" for="star-1"></label>
        </div>

        <div class="form-comment">
            <textarea rows="2" cols ="72" id="comment" name="comment" placeholder="Your comment..."></textarea>
            <input class="button-comment right" type="submit" name="complete" value="COMPLETE ORDER">
        </div>
    </form>
</div>

<script type="text/javascript" src="js/order_validation.js"></script>
</body>
