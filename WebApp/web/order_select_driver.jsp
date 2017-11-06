<%@ page import="example.OrderGojekService" %>
<%@ page import="example.OrderGojek" %>
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
    String token;
    sesi.setAttribute("token","dummy"); /* need to be replaced */
    token = sesi.getAttribute("token").toString();
    if (token == null) {
        response.sendRedirect("login.jsp");
    }
    String pickingPoint = sesi.getAttribute("pickingPoint").toString();
    String destination = sesi.getAttribute("destination").toString();
    String preferredDriver = sesi.getAttribute("preferredDriver").toString();
    OrderGojekService service = new OrderGojekService();
    OrderGojek port = service.getOrderGojekPort();
    boolean result = port.checkExpiryTime(token);
    java.util.List<example.Driver> driver = null;
    if (!result) {
        sesi.setAttribute("pickingPoint", pickingPoint);
        sesi.setAttribute("destination", destination);
        sesi.setAttribute("preferredDriver", preferredDriver);
        // database
        try {
            driver = port.getPreferredDrivers(pickingPoint, destination, preferredDriver);
            for (example.Driver x : driver){
                out.println(x.getUserName());
                out.println(x.getRating());
            }
        } catch (Exception e) {
        }
        //out.println(dummy + "hehe");
        //response.sendRedirect("complete_order.jsp");
        //$preferredDrivers = $user->getPreferredDrivers($pickingPoint, $destination, $name);
        //$otherDrivers = $user->getOtherDrivers($pickingPoint, $destination, $name);
    } else {
        response.sendRedirect("login.jsp");
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
				    for (example.Driver x : driver) {
						out.println(" <table class='table-select-driver'>" +
        "<tr>" +
            "<td>" +
                "<img class='img-driver-pic' src='images/profile/" + x.getProfPic() + "' alt='DRIVER PICTURE'>" +
            "</td>" +
            "<td>" +
                "<p class='driver-name'>" + x.getUserName() + "</p>" +
                "<p class='star'><span class='orange'>&#10025; ' . $preferredDriver['ratings'] . '</span> (' . $preferredDriver['votes'];");
                    if(x.getVote() > 1){
                     ' votes)';
                    } else {
                    echo ' vote)';
                    }
                    echo '</p>
                <a href="complete_order.php?id_active=' . $user->id . '&picking_point=' . $pickingPoint . '&destination=' . $destination . '&preferred_driver=' . $name . '&driver_id=' . $preferredDriver['id'] . '"><input class="button-i-choose-you right" type="button" value="I CHOOSE YOU!!"> </a>
            </td>
        </tr>
        </table>");
        }
        } else {
        echo '<p class="align-center nothing-to-display-margin">Nothing to display :(</p>';
        }
        --%>
    </div>

    <div class="select-driver-border dark-grey">
        <h1>Other Drivers:</h1>
        <%--?php
				if($otherDrivers) {
					foreach ($otherDrivers as $otherDriver) {
						echo '
						<table class="table-select-driver">
        <tr>
            <td>
                <img class="img-driver-pic" src="../assets/images/profile/' . $otherDriver['profile_picture'] . '" alt="DRIVER PICTURE">
            </td>
            <td>
                <p class="driver-name">' . $otherDriver['name'] . '</p>
                <p class="star"><span class="orange">&#10025; ' . $otherDriver['ratings'] . '</span> (' . $otherDriver['votes'];
                    if($otherDriver['votes'] > 1){
                    echo ' votes)';
                    } else {
                    echo ' vote)';
                    }
                    echo '</p>
                <a href="complete_order.php?id_active=' . $user->id . '&picking_point=' . $pickingPoint . '&destination=' . $destination . '&preferred_driver=' . $name . '&driver_id=' . $otherDriver['id'] . '"><input class="button-i-choose-you right" type="submit" value="I CHOOSE YOU!!"></a>
            </td>
        </tr>
        </table>';
        }
        } else {
        echo '<p class="align-center nothing-to-display-margin">Nothing to display :(</p>';
        }
        --%>
    </div>
</div>
</body>
</html>

