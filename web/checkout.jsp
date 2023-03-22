<%-- 
    Document   : checkout
    Created on : Feb 21, 2023, 9:57:57 PM
    Author     : long
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/base.css">
        <link rel="stylesheet" href="assets/css/home.css">
        <link rel="stylesheet" href="assets/css/products.css">
        <link rel="stylesheet" href="assets/css/cart.css">
        <link rel="stylesheet" href="assets/css/order.css">
        <title>AmoraShop</title>

    </head>
    <body>
        
        <jsp:include page="components/header.jsp" />
        <c:if test="${sessionScope.LOGIN_USER == null}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>

        <div style="font-size: 18px;" class="container mt-5">
            <h1 class="text-center mb-4">Your Order</h1>

            <c:set var="errors" value="${requestScope.CHECKOUT_ERROR}"/>
            <c:if test="${not empty errors}">
                <div class="alert alert-danger">
                    Your order has not been checked out successfully!!!
                    <br>
                    <font>${errors}</font>
                    <br>
                    <a href="ViewCartController">Back to your cart!</a>
                </div>
            </c:if>

            <c:if test="${empty errors}">
                <c:set var="cart" value="${sessionScope.CART.items}"/>
                <c:set var="total" value="${sessionScope.TOTAL_BILL}"/>
                <c:set var="date" value="${sessionScope.DATE}"/>
                <c:set var="orderId" value="${sessionScope.ORDER_ID}"/>

                <div class="alert alert-success">
                    Your order has been checked out successfully!
                    <br>
                    Your order id: ${orderId}
                    <br>
                    Date buy: ${date}
                    <br>
                    <form action="AuthorizePaymentServlet" method="POST">
                        <input type="hidden" name="orderID" value="${sessionScope.ORDER_ID}">
                        <input type="hidden" name="total" value="${sessionScope.TOTAL_BILL}">
                        <button style="font-size: 18px" type="submit" class="btn btn-primary">
                            <img src="assets/font/logo-paypal.svg" class="paypal-icon" alt="Paypal icon"/>
                            Pay with PayPal
                        </button>
                    </form>
                </div>

                <table class="table mt-4">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Product Name</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Price</th>
                            <th scope="col">Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="items" items="${cart}" varStatus="counter">
                            <c:set var="productID" value="${items.getKey()}"/>
                            <c:set var="quantity" value="${items.getValue()}"/>
                            <c:forEach var="product" items="${sessionScope.USER_PRODUCTS}">
                                <c:if test="${product.productID eq productID}">
                                    <tr>
                                        <th scope="row">${counter.count}</th>
                                        <td>${product.name}</td>
                                        <td>${quantity}</td>
                                        <td>${product.price}</td>
                                        <td>${product.price*quantity}</td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                        <tr>
                            <td colspan="4" class="text-right"><strong>TOTAL BILL:</strong></td>
                            <td><strong>${total}$</strong></td>
                        </tr>
                    </tbody>
                </table>

                <c:remove scope="session" var="CART"/>
            </c:if>
            
        </div>
            
            <jsp:include page="components/footer.jsp" />

    </body>




</html>
