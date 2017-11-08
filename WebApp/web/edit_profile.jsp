<%--
  Created by IntelliJ IDEA.
  User: MASTER
  Date: 11/7/2017
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="example.*" %>
<%@ page import="javax.servlet.annotation.MultipartConfig" %>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.DataOutputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@ page import="example.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String token;
    HttpSession sesi = request.getSession();
    int userId;
    example.User userOjek = new User();;
    if (sesi.getAttribute("token") != null) {
        token = sesi.getAttribute("token").toString();
        HelloWorldService servis = new HelloWorldService();
        HelloWorld prt = servis.getHelloWorldPort();
        try {
            if (prt.expiryTime(token) == 0) { // invalid
                out.print("<script>alert('INVALID ACCESS');" +
                        "window.location = 'login.jsp';</script>");
            } else if (prt.expiryTime(token) == 2) { // expired
                out.print("<script>alert('EXPIRED ACCESS');" +
                        "window.location = 'login.jsp';</script>");
            }
        } catch (IOException_Exception e) {
            e.printStackTrace();
        } catch (ParseException_Exception e) {
            e.printStackTrace();
        }
        userId = Integer.parseInt(sesi.getAttribute("userId").toString());
        java.util.List<example.User> userList = null;
        //java.util.List<example.Driver> driverList = null;
        //Driver driverOjek = new Driver();
        ProfileService serviceProfile = new ProfileService();
        Profile portProfile = serviceProfile.getProfilePort();
        try {
            userList = portProfile.getDataUser(userId);
            userOjek = userList.get(0);
            //driverOjek = port.getDriverByID(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        userId = -1;
    }
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Profile - PR-OJEK</title>
    <link href="css/style.css" rel="stylesheet">
    <link rel="shortcut icon" href="favicon.png" type="image/x-icon">
    <link rel="icon" href="favicon.png" type="image/x-icon">
</head>
<body>
<div class="container">
    <h2>Edit Profile Information</h2>
    <table class="table-edit-profile">
        <form class="form-edit-profile" action="editProfileServlet" onsubmit="" method="post" enctype="multipart/form-data">
            <tr>
                <td>
                    <img class="img-profile-picture" src="images/profile/<%out.print(userId);%>.png" alt="Your Profile Picture">
                </td>

                <td>
                    <label for="profile_picture" class="profile-picture" >Update Profile Picture</label>
                    <input type="text" id="profile_picture_path" name="profile_picture_path" disabled>
                    <input class="browse-button" id="button_browse" type="button" value="Browse.." onclick="browse()">
                    <input class="hidden" id="profile_picture" type="file" name="profile_picture" oninput="writeFilePath()">

                    <!--<input class="browse-button" id="button_browse2" type="button" value="Browseee.." onclick="writeFilePath()">-->
                </td>
            </tr>
            <tr>
                <td>
                    <label for="name">Your Name</label>
                </td>

                <td>
                    <input id="name" type="text" name="name" value="<%out.print(userOjek.getName());%>">
                </td>
            </tr>

            <tr>
                <td>
                    <label for="phone">Phone</label>
                </td>

                <td>
                    <input id="phone" type="text" name="phone" value="<%out.print(userOjek.getPhone());%>">
                </td>
            </tr>

            <tr>
                <td>
                    <label for="is_driver">Status Driver</label>
                </td>

                <td>
                    <label class="switch right">
                        <input id="is_driver" type="checkbox" name="is_driver" value="is_driver"
                               <%
                                if (userOjek.getStatus().equals("0")) {
                                    out.print("checked=true");
                                }
                            %>>
                        <span class="slider round"></span>
                    </label>
                </td>
            </tr>

            <tr>
                <td>
                    <a href="profile.jsp"><input class="button-back left" type="button" value="BACK"></a>
                </td>
                <td>
                    <input class="button-save right" name="edit_profile" type="submit" value="SAVE">
                </td>
            </tr>
        </form>
    </table>

</div>

<script type="text/javascript" src="js/edit_profile_validation.js"></script>
</body>
</html>

