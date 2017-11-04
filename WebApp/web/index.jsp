<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 11/2/2017
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <input class="button-login right" type="submit" value="GO!">
        </div>
        </form>
        </div>
        </div>

        <script type="text/javascript" src="../assets/js/login_validation.js"></script>
</body>
</html>