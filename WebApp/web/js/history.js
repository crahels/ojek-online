// Melakukan 
function hideCustomer(i, user_id) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            location.reload();
        }
    };
    xmlhttp.open("POST", "/TugasBesar1_2017/controller/HistoryCustomerController.php?id_active=" + user_id, true);
    xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlhttp.send("order_id=" + i);
}

function hideDriver(i, user_id) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            location.reload();
        }
    };
    xmlhttp.open("POST", "/TugasBesar1_2017/controller/HistoryDriverController.php?id_active=" + user_id, true);
    xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlhttp.send("order_id=" + i);
}