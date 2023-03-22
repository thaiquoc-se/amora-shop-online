<%-- 
    Document   : review-paypal
    Created on : Mar 3, 2023, 10:29:23 PM
    Author     : thaiq
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">>

        <title>AmoraShop</title>
        <style>
            td {
                font-size: 18px;
            }

            input.btn.btn-primary {
                font-size: 16px;
                padding: 12px 40px;
                margin: 15px;
            }
            h3{
                color: var(--main-page-color);
                font-weight: 600;
                font-size: 22px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="components/header.jsp" />


        <div>
            <form action="ExecutePaymentServlet" method="post">

                <input type="hidden" name="paymentId" value="${param.paymentId}" />
                <input type="hidden" name="PayerID" value="${param.PayerID}" />
                <input type="hidden" name="orderID" value="${transaction.description}" />

                <div class="container mt-5">
                    <h1 class="text-center mb-4">Review Your Information</h1>
                    <hr>
                    <div class="row">
                        <div class="col-md-6">
                            <h3>Transaction Details:</h3>
                            <table class="table table-bordered">
                                <tr>
                                    <td>Total:</td>
                                    <td>${transaction.amount.total}</td>
                                </tr>
                                <tr>
                                    <td>Order ID:</td>
                                    <td>${transaction.description}</td>
                                </tr>
                            </table>
                            <h3>Payer Information:</h3>
                            <table class="table table-bordered">
                                <tr>
                                    <td>First Name:</td>
                                    <td>${payer.firstName}</td>
                                </tr>
                                <tr>
                                    <td>Last Name:</td>
                                    <td>${payer.lastName}</td>
                                </tr>
                                <tr>
                                    <td>Email:</td>
                                    <td>${payer.email}</td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-6">                      
                            <h3>Shipping Address:</h3>
                            <table class="table table-bordered col-12">
                                <tr>
                                    <td>Recipient Name:</td>
                                    <td>${shippingAddress.recipientName}</td>
                                </tr>
                                <tr>
                                    <td>Line 1:</td>
                                    <td>${shippingAddress.line1}</td>
                                </tr>
                                <tr>
                                    <td>City:</td>
                                    <td>${shippingAddress.city}</td>
                                </tr>
                                <tr>
                                    <td>State:</td>
                                    <td>${shippingAddress.state}</td>
                                </tr>
                                <tr>
                                    <td>Country Code:</td>
                                    <td>${shippingAddress.countryCode}</td>
                                </tr>
                                <tr>
                                    <td>Postal Code:</td>
                                    <td>${shippingAddress.postalCode}</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="text-center">
                        <input type="submit" value="Pay Now" class="btn btn-primary" />
                    </div>
                </div>


            </form>
        </div>
        <jsp:include page="components/footer.jsp" />
    </body>
</html>
<!--                <table>
                    <tr>
                        <th colspan="2">Transaction Details:</th>
                        <td>
                            <input type="hidden" name="paymentId" value="${param.paymentId}" />
                            <input type="hidden" name="PayerID" value="${param.PayerID}" />
                            <input type="hidden" name="orderID" value="${transaction.description}" />

                        </td>
                    </tr>
                    <tr>
                        <td>Total:</td>
                        <td>${transaction.amount.total} USD</td>
                    </tr>

                    <tr>
                        <td>order ID</td>
                        <td>${transaction.description}</td>
                    </tr>

                    <tr>
                        <th colspan="2">Payer Information:</th>
                    </tr>
                    <tr>
                        <td>First Name:</td>
                        <td>${payer.firstName}</td>
                    </tr>
                    <tr>
                        <td>Last Name:</td>
                        <td>${payer.lastName}</td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td>${payer.email}</td>
                    </tr>

                    <tr>
                        <th colspan="2">Shipping Address:</th>
                    </tr>
                    <tr>
                        <td>Recipient Name:</td>
                        <td>${shippingAddress.recipientName}</td>
                    </tr>
                    <tr>
                        <td>Line 1:</td>
                        <td>${shippingAddress.line1}</td>
                    </tr>
                    <tr>
                        <td>City:</td>
                        <td>${shippingAddress.city}</td>
                    </tr>
                    <tr>
                        <td>State:</td>
                        <td>${shippingAddress.state}</td>
                    </tr>
                    <tr>
                        <td>Country Code:</td>
                        <td>${shippingAddress.countryCode}</td>
                    </tr>
                    <tr>
                        <td>Postal Code:</td>
                        <td>${shippingAddress.postalCode}</td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Pay Now" />
                        </td>
                    </tr>    



</body>
</html>
