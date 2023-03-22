<%-- 
    Document   : order-user
    Created on : Feb 25, 2023, 11:36:12 AM
    Author     : long
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/bootstrap-5.0.2-dist/css/bootstrap.css">
        <script src="assets/bootstrap-5.0.2-dist/js/bootstrap.yubundle.js"></script>
        <link rel="stylesheet" href="assets/css/base.css">
        <link rel="stylesheet" href="assets/css/home.css">
        <link rel="stylesheet" href="assets/css/products.css">
        <link rel="stylesheet" href="assets/css/cart.css">
        <link rel="stylesheet" href="assets/css/order.css">
        <link rel="icon" type="image/png" href="assets/images/LogoDoneEdited.png" />
        <title>AmoraShop</title>

        <style>
            .dropdown:hover .dropdown-menu {
                display: block;
            }

        </style>
    </head>
    <body>
        <jsp:include page="components/header.jsp" />
        <c:if test="${sessionScope.odto.size() == 0}">
            <h1>You don't have any orders</h1>
        </c:if> 


        <div class="body-container">

            <div class="order-body">

                <div class="order-list-header">
                    <h2 class="category-title">Order history</h2>

                    <button class="new-order-btn payment-actions">
                        <a href="ShowProductController">New Order</a>
                    </button>
                </div>

                <div class="order-list-body">
                    <c:set var="o" value="${sessionScope.odto}" />
                    <c:if test="${sessionScope.odto.size() != 0}">
                        <c:if test="${sessionScope.LOGIN_USER != null}">

                            <!--                    <div class="order-sort-title">
                                                    <select class="order-sort-options">
                                                        <option value="feature">View All</option>
                                                        <option value="price-asc">Processing Order</option>
                                                        <option value="price-des">Finished Order</option>                        
                                                    </select>
                                                </div>-->
                            <div class="dropdown">
                                <button class="dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Filter Orders
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <a class="dropdown-item" href="#" onclick="filterOrders('all')">All Orders</a>
                                    <a class="dropdown-item" href="#" onclick="filterOrders('PROCESSING')">Order Processing</a>
                                    <a class="dropdown-item" href="#" onclick="filterOrders('FINISHED')">Order Completed</a>
                                </div>
                            </div>

                            <div class="order-list-content">
                                <table class="table order-table">

                                    <thead>
                                        <tr class="">
                                            <th class="col-1">Order ID</th>
                                            <th class="col">Address</th>
                                            <th class="col-2">Date Order</th>
                                            <th class="col">Note</th>
                                            <th class="col">Total Price</th>
                                            <th class="col-1">Status</th>
                                            <th class="col-1 text-center">Action</th>
                                            <th class="col-1 text-center">Details</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach items="${o}" var="order">
                                            <tr>
                                                <td class="align-middle">${order.orderID}</td>
                                                <td class="align-middle">${order.address}</td>
                                                <td class="align-middle">${order.orderDate}</td>
                                                <td class="align-middle">${order.note}</td>
                                                <td class="align-middle">${order.totalPrice}$</td>
                                                <td class="status-row">
                                                    <c:if test="${order.status eq 'PROCESSING'}">
                                                        <div class="status-icon processing-box">
                                                            <img src="assets/font/pending_black_24dp.svg"
                                                                 class="processing-icon"
                                                                 alt="Processing" />
                                                            <p>On going</p>
                                                        </div>    
                                                    </c:if>
                                                    <c:if test="${order.status eq 'FINISHED'}">
                                                        <div class="status-icon paid-box">
                                                            <img src="assets/font/check_circle_white_24dp.svg"
                                                                 class="paid-icon"
                                                                 alt="paid" />
                                                            <p>Paid</p>
                                                        </div>
                                                    </c:if>

                                                    <c:if test="${order.status eq 'CANCELED'}">
                                                        <div class="status-icon canceled-box">
                                                            <img src="assets/font/highlight_off_black_24dp.svg"
                                                                 class="canceled-icon"
                                                                 alt="canceled" />
                                                            <p>Canceled</p>
                                                        </div>
                                                    </c:if>
                                                </td>
                                                <td class="action-row align-middle">
                                                    <c:if test="${order.status eq 'PROCESSING'}">
                                                        <button class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#blockBtn${order.orderID}">
                                                            Cancel Order
                                                        </button>  
                                                            
                                                        <div class="modal fade" id="blockBtn${order.orderID}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                            <div class="modal-dialog modal-dialog-centered">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <h5 class="modal-title" id="exampleModalLabel">Warning</h5>
                                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                    </div>
                                                                    <div class="p-4 text-center fs-3"
                                                                         style="color: red;">
                                                                        Are you sure you want to Cancel order "<span class="text-dark">${order.orderID}</span>" from your order list?
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <form action="UpdateStatusOrderController" method="get">
                                                                            <input type="hidden" name="orderID" value="${order.orderID}">
                                                                            <input type="hidden" name="status" value="CANCELED">
                                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Back</button>
                                                                            <button type="submit" class="btn btn-outline-danger"><i class="bi bi-trash"></i>Cancel Order</button>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                </td>
                                                <td class="detail-row align-middle">
                                                    <form action="OrderDetailController" method="POST">
                                                        <input type="hidden" name="orderID" value="${order.orderID}">
                                                        <input type="hidden" name="status" value="${order.status}">
                                                        <input type="hidden" name="totalPrice" value="${order.totalPrice}">
                                                        <input type="hidden" name="orderDate" value="${order.orderDate}">

                                                        <input type="image" src="assets/font/article_black_24dp.svg" alt="ViewDetail">
                                                    </form>
                                                </td>
                                            </tr>                                    
                                        </c:forEach>             
                                    </tbody>

                                </table>
                            </div>

                        </c:if>
                    </c:if>
                </div>


            </div>

        </div>



        <script>

        </script>
        <jsp:include page="components/footer.jsp" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    
    
    <!-- Core theme JS-->
    <script src="js/scripts.js"></script>

</html>
