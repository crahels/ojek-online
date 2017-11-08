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

//Mengaktifkan mode edit lokasi.
function editLocation (loc_name, user_id) {
	document.getElementById(loc_name + "id").className="edited-location";
	document.getElementById(loc_name + "label").className="edited-location hidden";
	document.getElementById(loc_name + "edit").className="pencil hidden";
	document.getElementById(loc_name + "save").className="checklist";
}

//Menyimpan lokasi yang telah diedit.
function saveLocation (loc_name, user_id) {
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
}
