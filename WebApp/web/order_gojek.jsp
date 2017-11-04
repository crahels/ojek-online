<%--
  Created by IntelliJ IDEA.
  User: MARCELLINO
  Date: 05/11/2017
  Time: 5:33
  To change this template use File | Settings | File Templates.
--%>
<%php
include (__DIR__ . '/../controller/UserController.php');
$currentPage = 'order';
$currentSubPage = 'destination';
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Order - Select Destination - PR-OJEK</title>
    <link href="../assets/css/style.css" rel="stylesheet">
    <link rel="shortcut icon" href="../assets/favicon.ico" type="image/x-icon">
    <link rel="icon" href="../assets/favicon.ico" type="image/x-icon">
</head>

<?php include(__DIR__ . '/header.php');?>

<body>

<div class="container">
    <%php include(__DIR__ . '/order_header.php');%>
    <form action="order_select_driver.php" onsubmit="return validateOrder()" method="get">
        <input type="hidden" id="id_active" name="id_active" value="<?php echo $user->id;?>">
        <table class="table-select_destination dark-grey">
            <tr>
                <td>
                    <label for="picking_point">Picking point</label>
                </td>

                <td>
                    <input id="picking_point" type="text" name="picking_point" placeholder="Enter your picking point">
                </td>
            </tr>

            <tr>
                <td>
                    <label for="destination">Destination</label>
                </td>

                <td>
                    <input id="destination" type="text" name="destination" placeholder="Enter your destination">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="preferred_driver">Preferred Driver</label>
                </td>

                <td>
                    <input id="preferred_driver" type="text" name="preferred_driver" placeholder="(optional)">
                </td>
            </tr>
        </table>
        <input class="button-next-order" type="submit" value="NEXT">
    </form>
</div>

<script type="text/javascript" src="../assets/js/order_validation.js"></script>
</body>
</html>

