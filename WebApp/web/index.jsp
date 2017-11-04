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
    if (request.getParameter("login") != null) {
        String USER_AGENT = "Mozilla/5.0";
        String url = "http://localhost:8001/login";
        URL connection = new URL(url);
        HttpURLConnection con = (HttpURLConnection) connection.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = "uname=aaa&psw=bbb";
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
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(resp.toString());
            String status = (String) obj.get("status");
            if (status.equals("OK")) {
                sesi = request.getSession();
                String nextPage = "catalog.jsp";
                response.sendRedirect(nextPage);
            }
        } catch (ParseException e) {
            e.printStackTrace();
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
                <a class="left" href="#"><%--signup.jsp--%>Don't have an account?</a>
                <input class="button-login right" type="submit" name="login" value="GO!">
            </div>
        </form>
    </div>
</div>

<!--<script type="text/javascript" src="js/login_validation.js"></script>-->
</body>
</html>