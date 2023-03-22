<%-- 
    Document   : shipping-details
    Created on : Feb 23, 2023, 1:55:52 PM
    Author     : long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/bootstrap-5.0.2-dist/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/base.css">
        <link rel="stylesheet" href="assets/css/home.css">
        <!--<link rel="stylesheet" href="assets/css/products.css">-->
        <link rel="stylesheet" href="assets/css/cart.css">
        <link rel="icon" type="image/png" href="assets/images/LogoDoneEdited.png"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
        <script src="assets/bootstrap-5.0.2-dist/js/bootstrap.bundle.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>

        <title>AmoraShop</title>
    </head>
    <body>
        <jsp:include page="components/header.jsp" />


        <c:set var="cart" value="${sessionScope.CART.items}" />
        <c:set var="userProduct" value="${sessionScope.USER_PRODUCTS}" />


        <div class="wrapper row">
            <div class="shipping-details-section row">
                <div class="main col-7">
                    <div class="main-header">
                        <a href="/" class="logo-text">
                            Amora Shop
                        </a>

                        <div class="bread-scrumb">
                            <div class="bread-scrumb-item">
                                <a href="viewCart.jsp">Cart</a>
                            </div>
                            <div class="bread-scrumb-item">
                                Shipping Details
                            </div>
                            <div class="bread-scrumb-item">
                                Delivery Method & Payment
                            </div>
                        </div>
                    </div>

                    <div class="main-body">
                        <div class="main-title">
                            Shipping Details
                        </div>
                        <div class="logged-in-user-infor">
                            <img src="${sessionScope.LOGIN_USER.getImage()}" class="logged-in-user-img" />
                            <div class="logged-in-user">
                                <div class="user-name-mail">
                                    ${sessionScope.LOGIN_USER.getFullName()}&nbsp;(${sessionScope.LOGIN_USER.getEmail()})
                                </div>
                                <a href="LogoutController" class="user-logout-action">
                                    Logout
                                </a>
                            </div>
                        </div>

                        <form action="CheckoutController" method="POST" class="shipping-details-form">
                            <div class="field-input-wrapper">
                                <span>Full name:</span>
                                <input class="field-title" type="text"
                                       placeholder="Full name" name="fullName"
                                       value="${sessionScope.LOGIN_USER.getFullName()}"/>
                                <input type="hidden" value="PROCESSING" name="status">
                                <input value="${sessionScope.LOGIN_USER.getUserID()}" type="hidden" name="userID">

                            </div>
                            <div class="field-input-wrapper">
                                <span>Phone number:</span>
                                <input class="field-title" type="text"
                                       placeholder="Phone number" name="phoneNumber"
                                       value="${sessionScope.LOGIN_USER.getPhoneNumber()}"/>
                            </div>
                            <div class="field-input-wrapper">
                                <span>Address:</span>
                                <input class="field-title" type="text"
                                       placeholder="Address" name="address"
                                       value="${sessionScope.LOGIN_USER.getAddress()}"/>
                            </div>
                            <div class="field-input-wrapper">
                                <span>Note:</span>
                                
                                <textarea class="input-field form-control" placeholder="Any wants about products? Special gift?" 
                                    type="text" name="note"></textarea>
                            </div>

                            <div class="shipping-details-action">
                                <a href="viewCart.jsp">Cart</a>
                                <button type="submit" class="continue-to-payment-btn">
                                    <a>Continue To Payment</a>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>


                <div class="side-bar col-5">
                    <div class="sidebar-content">
                        <div class="order-summary">
                            <div class="order-list">
                                <c:if test="${not empty cart}">
                                    <c:forEach var="item" items="${cart}">
                                        <c:set var="productId" value="${item.key}" />
                                        <c:set var="quantity" value="${item.value}" />
                                        <c:forEach var="product" items="${userProduct}">
                                            <c:if test="${product.productID eq productId}">
                                                <div class="order-item">
                                                    <div class="product-image-container">
                                                        <img src="${product.image}" alt="${product.name}" class="cart-item-img">
                                                        <div class="buy-quantity">${quantity}</div>
                                                    </div>
                                                    <div class="order-describe">
                                                        <div class="order-item-name">
                                                            ${product.name}
                                                        </div>
                                                        <div class="order-item-capacity">
                                                            ${product.capacity} ml
                                                        </div>
                                                        <div class="order-item-price">
                                                            <fmt:formatNumber value="${product.price*quantity}" pattern="#.##"/>$
                                                            <c:set var="total_all" value="${total_all + product.price*quantity}" />
                                                        </div>
                                                    </div>
                                                </div> 

                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                </c:if>                         
                            </div>

                            <div class="order-total-sum">
                                <p class="text">
                                    Total:
                                </p>
                                <div class="order-sum-cald">
                                    <i>Dollar</i>
                                    <fmt:formatNumber value="${total_all}" pattern="#.##"/>$
                                </div>
                            </div>
                        </div>


                    </div>

                </div>
            </div>

        </div>



        <jsp:include page="components/footer.jsp" />

    </body>
</html>
