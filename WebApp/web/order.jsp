<%--
  Created by IntelliJ IDEA.
  User: MARCELLINO
  Date: 05/11/2017
  Time: 5:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%php
require_once (__DIR__ . '/Model.php');
class Order extends Model {

public $id;
public $driverId;
public $customerId;
public $date;
public $pickingPoint;
public $destination;
public $rating;
public $comment;
public $customerHidden;
public $driverHidden;

/**
* Konstruktor.
*/
public function __construct($id) {
$query = "SELECT driver_id, customer_id, date, picking_point, destination, rating, comment, customer_hidden, driver_hidden
FROM orders
WHERE id='$id'";
$results = $this->getQueryResult($query);
if (!empty($results)) {
$result = $results[0];
$this->id = $id;
$this->driverId = $result['driver_id'];
$this->customerId = $result['customer_id'];
$this->date = $result['date'];
$this->pickingPoint = $result['picking_point'];
$this->destination = $result['destination'];
$this->rating = $result['rating'];
$this->comment = $result['comment'];
$this->customerHidden = $result['customer_hidden'];
$this->driverHidden = $result['driver_hidden'];
}
}

/**
* Mengambil data order dari basis data berdasarkan id supir yang diberikan.
*/
public static function getDriverOrder($driverId) {
$query = "SELECT id, customer_id, date, picking_point, destination, rating, comment, customer_hidden, driver_hidden
FROM orders
WHERE driver_id='$driverId'
order by date desc";
$model = new Model();
$results = $model->getQueryResult($query);
return $results;
}

/**
* Mengambil data order dari basis data berdasarkan id pelanggan yang diberikan.
*/
public static function getCostumerOrder($customerId) {
$query = "SELECT id, driver_id, date, picking_point, destination, rating, comment, customer_hidden, driver_hidden
FROM orders
WHERE customer_id='$customerId'
order by date desc";
$model = new Model();
$results = $model->getQueryResult($query);
return $results;
}

/**
* Mengganti data driver_hidden pada basis data berdasarkan id order yang diberikan
*/
public static function hideDriver($id) {
$query = null;
$query = "UPDATE orders
SET driver_hidden=true
WHERE id='$id'";
$model = new Model();
$model->executeQuery($query);
}

/**
* Mengganti data customer_hidden pada basis data berdasarkan id order yang diberikan
*/
public static function hideCustomer($id) {
$query = null;
$query = "UPDATE orders
SET customer_hidden=true
WHERE id='$id'";
$model = new Model();
$model->executeQuery($query);
}

/**
* Memasukkan data order ke dalam basis data.
*/
public static function saveOrder($driverId, $customerId, $pickingPoint, $destination, $rating, $comment) {
$query = "INSERT INTO orders(driver_id, customer_id, picking_point, destination, rating, comment)
VALUES('$driverId', '$customerId', '$pickingPoint', '$destination', '$rating', '$comment')";
$model = new Model();
$model->executeQuery($query);
}
}
%>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>ORDER</h1>
</body>
</html>

