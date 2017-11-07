<%--
  Created by IntelliJ IDEA.
  User: MASTER
  Date: 11/4/2017
  Time: 12:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.DataOutputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%
    HttpSession sesi = request.getSession();
%>
<%
    String username;
    String password;
    String errorMessage="";

    if(request.getParameter("login")!=null) {
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
        String valid = (String) obj.get("valid");
        String user_token = (String) obj.get("user_token");
        String token  = (String) obj.get("token");
        String email = (String) obj.get("user_email");
        String status = (String) obj.get("user_status");
        String phone = (String) obj.get("user_phone");
        String asdf = (String) obj.get("asdf");

        out.println(valid);
        out.println(user_token);
        out.println(token);
        out.println(email);
        out.println(status);
        out.println(phone);
        out.println(asdf);

        Integer id = ((Long) obj.get("user_id")).intValue();

        if(user_token.equals("yes")){
            sesi = request.getSession();
            sesi.setAttribute("username", username);
            sesi.setAttribute("userid", id);
            sesi.setAttribute("token", token);
            sesi.setAttribute("email", email);
            sesi.setAttribute("status", status);
            sesi.setAttribute("phone", phone);
            sesi.setAttribute("token", token);
            String nextPage;
            if (sesi.getAttribute("status") == "0") {
                nextPage = "profile.jsp";
            } else {
                nextPage = "order.jsp";
            }
            response.sendRedirect(nextPage);
        }
        else {
            errorMessage= "USERNAME NOT VALID";
            out.println(errorMessage);
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - PR-OJEK</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="shortcut icon" href="../assets/favicon.ico" type="image/x-icon">
    <link rel="icon" href="../assets/favicon.ico" type="image/x-icon">
</head>

<body>
<div class="container">
    <div class="login">
        <div class="divider">
            <hr class="left"/>Login<hr class="right"/>
        </div>

        <form  class="form-login" action="" onsubmit="" method="post">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" placeholder="Enter your username">

            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password">

            <div class="form-login-submit">
                <a class="left" href="register.jsp">Don't have an account?</a>
                <input class="button-login right" type="submit" name="login" value="GO!">
            </div>
        </form>
    </div>
</div>

<script type="text/javascript" src="../assets/js/login_validation.js"></script>
</body>
</html>