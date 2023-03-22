<%-- 
    Document   : user
    Created on : Feb 1, 2023, 5:06:24 PM
    Author     : thaiq
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>USER</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="assets/css/home.css"/>
        <link rel="stylesheet" href="assets/css/base.css"/>
        <link rel="icon" type="image/png" href="assets/images/LogoDoneEdited.png"/>

        <style>
            .user-info-actions {
                background-color: transparent;
                border: none;
            }

            .user-info-btn {
                text-decoration: none;
                color: black;
                border: 1px solid rgba(0, 0, 0, 0.5);
                padding: 5px 1rem;
                border-radius: 4px;
                transition: all 0.4s;
            }

            .user-info-actions h2 a:hover,
            .user-info-btn:hover {
                text-decoration: none;
                color: var(--main-page-color);
                filter: brightness(1.2);
                background-color: transparent;
            }

            .user-logout-btn:hover {
                color: #D81159;
                background-color: #EFEFEF;
            }

            .user-info-actions h2:first-child {
                display: flex;
                justify-content: space-between;
                margin: 5px 0 30px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="components/header.jsp"/>
        <section style="background-color: #eee; font-size: 30px">
            <div class="container py-5">
                <div class="row">
                    <div class="col" style="font-size: 20px">
                        <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
                            <ol class="breadcrumb mb-0">
                                <li class="breadcrumb-item"><a href="/AmoraShop">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">User Profile</li>
                            </ol>
                        </nav>
                    </div>
                </div>


                <div class="row">
                    <div class="col-lg-4">
                        <div class="card mb-4">
                            <div class="card-body text-center">
                                <img src="${sessionScope.LOGIN_USER.image}" alt="avatar"
                                     class="rounded-circle img-fluid" style="width: 150px;">

                            </div>
                        </div>
                        <div class="user-info-actions card mb-4 mb-lg-0">
                            <div class=" card-body p-0">
                                <h2>
                                    <a class="user-info-btn" href="update-informaton.jsp">Update</a>

                                    <form action="OrderListByUserIdController">
                                        <input class="user-info-btn" type="submit" value="View Your Orders">
                                    </form>
                                </h2>
                                <h2>
                                    <form action="LogoutController" method="POST">
                                        <input class="user-info-btn user-logout-btn" type="submit" name="action" value="Logout">
                                    </form>
                                </h2>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Full Name:</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">${sessionScope.LOGIN_USER.fullName}</p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Email:</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">${sessionScope.LOGIN_USER.email}</p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Phone:</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">${sessionScope.LOGIN_USER.phoneNumber}</p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Gender:</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">${sessionScope.LOGIN_USER.gender}</p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Address:</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">${sessionScope.LOGIN_USER.address}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
        </section>



        <jsp:include page="components/footer.jsp" />
    </body>
</html>
