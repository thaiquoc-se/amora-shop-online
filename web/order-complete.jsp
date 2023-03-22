<%-- 
    Document   : order-processing-staff
    Created on : Mar 2, 2023, 1:28:28 PM
    Author     : long
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Order Manager</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="hien-css/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body>

        <c:if test="${sessionScope.orderListCpl.size() != 0}">
            <c:if test="${sessionScope.LOGIN_USER != null}">


                
                    <body class="sb-nav-fixed">
                        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
                            <!-- Navbar Brand-->
                            <a class="navbar-brand ps-3" href="homePage.jsp">Aroma</a>
                            <!-- Sidebar Toggle-->
                            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
                            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                        <li><a class="dropdown-item" href="http://localhost:8080/AmoraShop/LogoutController">Logout</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                        <div id="layoutSidenav">
                            <div id="layoutSidenav_nav">
                                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                                    <div class="sb-sidenav-menu">
                                        <div class="nav">
                                            <div class="sb-sidenav-menu-heading">Amora System</div>
                                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseAuthentication" aria-expanded="false" aria-controls="collapseLayouts">
                                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                                Manager
                                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                            </a>
                                            <div class="collapse" id="collapseAuthentication" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">

                                                <nav class="sb-sidenav-menu-nested nav">
                                                    <!--                    ROLE load tu database kh dc fix cung-->   
                                                    <c:choose>
                                                        <c:when test="${sessionScope.LOGIN_USER.roleID == 1}">
                                                            <a class="nav-link" href="http://localhost:8080/AmoraShop/ShowUserController">User Manager</a>
                                                            <a class="nav-link" href="http://localhost:8080/AmoraShop/ShowProductAdminController">Product Manager</a>
                                                            <a class="nav-link" href="OrderCompleteController">Order Complete</a>
                                                            <a class="nav-link" href="ShowLogController">User Action</a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a class="nav-link" href="http://localhost:8080/AmoraShop/ShowProductAdminController">Product Manager</a>
                                                            <a class="nav-link" href="OrderProcessingStaffController">Order Processing</a>
                                                            <a class="nav-link" href="OrderCompleteController">Order Complete</a>
                                                            <a class="nav-link" href="FeedBackStaffController">Feed Back processing</a>
                                                        </c:otherwise>
                                                    </c:choose>


                                                </nav>
                                            </div>


                                        </div>
                                    </div>
                                    <div class="sb-sidenav-footer">
                                        <div class="small">Logged in as: </div>
                                        <c:choose>
                                            <c:when test="${sessionScope.LOGIN_USER.roleID == 1}">
                                                ADMIN: ${sessionScope.LOGIN_USER.fullName}
                                            </c:when>
                                            <c:otherwise>
                                                STAFF: ${sessionScope.LOGIN_USER.fullName}
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </nav>
                            </div>
                            <div id="layoutSidenav_content">
                                <main>
                                    <div class="container-fluid px-4">

                                        <ol class="breadcrumb mb-4">
                                            <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
                                            <li class="breadcrumb-item active">Order Complete</li>
                                        </ol>

                                        <div class="card mb-4">
                                            <div class="card-header">
                                                <i class="fas fa-table me-1"></i>
                                                ORDER COMPLETE
                                            </div>
                                            <div class="card-body">
                                                <table id="datatablesSimple">
                                                    <thead>
                                                        <tr>
                                                            <th>orderID</th>
                                                            <th>Address</th>
                                                            <th>Status</th>
                                                            <th>Order Date</th>
                                                            <th>Total Bill</th>
                                                            <th>Note</th>
                                                            <th>View Details</th>

                                                        </tr>
                                                    </thead>

                                                    <tbody>
                                                        <c:forEach items="${sessionScope.orderListCpl}" var="order">
                                                            <tr>
                                                                <td>${order.orderID}</td>
                                                                <td>${order.address}</td>
                                                                <td>${order.status}</td>
                                                                <td>${order.orderDate}</td>
                                                                <td>${order.totalPrice}</td>
                                                                <td>${order.note}</td>

                                                                <td>
                                                                    <form action="OrderDetailController" method="POST">
                                                                        <input type="hidden" name="orderID" value="${order.orderID}">
                                                                        <input type="hidden" name="status" value="${order.status}">
                                                                        <input type="hidden" name="totalPrice" value="${order.totalPrice}">
                                                                        <input type="hidden" name="orderDate" value="${order.orderDate}">
                                                                        <input type="submit" value="View Your Order Detail">
                                                                    </form>
                                                                </td>




                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </main>

                            </div>
                        </div>
                    </c:if>


                </c:if>
                <c:if test="${sessionScope.orderListCpl.size() == 0}"><h1> Don't have any order complete</h1></c:if>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
                <!-- Core theme JS-->
                <script src="js/scripts.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
                <script src="hien-css/js/scripts.js"></script>
                <script src="hien-css/js/datatables-simple-demo.js"></script>
                <script src="hien-css/assets/demo/chart-area-demo.js"></script>
                <script src="hien-css/assets/demo/chart-bar-demo.js"></script>
                <script src="hien-css/assets/demo/chart-pie-demo.js"></script>
                <script src="hien-css/assets/demo/datatables-demo.js"></script>

            </body>
        </html>
