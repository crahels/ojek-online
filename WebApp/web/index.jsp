<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.DataOutputStream" %>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="org.json.simple.parser.ParseException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession sesi = request.getSession();
%>
<%
    String username;
    String password;
    String errorMessage="";

    if(request.getParameter("register")!=null) {
        username = request.getParameter("username");
        password = request.getParameter("password");

        String USER_AGENT = "Chrome/61.0.3163.100";
        String url = "http://localhost:8001/login";
        URL connection = new URL(url);
        HttpURLConnection con = (HttpURLConnection) connection.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "username="+username+
                "&password="+password;
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder resp = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            resp.append(inputLine);
        }
        in.close();
        con.disconnect();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(resp.toString());
        String status = (String) obj.get("status");
        //long userid = (Long) obj.get("userid");
        String token  = (String) obj.get("token");
        if(status.equals("ok")){
            sesi = request.getSession();
            sesi.setAttribute("username", username);
            //sesi.setAttribute("userid", userid);
            sesi.setAttribute("token", token);
            String nextPage = "profile.jsp";
            response.sendRedirect(nextPage);
        }
        else {
            errorMessage= "USERNAME NOT VALID";
            out.println(errorMessage);
        }
    }
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Login - PR-OJEK</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="shortcut icon" href="favicon.png" type="image/x-icon">
    <link rel="icon" href="favicon.png" type="image/x-icon">
</head>

<body>
<div class="container">
    <div class="login">
        <div class="divider">
            <hr class="left"/>Login<hr class="right"/>
        </div>

        <div class="error-message-login">
            <%--<?php
                if(isset($_GET['invalid']) && $_GET['invalid'] == 'true') {
                    echo '<script type="text/javascript">';
                    echo 'alert("Invalid credentials.")';
                    echo '</script>';
                }
            ?>--%>
        </div>

        <form  class="form-login" onsubmit="" method="POST">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" placeholder="Enter your username">

            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password">

            <div class="form-login-submit">
                <a class="left" href="register.jsp"><%--signup.jsp--%>Don't have an account?</a>
                <input class="button-login right" type="submit" name="login" value="GO!">
            </div>
        </form>
    </div>
</div>

<!--<script type="text/javascript" src="js/login_validation.js"></script>-->
</body>
</html>