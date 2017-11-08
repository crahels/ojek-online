<%@ page import="example.LocationService" %>
<%@ page import="example.Location" %>
<%@ page import="example.IllegalAccessException_Exception" %>
<%--
  Created by IntelliJ IDEA.
  User: Rachel
  Date: 07/11/2017
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    application.setAttribute("currentPage","order");
    application.setAttribute("currentSubPage","complete");

    HttpSession sesi = request.getSession();
    LocationService service = new LocationService();
    Location port = service.getLocationPort();
    int userId;
    java.util.List<String> locs = null;
    if (sesi.getAttribute("token") != null) {
        userId = Integer.parseInt(sesi.getAttribute("userId").toString());
        try {
            locs = port.getUserLocation(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (request.getParameter("submit") != null) {
            String locName = request.getParameter("loc_name");
            if (locName != "") {
                try {
                    port.addLocation(userId, locName);
                } catch (IllegalAccessException_Exception e) {
                    e.printStackTrace();
                }
                response.sendRedirect("edit_preferred_location.jsp");
            }
        }
        if (request.getParameter("delete") != null) {
            String locName = request.getParameter("delete");
            try {
                port.deleteLocation(userId, locName);
            } catch (IllegalAccessException_Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("edit_preferred_location.jsp");
        }
    }
    else {
        userId = -1;
    }
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Preferred Location - PR-OJEK</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="shortcut icon" href="favicon.png" type="image/x-icon">
    <link rel="icon" href="favicon.png" type="image/x-icon">
</head>

<body>
<div class="container">
    <h2>Edit Preferred Locations</h2>

    <table class="table-edit-location">
        <tr>
            <th width="8%">No</th>
            <th>Location</th>
            <th width="16%">Actions</th>
        </tr>
        <%
            int i;
            for (i = 0; i < locs.size(); i++) {
                int j = i + 1;
                out.print("<tr>" +
                        "<td>" + j + "</td>" +
                        "<form action='edit_preferred_location.jsp' onsubmit='' method='get'><td><label class='edited-location' id='" + locs.get(i) + "label'>" + locs.get(i) + "</label><input id='" +
                        locs.get(i) + "id' type='text' name='edit_text' class='edited-location hidden' value='" + locs.get(i) + "'></td>" +
                        "<td><button id='" + locs.get(i) +
                        "edit' class='pencil orange left' type='submit' name='edit' value='" + locs.get(i) + "'></button><button id='" +
                        locs.get(i) + "save' name='save' type='submit' class='checklist hidden' value='" + locs.get(i) + "'></button>" +
                        "<button class='cross right red' type='submit' name='delete' value='" + locs.get(i) + "'></button></td></form></tr>");
            }
        %>
    </table>

    <h3>Add new Location:</h3>
    <form class="form-location" action="" onsubmit="" method="post">
        <input type="text" id="loc_name" name="loc_name">
        <input class="button-add-location" type="submit" name="submit" value="ADD">
    </form>
    <div>
        <%-- profile.jsp --%>
        <a href="#"><input class="button-back left" type="button" value="BACK"></a>
    </div>
</div>
</body>
</html>

<%
    if (request.getParameter("edit") != null) {
        String locName = request.getParameter("edit");
        out.print("<script>" +
                "document.getElementById('" + locName + "id').className='edited-location';\n" +
                "document.getElementById('" + locName + "label').className='edited-location hidden';\n" +
                "document.getElementById('" + locName + "edit').className='pencil hidden';\n" +
                "document.getElementById('" + locName + "save').className='checklist';\n" +
                "</script>");
    }
    if (request.getParameter("save") != null) {
        String oldName = request.getParameter("save");
        String newName = request.getParameter("edit_text");
        out.print(oldName + " versus " + newName);
        out.print("<script>" +
                "document.getElementById('" + oldName + "id').className='edited-location hidden';\n" +
                "document.getElementById('" + oldName + "label').className='edited-location';\n" +
                "document.getElementById('" + oldName + "save').className='checklist hidden';\n" +
                "document.getElementById('" + oldName + "edit').className='pencil orange left';\n" +
                "</script>");
        try {
            port.saveLocation(userId, oldName, newName);
            response.sendRedirect("edit_preferred_location.jsp");
        } catch (IllegalAccessException_Exception e) {
            e.printStackTrace();
        }
    }
%>

