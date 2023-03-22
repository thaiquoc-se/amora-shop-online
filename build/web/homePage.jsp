<%-- 
    Document   : homePage
    Created on : Feb 6, 2023, 3:39:59 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/bootstrap-5.0.2-dist/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/base.css">
        <link rel="stylesheet" href="assets/css/products.css">
        <link rel="stylesheet" href="assets/css/home.css">
        <link rel="stylesheet" href="assets/css/slider.css">
        <link rel="stylesheet" href="assets/css/cart.css">        
        <link rel="icon" type="image/png" href="assets/images/LogoDoneEdited.png"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
        <script src="assets/bootstrap-5.0.2-dist/js/bootstrap.bundle.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>

        <title>AmoraShop</title>
        
        <style>
            a{
                text-decoration: none;
            }
        </style>
    </head>

    <body>
        <div id="wrapper">

            <jsp:include page="components/header.jsp" />          

            <div class="body-container">

                <div id="banner">
                    <img class="mySlides home-banner" src="assets/images/threePerfumeBottleBlackTheme.png" alt="Beautiful man likely perfume">
                    <img class="mySlides home-banner" src="assets/images/happyNewYearWhiteTheme.jpg" alt="Happy Cat Year 2023">                    
                    <img class="mySlides home-banner" src="assets/images/twoPefumeWomanTheme.png" alt="Beautiful woman likely perfume">
                    <button class="button-navigation-banner prev-banner-button" onclick="plusDivs(-1)">&#10094;</button>
                    <button class="button-navigation-banner next-banner-button" onclick="plusDivs(1)">&#10095;</button>
                </div>

                <!--          New Products Section         -->
                <div class="new-product row">
                    <h2 class="new-product-title">New Products</h2>
                    <div class="owl-carou-container">
                        <div class="owl-carousel owl-theme">
                            <c:forEach items="${products}" var="newArr">
                                <div class="item">
                                    <a href="ShowProductDetailUserController?product_id=${newArr.productID}">
                                        <div class="product-img-wrap">
                                            <img src="${newArr.image}" alt="${newArr.name}" class="product-img img-fluid" />
                                        </div>
                                    </a>
                                    <a href="ShowProductDetailUserController?product_id=${newArr.productID}">
                                        <div class="product-name">
                                            ${newArr.name}
                                        </div>
                                    </a>

                                    <div class="product-price">
                                        ${newArr.price}$
                                    </div>
                                </div>                               
                            </c:forEach>                          
                        </div>
                    </div>
                </div>

                <!--            Top Brand Section           -->
                <div class="top-pf-brand-container row">

                    <div class="top-pf-brands-block col-sm-4 col-md-4">
                        <a href="http://localhost:8080/AmoraShop/ShowProductByBrand?brand=DvG" class="top-pf-iblock img-fluid">
                            <img class="img-fluid" src="assets/images/DolceGrabrana-home-page.jpg" alt="Dolce & Gabbana">
                        </a>
                        <div class="top-pf-brand-caption">
                            <div class="top-pf-name">Dolce & Gabbana</div>
                            <a href="http://localhost:8080/AmoraShop/ShowProductByBrand?brand=DvG" class="button-57">
                                <span class="text">See More</span>
                                <span>See More</span>
                            </a>
                        </div>
                    </div>

                    <div class="top-pf-brands-block col-sm-4 col-md-4">
                        <a href="http://localhost:8080/AmoraShop/ShowProductByBrand?brand=Prada" class="top-pf-iblock img-fluid">
                            <img class="img-fluid" src="assets/images/prada-home-page.jpg" alt="Prada">
                        </a>
                        <div class="top-pf-brand-caption">
                            <div class="top-pf-name">Prada</div>
                            <a href="http://localhost:8080/AmoraShop/ShowProductByBrand?brand=Prada" class="button-57">
                                <span class="text">See More</span>
                                <span>See More</span>
                            </a>
                        </div>
                    </div>

                    <div class="top-pf-brands-block col-sm-4 col-md-4">
                        <a href="http://localhost:8080/AmoraShop/ShowProductByBrand?brand=Chanel" class="top-pf-iblock img-fluid">
                            <img class="img-fluid" src="assets/images/chanel-home-page.jpg" alt="Chanel">
                        </a>
                        <div class="top-pf-brand-caption">
                            <div class="top-pf-name">Chanel</div>
                            <a href="http://localhost:8080/AmoraShop/ShowProductByBrand?brand=Chanel" class="button-57">
                                <span class="text">See More</span>
                                <span>See More</span>
                            </a>
                        </div>
                    </div>
                </div>

                <c:if test="${not empty requestScope.favPrd}">
                    <div class="favorite-prd-section row">
                        <div class="quote-slider col-4">
                            <img src="assets/images/Perfume Quote 1.2.jpg" alt="" class="img-fluid quote-item active">
                            <img src="assets/images/Perfume Quote 2.1.jpg" alt="" class="img-fluid quote-item">
                            <img src="assets/images/Perfume Quote 3.jpg" alt="" class="img-fluid quote-item">
                            <img src="assets/images/Perfume Quote 4.jpg" alt="" class="img-fluid quote-item">
                            <img src="assets/images/Perfume Quote 1.2.jpg" alt="" class="img-fluid quote-item">
                        </div>

                        <div class="home-product-container col-8">
                            <div class="home-prd-title">
                                <h2>Favorites</h2>
                                <form action="ShowProductController" method="POST" class="show-all-btn">
                                    <input type="submit" value="Show Alls" class="payment-actions ">
                                </form>
                            </div>

                            <div class="home-prd-list row">
                                <c:forEach items="${favPrd}" var="product">
                                    <div class="prd-item col-5 mb-5">
                                        <div class="prd-wrap row">
                                            <a href="ShowProductDetailUserController?product_id=${product.productID}" class="prd-img col-6">
                                                <img src="${product.image}" alt="${product.name}" class="prd-img-inner img-fluid">                            
                                            </a>
                                            <div class="prd-content col-6">
                                                <a href="ShowProductDetailUserController?product_id=${product.productID}" class="prd-name">
                                                    ${product.name}
                                                </a>
                                                <div class="prd-brand">
                                                    ${product.brand}
                                                </div>
                                                <div class="prd-price">
                                                    ${product.price}$
                                                </div>
                                            </div>
                                        </div>
                                    </div>    
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:if>

                <!--
                                <div class="about-us processing-section">
                <c:if test="${not empty requestScope.newArrivalsHome}">
                    <c:forEach items="${newArrivalsHome}" var="product">

                        <div class="product-items col-12 col-sm-6 col-md-6 col-xl-3">
                            <a href="ShowProductDetailUserController?product_id=${product.productID}">
                                <div class="product-img-wrap">
                                    <img class="product-img img-fluid" src="${product.image}" alt="${product.name}" >
                                </div>
                            </a>
                            <div class="product-name">
                                <a href="ShowProductDetailUserController?product_id=${product.productID}">${product.name}</a>

                            </div>
                            <div class="product-price">
                        ${product.price}$
                    </div>
                </div>

                    </c:forEach>
                </c:if>
            </div>
        </div>-->

                <jsp:include page="components/footer.jsp" />

            </div>
            <script src="assets/js/script.js"></script>
            <script>
                        let slides = document.querySelectorAll('.quote-item');
                        let currentSlide = 0;

                        function nextSlide() {
                            slides[currentSlide].classList.remove('active');
                            currentSlide = (currentSlide + 1) % slides.length;
                            slides[currentSlide].classList.add('active');
                        }

                        setInterval(nextSlide, 8000);
            </script>
    </body>

</html>