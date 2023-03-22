<%-- 
    Document   : receipt
    Created on : Mar 4, 2023, 2:12:52 PM
    Author     : thaiq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/bootstrap-5.0.2-dist/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/base.css">
        <link rel="stylesheet" href="assets/css/home.css">
        <link rel="icon" type="image/png" href="assets/images/LogoDoneEdited.png"/>
        <script src="assets/bootstrap-5.0.2-dist/js/bootstrap.bundle.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <title>AmoraShop</title>
        <style>
            td {
                font-size: 18px;
            }

            h3{
                color: var(--main-page-color);
                font-weight: 600;
                font-size: 22px;
            }
            p{
                font-size: 18px
            }
            div.text-center a.btn.btn-primary {
                padding: 10px;
                font-size: 1.6rem;
                margin: 2rem 0;
            }
        </style>
    </head>
    <body>
        <jsp:include page="components/header.jsp" />



        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <h2 class="text-center mb-4">Receipt Details</h2>
                    <div class="card border-primary">
                        <div class="card-body">
                            <h3 class="card-title text-primary">Amora Shop</h3>
                            <p class="card-text">Payer: ${payer.firstName} ${payer.lastName}</p>
                            <p class="card-text">Order id: ${transaction.description}</p>
                            <hr>
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td>Subtotal:</td>
                                        <td class="text-right">${transaction.amount.details.subtotal}$</td>
                                    </tr>
                                    <tr>
                                        <td>Shipping:</td>
                                        <td class="text-right">${transaction.amount.details.shipping}$</td>
                                    </tr>
                                    <tr>
                                        <td>Tax:</td>
                                        <td class="text-right">${transaction.amount.details.tax}$</td>
                                    </tr>
                                    <tr>
                                        <td>Total:</td>
                                        <td class="text-right text-success">${transaction.amount.total}$</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="text-center">
            <a href="homePage.jsp" class="btn btn-primary">Return Home Page</a>
        </div>

        <!--        <div align="center">
                    <h1>Payment Done. Thank you for purchasing our products</h1>
                    <br/>
                    <h2>Receipt Details:</h2>
                    <table>
                        <tr>
                            <td><b>Merchant:</b></td>
                            <td>Amora Shop</td>
                        </tr>
                        <tr>
                            <td><b>Payer:</b></td>
                            <td>${payer.firstName} ${payer.lastName}</td>      
                        </tr>
                        <tr>
                            <td><b>Order id:</b></td>
                            <td>${transaction.description}</td>
                        </tr>
                        <tr>
                            <td><b>Subtotal:</b></td>
                            <td>${transaction.amount.details.subtotal} USD</td>
                        </tr>
                        <tr>
                            <td><b>Shipping:</b></td>
                            <td>${transaction.amount.details.shipping} USD</td>
                        </tr>
                        <tr>
                            <td><b>Tax:</b></td>
                            <td>${transaction.amount.details.tax} USD</td>
                        </tr>
                        <tr>
                            <td><b>Total:</b></td>
                            <td>${transaction.amount.total} USD</td>
                        </tr>                    
                    </table>
                </div>-->

        <jsp:include page="components/footer.jsp" />

    </body>
</html>
