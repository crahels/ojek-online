<%--
  Created by IntelliJ IDEA.
  User: MASTER
  Date: 11/4/2017
  Time: 4:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.DataOutputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>


<%
    HttpSession sesi = request.getSession();
%>
<%
    // HTTP POST request
    String fullname;
    String username;
    String email;
    String password;
    String phonenumber;
    String isDriver;
    String errorMessage="";
    if(request.getParameter("register")!=null) {

        fullname = request.getParameter("name");
        username = request.getParameter("username");
        email = request.getParameter("email");
        password = request.getParameter("password");
        phonenumber = request.getParameter("phone_number");
        if (request.getParameter("is_driver") != null) {
            isDriver = "0";
        } else {
            isDriver = "1";
        }
        String USER_AGENT = "Mozilla/5.0";
        String url = "http://localhost:8003/register";
        URL connection = new URL(url);
        HttpURLConnection con = (HttpURLConnection) connection.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


        String urlParameters = "name="+fullname+
                "&username="+username+
                "&email="+email+
                "&password="+password+
                "&phone_number="+phonenumber+
                "&isDriver="+isDriver;
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
        String valid= (String) obj.get("valid");
        if(valid.equals("yes")){
            String user_info = (String) obj.get("user_info");
            if(user_info.equals("yes")) {
                String user_token = (String) obj.get("user_token");
                if(user_token.equals("yes")){
                    Integer id = ((Long) obj.get("user_id")).intValue();
                    String token  = (String) obj.get("token");
                    sesi = request.getSession();
                    sesi.setAttribute("username", username);
                    sesi.setAttribute("userid", id);
                    sesi.setAttribute("token", token);
                    sesi.setAttribute("email", email);
                    sesi.setAttribute("status", isDriver);
                    sesi.setAttribute("phone", phonenumber);
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
                    errorMessage= "Failed to insert data, server may be busy, please try again later";
                    out.println(errorMessage);
                }
            }
            else {
                errorMessage= "Failed to insert data, server may be busy, please try again later";
                out.println(errorMessage);
            }
        } else {
            errorMessage= "USERNAME NOT VALID";
            out.println(errorMessage);
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up - PR-OJEK</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="shortcut icon" href="../assets/favicon.ico" type="image/x-icon">
    <link rel="icon" href="../assets/favicon.ico" type="image/x-icon">
</head>

<body>
<div class="container">
    <div class="signup">
        <div class="divider">
            <hr class="left"/>Sign Up<hr class="right"/>
        </div>

        <form  class="form-signup" action="" onsubmit="" method="post">

            <%--@declare id="phone number"--%>
            <label for="name">Your Name</label>
            <input type="text" id="name" name="name" placeholder="Enter your name">

            <label for="username">Username</label>
            <input class="validated-on-input" type="text" id="username" name="username" placeholder="Enter your username" oninput="validateUsername()">
            <span id="username_result"></span>

            <label for="email">Email</label>
            <input class="validated-on-input" type="text" id="email" name="email" placeholder="Enter your email" oninput="validateEmail()">
            <span id="email_result"></span>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password">

            <label for="confirm_password">Confirm Password</label>
            <input type="password" id="confirm_password" name="confirm_password" placeholder="Reenter your password">

            <label for="Phone Number">Phone Number</label>
            <input type="text" id="phone_number" name="phone_number" placeholder="Enter your phone number">

            <div class="checkbox-driver">
                <input type="checkbox" id="is_driver" name="is_driver" value="is_driver">Also sign me up as a driver!
            </div>

            <div class="form-signup-submit">
                <a class="left" href="login.jsp">Already have an account?</a>
                <input class="button-signup right" name="register" type="submit" value="REGISTER">
            </div>
        </form>

    </div>
</div>

<script type="text/javascript" src="../assets/js/signup_validation.js"></script>
</body>
</html>
