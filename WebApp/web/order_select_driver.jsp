<%--
  Created by IntelliJ IDEA.
  User: MARCELLINO
  Date: 05/11/2017
  Time: 5:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%php
include (__DIR__ . '/../controller/UserController.php');
$currentPage = 'order';
$currentSubPage = 'driver';
$pickingPoint = $_GET['picking_point'];
$destination = $_GET['destination'];
$name = $_GET['preferred_driver'];
$preferredDrivers = $user->getPreferredDrivers($pickingPoint, $destination, $name);
$otherDrivers = $user->getOtherDrivers($pickingPoint, $destination, $name);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Order - Select Driver - PR-OJEK</title>
    <link href="../assets/css/style.css" rel="stylesheet">
    <link rel="shortcut icon" href="../assets/favicon.ico" type="image/x-icon">
    <link rel="icon" href="../assets/favicon.ico" type="image/x-icon">
</head>

<?php include(__DIR__ . '/header.php');?>

<body>
<div class="container dark-grey">
    <?php include(__DIR__ . '/order_header.php');?>
    <div class="select-driver-border">
        <h1>Preferred Drivers:</h1>
        <?php
				if($preferredDrivers) {
					foreach ($preferredDrivers as $preferredDriver) {
						echo '
						<table class="table-select-driver">
        <tr>
            <td>
                <img class="img-driver-pic" src="../assets/images/profile/' . $preferredDriver['profile_picture'] . '" alt="DRIVER PICTURE">
            </td>
            <td>
                <p class="driver-name">' . $preferredDriver['name'] . '</p>
                <p class="star"><span class="orange">&#10025; ' . $preferredDriver['ratings'] . '</span> (' . $preferredDriver['votes'];
                    if($preferredDriver['votes'] > 1){
                    echo ' votes)';
                    } else {
                    echo ' vote)';
                    }
                    echo '</p>
                <a href="complete_order.php?id_active=' . $user->id . '&picking_point=' . $pickingPoint . '&destination=' . $destination . '&preferred_driver=' . $name . '&driver_id=' . $preferredDriver['id'] . '"><input class="button-i-choose-you right" type="button" value="I CHOOSE YOU!!"> </a>
            </td>
        </tr>
        </table>';
        }
        } else {
        echo '<p class="align-center nothing-to-display-margin">Nothing to display :(</p>';
        }
        ?>
    </div>

    <div class="select-driver-border dark-grey">
        <h1>Other Drivers:</h1>
        <?php
				if($otherDrivers) {
					foreach ($otherDrivers as $otherDriver) {
						echo '
						<table class="table-select-driver">
        <tr>
            <td>
                <img class="img-driver-pic" src="../assets/images/profile/' . $otherDriver['profile_picture'] . '" alt="DRIVER PICTURE">
            </td>
            <td>
                <p class="driver-name">' . $otherDriver['name'] . '</p>
                <p class="star"><span class="orange">&#10025; ' . $otherDriver['ratings'] . '</span> (' . $otherDriver['votes'];
                    if($otherDriver['votes'] > 1){
                    echo ' votes)';
                    } else {
                    echo ' vote)';
                    }
                    echo '</p>
                <a href="complete_order.php?id_active=' . $user->id . '&picking_point=' . $pickingPoint . '&destination=' . $destination . '&preferred_driver=' . $name . '&driver_id=' . $otherDriver['id'] . '"><input class="button-i-choose-you right" type="submit" value="I CHOOSE YOU!!"></a>
            </td>
        </tr>
        </table>';
        }
        } else {
        echo '<p class="align-center nothing-to-display-margin">Nothing to display :(</p>';
        }
        ?>
    </div>
</div>
</body>
</html>

