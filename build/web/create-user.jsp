<%-- 
    Document   : create-user
    Created on : Feb 27, 2023, 12:22:27 PM
    Author     : thaiq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Create User</title>
        <link href="hien-css/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <style>
            .error {
                color: #333;
                font-size: 14px;
                padding: 20px;
                text-align: center;
                display: none;
            }

            .invalid {
                border: 2px solid red;
            }

        </style>
    </head>

    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>

                    <h1 style="color: green">${requestScope.successfully}</h1>
                    <h1 style="color: red ">${requestScope.erorr}</h1>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header">
                                        <h3 class="text-center font-weight-light my-4">Create User</h3>
                                    </div>
                                    <div class="card-body">
                                        <form action="CreateUserByAdminController" method="POST">
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputName" type="text" name="fullName" placeholder="name" required=""/>

                                                <label for="inputName">Full Name</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="user-email" type="email" name="email"
                                                       onchange="validateEmail()"  placeholder="name@example.com" autocomplete="off"/>
                                                <div class="error" id="errorMessage" style="color: red">Please enter a valid email address(Example: example@domain.com)</div>
                                                <h3 id="email-validation-message" style="color: red"></h3>
                                                <label for="inputEmail">Email address</label>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="user-email-password" type="password" autocomplete="off" name="password" placeholder="Create a password" />
                                                        <div class="error" id="errorMessage3" style="color: red">Password must have  9 to 30 characters and at least one number</div>
                                                        <label for="inputPassword">Password</label>
                                                    </div>
                                                </div>

                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="user-email-passwords" type="password" placeholder="Enter Pass"
                                                               onchange="validatePassword()"   name="repassword"/>
                                                        <div class="error" id="errorMessage2" style="color: red">Confirm password does not match password</div>
                                                        <label for="inputPassword">Confirm Password</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-12" style="margin-top: 10px">
                                                    <div class="form-floating">
                                                        <input class="form-control" id="user-address" type="text" name="address" required=""
                                                               placeholder="Enter Address" />
                                                        <label for="inputAddress">Address</label>
                                                    </div>

                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <div class="col-md-10">
                                                    <div class="form-floating mb-3">
                                                        <input class="form-control" id="user-phone-num" type="text" name="phoneNumber" onchange="checkPhone()" required=""
                                                               placeholder="Enter Phonenumber" />
                                                        <div class="error" id="errorMessage4" style="color: red">Phone number must be 10 digits</div>
                                                        <label for="inputPhonenumber">Phone number</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        Gender <input type="radio" name="gender" value="MALE"
                                                                      checked="checked">MALE
                                                        <input type="radio" name="gender" value="FEMALE">FEMALE
                                                    </div>
                                                </div>
                                                <!--                                                <div class="row mb-3">
                                                                                                    <div class="col-md-6">
                                                                                                        <div class="form-floating mb-3 mb-md-0">
                                                                                                            Status <input type="radio" name="status" value="ACTIVE"
                                                                                                                          checked="checked">ACTIVE
                                                                                                            <input type="radio" name="status" value="INACTIVE">INACTIVE
                                                                                                        </div>
                                                                                                    </div>
                                                                                                    <div class="col-md-6">
                                                                                                        <div class="form-floating">
                                                                                                            Role
                                                                                                            <select name="roleID">
                                                                                                                <option value="2">staff</option>
                                                                                                                <option value="3">customer</option>
                                                                                                                <option value="4">user</option>
                                                                                                            </select>
                                                
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>-->
                                                <div class="mt-4 mb-0">
                                                    <button id="button" class="btn btn-lg btn-success" type="submit">
                                                        <img class="form-group-icon" src="assets/font/save_white_24dp.svg" alt="" />
                                                        Create
                                                    </button>
                                                </div>

                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>

        </main>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script src="assets/js/validation.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
                            $(document).ready(function () {
                                $('#user-email').on('input', function () {
                                    var email = $(this).val();
                                    $.ajax({
                                        type: 'POST',
                                        url: 'CheckEmailController',
                                        data: {email: email},
                                        success: function (data) {
                                            $('#email-validation-message').text(data);
                                            if (data) {
                                                // email already exists
                                                document.getElementById("user-email-password").disabled = true;
                                                document.getElementById("user-email-passwords").disabled = true;
                                                document.getElementById("user-address").disabled = true;
                                                document.getElementById("user-phone-num").disabled = true;
                                                document.getElementById("user-email").classList.add("invalid");
                                                document.getElementById("button").style.display = "none";
                                            } else {
                                                // email is available
                                                messageElem.text('');
                                                $('#user-email').prop('disabled', false);
                                            }
                                        }
                                    });
                                });
                            });
        </script>
</body>

</html>
