<%-- 
    Document   : viewCart
    Created on : Feb 21, 2023, 1:39:13 PM
    Author     : long
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/bootstrap-5.0.2-dist/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/base.css">
        <link rel="stylesheet" href="assets/css/home.css">
        <link rel="stylesheet" href="assets/css/products.css">
        <link rel="stylesheet" href="assets/css/cart.css">
        <link rel="icon" type="image/png" href="assets/images/LogoDoneEdited.png"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
        <script src="assets/bootstrap-5.0.2-dist/js/bootstrap.bundle.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
        <title>AmoraShop</title>
    </head>

    <body>

        <%--  <h1>----Inventory----</h1>
          <c:set var="cart" value="${sessionScope.CART.items}" />
          <c:set var="userProduct" value="${sessionScope.USER_PRODUCTS}" />
          <c:if test="${not empty cart}">
              <form action="RemoveCartServlet">
                  <table border="1">
                      <thead>
                          <tr>
                              <th>No.</th>
                              <th>Book Name</th>                         
                              <th>Quantity</th>
                              <th>Price</th>
                              <th>Total</th>
                              <th>Action</th>
                          </tr>
                      </thead>
                      <tbody>
                          <c:forEach var="item" items="${cart}" varStatus="counter">
                              <c:set var="productId" value="${item.key}" />
                              <c:set var="quantity" value="${item.value}" />
                              <c:forEach var="product" items="${userProduct}">
                                  <c:if test="${product.productID eq productId}">
                                      <tr>
                                          <td>
                                              ${counter.count}
                                              .</td>
                                          <td>
                                              ${product.name}
                                          </td>
                                          <td>
                                              ${quantity}
                                          </td>
                                          <td>
                                              ${product.price}
                                          </td>
                                          <td>
                                              ${product.price*quantity}
                                          </td>
                                          <td>
                                              <input type="submit" name="chkItem" 
                                                     value="${productId}" />
                                          </td>
                                      </tr>
                                  </c:if>

                            </c:forEach>
                        </c:forEach>
                        <tr>
                            <td colspan="5">
                                <a href="showProduct">Add More Books to Your Cart.</a>  
                            </td>

                        </tr>
                    </tbody>
                </table>
            </form>
            
            <form action="AuthorizePayment" method="POST">
                <input name="name" value="${product.name}" type="hidden">
                <input name="quantity" value="${quantity}" type="hidden">
                <input name="total" value="${product.price*quantity}" type="hidden">
                <input type="submit" value="Paypal">
            </form>
        <li>
            <a href="shipping-details.jsp" class="header-icon">Buy</a>           
        </li>
    </c:if>
    <c:if test="${empty cart}">
        <h2>
            No cart is existed!!!!! 
        </h2>
        <a href="ShowProductController">Click here to buy more</a>  
    </c:if>--%>
        <jsp:include page="components/header.jsp"/>
        <c:if test="${sessionScope.LOGIN_USER.roleID == 1 ||sessionScope.LOGIN_USER.roleID == 3 }">
            <h1 style="color: red; text-align:center">Your Role Not Support This Service</h1>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER.roleID == 2||sessionScope.LOGIN_USER.roleID == null }">
            <c:set var="cart" value="${sessionScope.CART.items}" />
            <c:set var="userProduct" value="${sessionScope.USER_PRODUCTS}" />
            <%
                float total = 0;
            %>
            <div class="body-container">

                <div class="bread-crumb-container">
                    <div class="bread-crumbs">
                        <a href="homePage.html" class="main-page-link">Home</a>
                        <div class="slash">/</div>
                        <a href="#" class="main-page link">Cart</a>
                    </div>
                </div>

                <div class="search-heading-page">
                    <div class="cart-container">
                        <div class="header-text-container">
                            <div class="header-page-title">
                                Your Inventory
                            </div>
                            <div class="header-quantity-result">
                                <c:if test="${sessionScope.CART.items.size() gt 1}">
                                    There are ${sessionScope.CART.items.size()} products in your cart    
                                </c:if>
                                <c:if test="${sessionScope.CART.items.size() eq 1}">
                                    There are ${sessionScope.CART.items.size()} product in your cart    
                                </c:if>
                                <c:if test="${empty cart}">
                                    There is no product in your cart    
                                </c:if>
                                <c:if test="${requestScope.error != null}">
                                    <div style="display: flex; justify-content: center; flex-direction: column; align-items: center; padding-top: 5px ">
                                        <h3 class="text-danger">${error}</h3>
                                        <a class="login-navigation" href="login.jsp">Click here to login</a>    
                                    </div>
                                </c:if> 
                            </div>
                        </div>
                    </div>
                </div>


                <div class="cart-content-container">
                    <div class="cart-product-list row">
                        <c:if test="${not empty cart}">

                            <c:forEach var="item" items="${cart}">
                                <c:set var="productId" value="${item.key}" />
                                <c:set var="quantity" value="${item.value}" />
                                <c:forEach var="product" items="${userProduct}">
                                    <c:if test="${product.productID eq productId}">
                                        <div class="cart-item row">
                                            <div class="product-image-container col-3">
                                                <img class="cart-item-img" src="${product.image}"
                                                     alt="${product.name}">
                                            </div>
                                            <div class="item-content col-9">
                                                <a href="ShowProductDetailUserController?product_id=${product.productID}" class="item-name">
                                                    ${product.name}
                                                </a>                                                                                                        

                                                <div class="item-price">
                                                    ${product.price}$
                                                </div>

                                                <c:if test="${not empty requestScope.ERROR_QUAN_DB}">
                                                    <div class="error-notification">${requestScope.ERROR_QUAN_DB}</div>
                                                </c:if>
                                                <form action="UpdateItemCartServlet" class="select-quantity-area">
                                                    <button type="submit" value="-" name="minusQuan" class="quantity-btn">
                                                        -
                                                    </button>
                                                    <p class="quantity-selector">${quantity}</p>
                                                    <button type="submit" name="plusQuan" value="+" class="quantity-btn">
                                                        +
                                                    </button>
                                                    <input type="hidden" name="productID" value="${productId}">
                                                </form>

                                                <div class="product-item-action">
                                                    <form action="RemoveCartServlet">

                                                        <button class="delete-item-btn" type="submit" name="chkItem" value="${productId}">
                                                            <img class="detete-item-icon" src="assets/font/close_black_24dp.svg" alt="Remove Item">
                                                        </button>
                                                    </form>

                                                    <div class="item-total-money">
                                                        <fmt:formatNumber value="${product.price*quantity}" pattern="#.##"/>$                        

                                                        <c:set var="total_all" value="${total_all + product.price*quantity}" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>                                       
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                        </c:if>

                    </div>
                    <c:if test="${not empty cart}">
                        <div class="order-info-container">
                            <div class="order-info">
                                Total price:
                                <div class="total-price">
                                    <fmt:formatNumber value="${total_all}" pattern="#.##"/>$                        
                                </div>
                            </div>

                            <div class="order-actions">
                                <a class="button-57" href="ShowProductController" class="continues-buying-btn">
                                    <span>Continues Buying</span>
                                    <span>Continues Buying</span>
                                </a>
                                <a class="button-57" href="CheckoutController" class="checkout-btn">  
                                    <span>Check Out</span>
                                    <span>Check Out</span>
                                </a>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${empty cart}">
                        <div class="order-actions-no-product">
                            <span class="noProduct">
                                Your cart is empty.
                            </span>
                            <a class="button-57" href="ShowProductController" class="continues-buying-btn">
                                <span>Continues Buying</span>
                                <span>Continues Buying</span>
                            </a>
                        </div>
                    </c:if>

                </div>

            </div>

        </c:if>
        <jsp:include page="components/footer.jsp"/>





    </body>
</html>
