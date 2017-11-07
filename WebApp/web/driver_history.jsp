<%@ page import="example.HistoryGojek" %>
<%@ page import="example.HistoryGojekService" %>
<%@ page import="example.UserDriverHistory" %>
<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 11/7/2017
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    application.setAttribute("currentPage","history");
    application.setAttribute("specificPage","driver-history");

    HttpSession sesi = request.getSession();
    sesi.setAttribute("token","dummy"); /* need to be replaced */
    String token = sesi.getAttribute("token").toString();
    if (token == null) {
        response.sendRedirect("login.jsp");
    }

    //int userId = Integer.parseInt(sesi.getAttribute("userId").toString());
    int userId = 1;

    HistoryGojekService service = new HistoryGojekService();
    HistoryGojek port = service.getHistoryGojekPort();
    java.util.List<UserDriverHistory> passengerList = null;

    boolean result = port.checkExpiryTime(token);
    if (!result) {
        try {
            passengerList = port.getDriverHistory(userId);
            if(request.getParameter("hide") != null) {
                port.hidePassanger(Integer.parseInt(request.getParameter("hide")));
                response.sendRedirect("driver_history.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        response.sendRedirect("login.jsp");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Driver History - PR-OJEK</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="shortcut icon" href="favicon.png" type="image/x-icon">
    <link rel="icon" href="favicon.png" type="image/x-icon">
</head>

<%@include file="header.jsp"%>

<body>
<div class="container">
    <%@include file="history_header.jsp"%>
    <%
        if(passengerList.size() > 0) {
            for (UserDriverHistory x : passengerList) {
                out.println("<table class=\"table-select-driver margin-driver-history\">" +
                        "<tr>" +
                        "<td>" +
                        "<img class='img-driver-pic' src='images/profile/" + userId + ".png' alt='CUSTOMER PICTURE'>" +
                        "</td>" +
                        "<td class=\"driver-history-setting\">" +
                        "<a href='driver_history.jsp?hide=" + String.valueOf(x.getOrderId()) +"'><button class=\"button-hide right\" " +
                        "type=\"submit\" name=\"hide_button\" value=\"" + x.getOrderId() + "\">HIDE</button></a>" +
                        "<p class=\"light-grey font-15\">" + x.getDate() + "</p>" +
                        "<p class=\"name-history\">" + x.getName() + "</p>" +
                        "<div class=\"history-details\">" +
                        "<p>" + x.getPickingPoint() + " &#x2192; " + x.getDestination() + "</p>" +
                        "<p>gave <span class=\"orange font-16\">" + x.getRating() + "</span> stars for this order</p>" +
                        "<p>and left comment:</p>" +
                        "<p>&emsp;" + x.getComment() + "</p>" +
                        "</div>" +
                        "</td>" +
                        "</tr>" +
                        "</table>"
                );
            }
        }
    %>
</div>
<script type="text/javascript" src="js/history.js"></script>
</body>
</html>
