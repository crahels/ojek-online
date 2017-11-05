<%--
  Created by IntelliJ IDEA.
  User: MARCELLINO
  Date: 05/11/2017
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  application.setAttribute( "currentPage", "order");
    application.setAttribute("currentSubPage","destination");
%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="container">
        <table width="100%">
            <tr>
                <td>
                    <h1 class="left"><span class="header-title-1">PR</span>-<span class="header-title-2">OJEK</span></h1>
                </td>

                <td>
                    <p class="hi-username">Hi, <span class="bold"></span> !</p>
                </td>
            </tr>
            <tr>
                <td>
                    <p class="subtitle left">wushh... wushh... ngeeeeeenggg...</p>
                </td>
                <td>
                    <a class="logout right" href="index.jsp">Logout</a>
                </td>
            </tr>
        </table>
        <ul class="nav-bar">
            <li>
                <a <% if (application.getAttribute("currentPage") == "order") {
                    out.print("class='active'");
                }
                %>
                    href="#">Order</a></li>
            <li>
                <a <% if (application.getAttribute("currentPage") == "history") {
                    out.print("class='active'");
                }%>
                    href="#">History</a></li>
            <li>
                <a <% if (application.getAttribute("currentPage") == "profile") {
                    out.print("class='active'");
                }%>
                    href="#">My Profile</a></li>
        </ul>
    </div>
</body>
</html>


