$(document).ready(function () {

    // 1. hide Error section ----------
    $("#userTypeError").hide();
    $("#userCodeError").hide();
    $("#userEmailError").hide();
    $("#userContactError").hide();
    $("#userIdTypeError").hide();
    $("#ifOtherError").hide();
    $("#userIdNumError").hide();
    $("#ifOtherSec").hide();

    // difine error variable

    var userTypeError = false;
    var userCodeError = false;
    var userEmailError = false;
    var userContactError = false;
    var userIdTypeError = false;
    var ifOtherError = false;
    var userIdNumError = false;

    // difine funtion validation

    function validate_userType() {
        var val = $('[name="userType"]:checked').length;
        if (val == '') {
            $("#userTypeError").show();
            $("#userTypeError").html(" * Please select one  <b> User Type </b>");
            $("#userTypeError").css('color', 'red');
            userTypeError = false;

        } else {
            $("#userTypeError").hide();
            userTypeError = true;
        }

        return userTypeError;
    }

    function validate_userCode() {
        var val = $("#userCode").val();
        var exp = /^[A-Z\-\s]{4,8}$/;

        if (val == '') {
            $("#userCodeError").show();
            $("#userCodeError").html(" * Please select <b> code </b>");
            $("#userCodeError").css('color', 'red');


        }
        else if (!exp.test(val)) {
            $("#userCodeError").show();
            $("#userCodeError").html("*<b>Code</b> must be 4-8 uppercase letters");
            $("#userCodeError").css('color', 'red');
            userCodeError = false;
        } else {
            var id = 0; //for register
            if ($("#id").val() != undefined) { //for edit
                id = $("#id").val(); //if present
                userCodeError = true;
            }
            //ajax call start    
            $.ajax({
                url: 'validate',
                data: { "code": val, "id": id },
                success: function (resTxt) {
                    if (resTxt != '') { //error, duplicate exist
                        $("#userCodeError").show();
                        $("#userCodeError").html(resTxt);
                        $("#userCodeError").css('color', 'red');
                        userCodeError = false;
                    } else { //valid, no duplicate
                        $("#userCodeError").hide();
                        userCodeError = true;
                    }
                }
            });
            //ajax call end    
        }
        return userCodeError;
    }

    function validate_userEmail() {

        var val = $("#userEmail").val();
        if (val == '') {
            $("#userEmailError").show();
            $("#userEmailError").html(" * Please Enter Your <b>Email Id</b> ");
            $("#userEmailError").css('color', 'red');
            userEmailError = false;

        }
        else {
            var id = 0; //for register
            if ($("#id").val() != undefined) { //for edit
                id = $("#id").val(); //if present
            }
            //ajax call start    
            $.ajax({
                url: 'validateemail',
                data: { "email": val, "id": id },
                success: function (resTxt) {
                    if (resTxt != '') { //error, duplicate exist
                        $("#userEmailError").show();
                        $("#userEmailError").html(resTxt);
                        $("#userEmailError").css('color', 'red');
                        userCodeError = false;
                    } else { //valid, no duplicate
                        $("#userEmailError").hide();
                        userEmailError = true;
                    }
                }
            });
            //ajax call end    
        }

        return userEmailError;
    }

    function validate_userContact() {

        var val = $("#userContact").val();
        var exp = /^[A-Za-z0-9\s\-\.]{10,100}$/;
        if (val == '') {
            $("#userContactError").show();
            $("#userContactError").html(" * Please Enter your Contact ");
            $("#userContactError").css('color', 'red');
        } else if (!exp.test(val)) {
            $("#userContactError").show();
            $("#userContactError").html(" * Please enter 10-100 chars");
            $("#userContactError").css('color', 'red');
            userIdNumError = false;
        }

        else {
            $("#userContactError").hide();
            userContactError = true;
        }

        return userContactError;
    }

    function validate_userIdType() {

        var val = $("#userIdType").val();
        if (val == '') {
            $("#userIdTypeError").show();
            $("#userIdTypeError").html(" * Please Select one  your<b>ID</b> ");
            $("#userIdTypeError").css('color', 'red');
            userIdTypeError = false;

        }
        else {
            $("#userIdTypeError").hide();
            userIdTypeError = true;
        }

        return userIdTypeError;
    }

    function validate_userIdNum() {
        var val = $("#userIdNum").val();
        var exp = /^[A-Za-z0-9\-\s\.\:\@\,]{8,14}$/;

        if (val == '') {
            $("#userIdNumError").show();
            $("#userIdNumError").html(" * Please Enter Your <b> Id Number </b> ");
            $("#userIdNumError").css('color', 'red');


        } else if (!exp.test(val)) {
            $("#userIdNumError").show();
            $("#userIdNumError").html(" Only 8-14 chars allowed");
            $("#userIdNumError").css('color', 'red');
            userIdNumError = false;
        }
        else {
            var id = 0; //for register
            if ($("#id").val() != undefined) { //for edit
                id = $("#id").val(); //if present
            }
            //ajax call start    
            $.ajax({
                url: 'validateidnum',
                data: { "idnum": val, "id": id },
                success: function (resTxt) {
                    if (resTxt != '') { //error, duplicate exist
                        $("#userIdNumError").show();
                        $("#userIdNumError").html(resTxt);
                        $("#userIdNumError").css('color', 'red');
                        userCodeError = false;
                    } else { //valid, no duplicate
                        $("#userIdNumError").hide();
                        userIdNumError = true;
                    }
                }
            });
            //ajax call end    
        }

        return userIdNumError;
    }
    // 4. link with Action Event 

    $('[name="userType"]').click(function () {
 		validate_userType();
        var val = $('[name="userType"]:checked').val();
        if (val == "Vendor")
            $('#userFor').val("Purchase");
        else if(val == "Customer")
            $('#userFor').val("Sale");
       
    });

    $("#userCode").keyup(function () {
        $(this).val($(this).val().toUpperCase());
        validate_userCode();
    });

    $("#userEmail").keyup(function () {
        validate_userEmail();
    });

    $("#userContact").keyup(function () {
        validate_userContact();
    });

    $("#userIdType").change(function () {
        if ($(this).val() == "OTHER") {
            $("#ifOtherSec").show();
        } else {
            $("#ifOtherSec").hide();
        }
        validate_userIdType();
    });

    $("#userIdNum").keyup(function () {
        validate_userIdNum();
    });

    // 5.click on submit   
    $("#whUtForm").submit(function () {

        validate_userType();
        validate_userCode();
        validate_userEmail();
        validate_userContact();
        validate_userIdType();
        validate_userIdNum();
        if (userTypeError && userCodeError && userEmailError
            && userContactError && userIdTypeError && userIdNumError)
            return true;
        else return false;

    });

});