//Melakukan validasi input location kosong
function validateLocation() {
    var userPreferredLocation = [
      "user_id",
      "location"
    ];

    var error = false;
    var errorMsg = "";
    var input = document.getElementById("loc_name").value;

    if (input == "") {
   		error = true;
   		errorMsg += "Location cannot be blank.";
    }

    if (error) {
    	alert(errorMsg);
    	return false;
    }
}

//Menghapus lokasi
function deleteLocation (loc_name, user_id) {
	var xmlhttp = new XMLHttpRequest();

	 xmlhttp.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            location.reload();
        }
     };

	if (confirm("Delete this location?") == true) {
	    xmlhttp.open("DELETE", "/TugasBesar1_2017/controller/EditUserPreferredLocationsController.php?id_active=" + user_id + "&loc_name=" + loc_name, true);
	    xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	    xmlhttp.send();
	}
}

//Mengaktifkan mode edit lokasi.
function editLocation (loc_name, user_id) {
	document.getElementById(loc_name + "id").className="edited-location";
	document.getElementById(loc_name + "label").className="edited-location hidden";
	document.getElementById(loc_name + "edit").className="pencil hidden";
	document.getElementById(loc_name + "save").className="checklist";
}

//Menyimpan lokasi yang telah diedit.
function saveLocation (loc_name, user_id) {
	var xmlhttp = new XMLHttpRequest();
	document.getElementById(loc_name + "id").className="edited-location hidden";
	document.getElementById(loc_name + "label").className="edited-location";
	document.getElementById(loc_name + "save").className="checklist hidden";
	document.getElementById(loc_name + "edit").className="pencil orange left";

    var input = document.getElementById(loc_name + "id").value;
    var prevLoc = document.getElementById(loc_name + "label").innerText;

    var error = false;
    var errorMsg = "";

    if (input == "") {
   		error = true;
   		errorMsg += "Location cannot be blank.";
    }

    if (error) {
    	alert(errorMsg);
    	return false;
    } else {
    	xmlhttp.open("POST", "/TugasBesar1_2017/controller/EditUserPreferredLocationsController.php?id_active=" + user_id + "&loc_name=" + input + "&prevLoc=" + prevLoc + "&action=save_edit", true);
	    xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	    xmlhttp.send("loc_name=" + input);
    }

	xmlhttp.onreadystatechange = function() {
            if(this.readyState == 4 && this.status == 200) {
                location.reload();
            }
        }
}
