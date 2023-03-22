<%-- 
    Document   : products-user-page
    Created on : Feb 13, 2023, 5:54:22 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/bootstrap-5.0.2-dist/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/responsive.css">
        <link rel="stylesheet" href="assets/css/base.css">
        <link rel="stylesheet" href="assets/css/home.css">
        <link rel="stylesheet" href="assets/css/products.css">
        <link rel="icon" type="image/png" href="assets/images/LogoDoneEdited.png"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
        <script src="assets/bootstrap-5.0.2-dist/js/bootstrap.bundle.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>

        <title>AmoraShop</title>

        <style>
            /* Define the animation keyframes */
            @keyframes fire {
                0% {
                    transform: scale(1);
                }
                50% {
                    transform: scale(1.1) rotate(5deg);
                }
                100% {
                    transform: scale(1);
                }
            }

            /* Apply the animation to the header */
            .category-title {
                animation: fire 0.8s ease-out infinite alternate;
                color: #FF5733;
            }
        </style>
    </head>
    <body>

        <jsp:include page="components/header.jsp" />


        <div class="body-container">
            <div class="bread-crumb-container">
                <div class="bread-crumbs">
                    <a href="/AmoraShop" class="main-page link">Home</a>
                    <div class="slash">/</div>
                    <a href="#" class="main-page link">New Arrivals</a>
                </div>
            </div>
            <div id="banner">
                <img class="mySlides products-banner" src="assets/images/happyNewYearWhiteTheme.jpg" alt="Happy Cat Year 2023">
            </div>

            <div class="products-body row">

                <div class="right-product-list col-12">
                    <div class="product-list-header">
                        <h2 style="padding-left: 5rem;" class="category-title">NEW ARRIVALS</h2>
                        <div class="category-sort-cotainer">
                            <select id="sort-by" class="category-sort-options">
                                <option value="price-asc">Ascending Price</option>
                                <option value="price-des">Descending Price</option>
                                <option value="name-a-z">Name: A - Z</option>
                                <option value="name-z-a">Name: Z - A</option>
                            </select>
                        </div>
                    </div>

                    <div class="product-list-container row">

                        <!--Testing area-->

                        <c:forEach items="${products}" var="product">

                            <div style="display: flex; align-items: center;" class="product-items col-12 col-sm-6 col-md-6 col-xl-3">
                                <a href="ShowProductDetailUserController?product_id=${product.productID}">
                                    <div class="product-img-wrap">
                                        <img class="product-img img-fluid" src="${product.image}" alt="${product.name}" >
                                    </div>
                                </a>

                                <div class="product-name">
                                    <a href="ShowProductDetailUserController?product_id=${product.productID}">
                                        ${product.name}
                                    </a>
                                </div>
                                <div class="product-price">
                                    ${product.price}$
                                </div>
                            </div>

                        </c:forEach>

                    </div>

                    <nav aria-label="..." class="pagination-container">
                        <ul class="pagination">
                            <li class="page-item">
                                <a <c:if test="${page!=1}">
                                        href="ShowAllArrivalsProduct?page=${page-1}"
                                    </c:if> class="page-link" aria-label="Next">
                                    <span aria-hidden="true">«</span>
                                </a>
                            </li>

                            <c:forEach begin="1" end="${totalPage}" var="i">
                                <li class="page-item ${i==page?"active":""}"><a class="page-link" href="ShowAllArrivalsProduct?page=${i}">${i}</a></li>
                                </c:forEach>    

                            <li class="page-item">
                                <a <c:if test="${page!=totalPage}">
                                        href="ShowAllArrivalsProduct?page=${page+1}"
                                    </c:if> class="page-link" aria-label="Next">
                                    <span aria-hidden="true">»</span>
                                </a>
                            </li>
                        </ul>
                    </nav>

                </div>
            </div>
        </div>

        <jsp:include page="components/footer.jsp" />
        <script src="assets/js/SortProduct.js"></script>

    </body>
</html>
