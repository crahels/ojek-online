<%@ page import="example.OrderGojekService" %>
<%@ page import="example.OrderGojek" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.util.Calendar" %><%--
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
    HttpSession sesi = request.getSession();
    int userId;
    String pickingPoint;
    String destination;
    String preferredDriver;
    String driverName;
    int driverId;
    if (sesi.getAttribute("token") != null) {
        String token = sesi.getAttribute("token").toString();
        userId = Integer.parseInt(sesi.getAttribute("userId").toString());
        pickingPoint = request.getParameter("pickingPoint");
        destination = request.getParameter("destination");
        preferredDriver = request.getParameter("preferredDriver");
        driverName = request.getParameter("userName");
        driverId = Integer.parseInt(request.getParameter("driverId"));

        OrderGojekService service = new OrderGojekService();
        OrderGojek port = service.getOrderGojekPort();

        try {
            if (request.getParameter("complete") != null) {
                int rating = Integer.parseInt(request.getParameter("star"));
                String comment = request.getParameter("comment");
                boolean result = port.addOrderToDatabase(token, pickingPoint, destination, userId, driverId, rating, comment);
                if (result) {
                    response.sendRedirect("order_gojek.jsp");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        userId = -1;
        pickingPoint = "";
        destination = "";
        preferredDriver = "";
        driverName = "";
        driverId = -1;
    }
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
    <img src="images/profile/<%=userId%>.png" alt='DRIVER PICTURE' class="img-circle"></img>
    <p class="username-driver">@<% out.print(preferredDriver); %></p>
    <p class="name-driver"><% out.print(driverName); %></p>

    <form action="" method="post" onsubmit="return validateCompleteOrder()">
        <input type="hidden" id="picking_point" name="picking_point" value=<% out.print(pickingPoint); %>>
        <input type="hidden" id="destination" name="destination" value=<% out.print(destination); %>>
        <input type="hidden" id="driver_id" name="driver_id" value=<% out.print(driverId); %>>
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
