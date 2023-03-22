<%-- 
    Document   : checkout-status
    Created on : Mar 6, 2023, 4:57:04 PM
    Author     : thaiq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/bootstrap-5.0.2-dist/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/base.css">
        <link rel="stylesheet" href="assets/css/home.css">
        <link rel="stylesheet" href="assets/css/products.css">
        <link rel="stylesheet" href="assets/css/cart.css">


        <link rel="icon" type="image/png" href="assets/images/LogoDoneEdited.png"/>

        <script src="assets/bootstrap-5.0.2-dist/js/bootstrap.bundle.js"></script>

        <title>AmoraShop</title>
        <style>
            .payment-container {
                min-height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .payment-status {
                font-size: 4rem;
                font-weight: 600;
                display: flex;
                align-items: center;
            }

            .icon-success {
                display: block;
                width: 38px;
                align-self: center;
                margin-left: 10px;
            }

            .success-icon {
                cursor: default;
                display: flex
            }

            .payment-description {
                font-size: 1.6rem;
                margin: 10px 0;
                font-weight: 500;
            }

            .payment-navigations {
                display: flex;
                justify-content: flex-start;
            }

            .payment-actions {
                outline: none;
                border: none;
                padding: 12px;
                border-radius: 5px;
                font-size: 14px;
                font-weight: 500;
                transition: ease 0.6s;
            }

            .payment-actions:first-child {
                margin-right: 3rem;
            }

            .payment-actions:hover {
                background-color: var(--main-page-color);
                filter: brightness(1.2);
            }
        </style>

    </head>
    <body>

        <jsp:include page="components/header.jsp" />
        <div class="payment-container row">
            <c:if test="${not empty sessionScope.SUCCESS_PAY}">
                <div class="payment-detail col-6">
                    <div class="payment-status">
                        Payment Successful
                        <i class="icon-success">
                            <img style="width: 48px;" class="success-icon" src="assets/font/Eo_circle_green_checkmark.png" alt="Success">
                        </i>
                    </div>
                    <div class="payment-description">
                        Woohoo! Your order was successfully submit. <br>
                        Thank you for choosing Amora, we are on the way to preparing your order.
                    </div>
                    <div class="payment-navigations">
                        <button class="payment-actions">
                            <a href="homePage.jsp">Back To Home</a>
                        </button>
                        <button class="payment-actions">
                            <a href="OrderListByUserIdController">View Your Order</a>
                        </button>
                    </div>
                </div>
                <div class="payment-img-wrap col-4">
                    <div class="success-img-wrap">
                        <img src="assets/images/paymentSuccess.png" alt="Success">
                    </div>
                </div>
            </c:if>

            <!-- Payment fail html -->
            <c:if test="${empty sessionScope.SUCCESS_PAY}">
                <div class="payment-detail col-6">
                    <div class="payment-status">
                        Payment Fail
                        <i class="icon-success">
                            <img style="width: 48px;" class="success-icon" src="assets/font/PaymentFailIcon.png" alt="Fail">
                        </i>
                    </div>
                    <div class="payment-description">
                        Oops! Something has gone wrong with your order.<br>
                        Sorry about this inconvenience, please check your cart again!
                    </div>
                    <div class="payment-navigations">
                        <button class="payment-actions">
                            <a href="homePage.jsp">Back To Home</a>
                        </button>
                        <button class="payment-actions">
                            <a href="ViewCartController">View Your Cart</a>
                        </button>
                    </div>
                </div>

                <div class="payment-img-wrap col-4">
                    <div class="success-img-wrap">
                        <img src="assets/images/paymentFail.png" alt="Fail">
                    </div>
                </div>
            </c:if>
        </div>
        <jsp:include page="components/footer.jsp" />
    </body>
</html>
