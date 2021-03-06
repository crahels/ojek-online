<%@ page import="example.OrderGojekService" %>
<%@ page import="example.OrderGojek" %>
<%--
  Created by IntelliJ IDEA.
  User: Rachel
  Date: 05/11/2017
  Time: 5:33
  To change this template use File | Settings | File Templates.
--%>
<%  application.setAttribute( "currentPage", "order");
    application.setAttribute("currentSubPage","destination");

    HttpSession sesi = request.getSession();

    if (request.getParameter("submit") != null) {
        String pickingPoint = request.getParameter("picking_point");
        String destination = request.getParameter("destination");
        String preferredDriver = request.getParameter("preferred_driver");

        OrderGojekService service = new OrderGojekService();
        OrderGojek port = service.getOrderGojekPort();

        sesi.setAttribute("pickingPoint", pickingPoint);
        sesi.setAttribute("destination", destination);
        sesi.setAttribute("preferredDriver", preferredDriver);
        response.sendRedirect("order_select_driver.jsp");
    }
%>

<html>
<head>
    <meta charset="utf-8">
    <title>Order - Select Destination - PR-OJEK</title>
    <link href="css/style.css" rel="stylesheet">
    <link rel="shortcut icon" href="favicon.png" type="image/x-icon">
    <link rel="icon" href="favicon.png" type="image/x-icon">
</head>

<%@include file="header.jsp"%>
<body>

<div class="container">
    <%@include file="order_header.jsp"%>
    <form action="" onsubmit="return validateOrder()" method="get">
        <table class="table-select_destination dark-grey">
            <tr>
                <td>
                    <label for="picking_point">Picking point</label>
                </td>

                <td>
                    <input id="picking_point" type="text" name="picking_point" placeholder="Enter your picking point">
                </td>
            </tr>

            <tr>
                <td>
                    <label for="destination">Destination</label>
                </td>

                <td>
                    <input id="destination" type="text" name="destination" placeholder="Enter your destination">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="preferred_driver">Preferred Driver</label>
                </td>

                <td>
                    <input id="preferred_driver" type="text" name="preferred_driver" placeholder="(optional)">
                </td>
            </tr>
        </table>
        <input class="button-next-order" name="submit" type="submit" value="NEXT">
    </form>
</div>

<script type="text/javascript" src="js/order_validation.js"></script>
</body>
</html>

