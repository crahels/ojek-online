<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 11/7/2017
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2>Transaction History</h2>
<ul class="nav-bar-history">
    <li>
        <a <%
	  		if(application.getAttribute("specificPage") == "costumer-history") {
                out.print("class=\"active\"");
            }%>
        href="my_previous_order.jsp">MY PREVIOUS ORDERS
        </a>
    </li>
    <li>
        <a <%
            if(application.getAttribute("specificPage") == "driver-history") {
                out.print("class=\"active\"");
            }%>
        href="driver_history.jsp">DRIVER HISTORY
        </a>
    </li>
</ul>