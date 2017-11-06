//Variabel global
var isEmailValid = false;
var isEmailUsed = false;
var isUsernameUsed = false;
var isUsernameValid = true;

// Melakukan validasi terhadap form sign up
function validateSignUp() {
    var signUpForm = [
      "name",
      "username",
      "email",
      "password",
      "confirm_password",
      "phone_number"
    ];

    var errorMessage = "";
    var error = false;

    for(var i = 0; i < signUpForm.length; i++) {
        var input = document.getElementById(signUpForm[i]).value;
        if(input == "") {
            error = true;
            errorMessage += "Form cannot be blank.";
            break;
        }
    }

    if(!isEmailValid) {
        error = true;
        if(errorMessage != "") {
            errorMessage += "\n";
        }
        if(isEmailUsed) {
            errorMessage += "Email is already used.";
        } else {
            errorMessage += "Invalid email address.";
        }
    }

    if(!isUsernameValid) {
        error = true;
        if(errorMessage != "") {
            errorMessage += "\n";
        }
        errorMessage += "Invalid username, maximum length is 20 characters.";
    } else {
        if(isUsernameUsed) {
            error = true;
            if(errorMessage != "") {
                errorMessage += "\n";
            }
            errorMessage += "Username is already used.";
        }
    }

    if(!validatePassword()) {
        error = true;
        if(errorMessage != "") {
            errorMessage += "\n";
        }
        errorMessage += "Password does not match the confirm password.";
    }

    if(!validatePhoneNumber()) {
        error = true;
        if(errorMessage != "") {
            errorMessage += "\n";
        }
        errorMessage += "Phone number must be between 9 to 12 digits.";
    }

    if(error) {
        alert(errorMessage);
        return false;
    }
}

// Melakukan validasi terhadap username yang dimasukkan (menggunakan AJAX).
function validateUsername() {
    var username = document.getElementById("username").value;
    if(username == "" || username.length > 20) {
        isUsernameUsed = false;
        if (username.length > 20) {
            isUsernameValid = false;
        } else {
            isUsernameValid = true;
        }
        document.getElementById("username_result").innerHTML= "&#10005";
        document.getElementById("username_result").style.color = "red";
    } else {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if(this.readyState == 4 && this.status == 200) {
                if(this.responseText == "valid") {
                    isUsernameUsed = false;
                    isUsernameValid = true;
                    document.getElementById("username_result").innerHTML= "&#10003";
                    document.getElementById("username_result").style.color = "#03702c";
                } else {
                    isUsernameValid = true;
                    isUsernameUsed = true;
                    document.getElementById("username_result").innerHTML= "&#10005";
                    document.getElementById("username_result").style.color = "red";
                }
            }
        };
        xmlhttp.open("POST", "/TugasBesar1_2017/controller/ValidationController.php?validate=username", true);
        xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xmlhttp.send("username=" + username);
    }
}

// Melakukan validasi terhadap email yang dimasukkan (menggunakan AJAX).
function validateEmail() {
    var email = document.getElementById("email").value;
    var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(regex.test(email)) {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if(this.readyState == 4 && this.status == 200) {
                if(this.responseText == "valid") {
                    isEmailUsed = false;
                    isEmailValid = true;
                    document.getElementById("email_result").innerHTML= "&#10003";
                    document.getElementById("email_result").style.color = "#03702c";
                } else {
                    isEmailUsed = true;
                    isEmailValid = false;
                    document.getElementById("email_result").innerHTML= "&#10005";
                    document.getElementById("email_result").style.color = "red";
                }
            }
        };
        xmlhttp.open("POST", "/TugasBesar1_2017/controller/ValidationController.php?validate=email", true);
        xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xmlhttp.send("email=" + email);

    } else {
        isEmailUsed = false;
        isEmailValid = false;
        document.getElementById("email_result").innerHTML= "&#10005";
        document.getElementById("email_result").style.color = "red";
    }
}

// Melakukan validasi terhadap nomor telepon yang dimasukkan (harus berada antar 9-12 digit).
function validatePhoneNumber() {
    var regex = /^\d{9,12}$/;
    var phoneNumber = document.getElementById("phone_number").value;
    if(regex.test(phoneNumber)) {
        return true;
    } else {
        return false;
    }
}

// Melakukan validasi terhadap password yang dimasukkan.
function validatePassword() {
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirm_password").value;
    if (password == confirmPassword) {
        return true;
    } else {
        return false;
    }
}
