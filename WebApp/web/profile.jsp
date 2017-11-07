<%--
  Created by IntelliJ IDEA.
  User: MASTER
  Date: 11/4/2017
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String token = null;
    HttpSession sesi = request.getSession();
    if(sesi.getAttribute("token") == null){
        response.sendRedirect("login.jsp");
    }
    else {
        token = sesi.getAttribute("token").toString();
    }
%>
<%--<?php
include (__DIR__ . '/../controller/UserController.php');
require_once (__DIR__ . '/../model/UserPreferredLocations.php');
$currentPage = 'profile';
$userPreferredLocations = new UserPreferredLocations($user->id);
?>--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile - PR-OJEK</title>
    <link href="css/style.css" rel="stylesheet">
    <link rel="shortcut icon" href="favicon.png" type="image/x-icon">
    <link rel="icon" href="favicon.png" type="image/x-icon">
</head>

<%--<?php include (__DIR__ . '/header.php');?>--%>

<body>
<div class="container">
    <h2>My Profile
        <a class="pencil orange right" href=""></a>
    </h2>
    <img class="img-circle" src="images/profile" alt="YOUR PROFILE PICTURE">
    <div class="user-info text-center">
        <div class="username">
            @<?php echo $user->username ?>
        </div>

        <div class="user-details">
            <?php echo $user->name ?> </br>
            <?php
                        if($user->isDriver) {
            echo 'Driver | <span class="text-small orange">&#9734;</span> <span class="orange">' . $user->ratings . '</span> (' . $user->votes;
            if ($user->votes > 1) {
            echo ' votes)';
            } else {
            echo ' vote)';
            }
            } else {
            echo 'Non-Driver';
            }
            ?> </br>
            <span class="text-small">&#9993;</span> <?php echo $user->email; ?> </br>
            <span class="text-small">&#9743;</span> <?php echo $user->phoneNumber; ?>
        </div>
    </div>
    <?php
            if ($user->isDriver) {
    echo '<h3>
    Preferred Locations:
    <a class="pencil orange text-large right" href="edit_preferred_location.php?id_active='. $user->id .'"></a>
</h3>

    <div class="preferred-location-list">';
        foreach ($userPreferredLocations->locName as $preferredLocation) {
        echo '<ul> ' . $preferredLocation;
            }
            foreach ($userPreferredLocations->locName as $preferredLocation) {
            echo '</ul>';
        }
        echo '</div>';
    }
    ?>
</div>
</body>
</html>

