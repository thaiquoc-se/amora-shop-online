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
        <link rel="stylesheet" href="assets/css/responsive.css"/>
        <link rel="stylesheet" href="assets/css/base.css">
        <link rel="stylesheet" href="assets/css/home.css">
        <link rel="stylesheet" href="assets/css/products.css">
        <link rel="icon" type="image/png" href="assets/images/LogoDoneEdited.png"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
        <script src="assets/bootstrap-5.0.2-dist/js/bootstrap.bundle.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>

        <title>AmoraShop</title>
    </head>
    <body>

        <jsp:include page="components/header.jsp" />


        <div class="body-container">
            <div class="bread-crumb-container">
                <div class="bread-crumbs">
                    <a href="/AmoraShop" class="main-page link">Home</a>
                    <div class="slash">/</div>
                    <a href="ShowProductController" class="main-page link">Products</a>
                </div>
            </div>
            <div id="banner">
                <img class="mySlides products-banner" src="assets/images/happyNewYearWhiteTheme.jpg" alt="Happy Cat Year 2023">
            </div>

            <div class="products-body row">
                <div class="left-nav-container col-2">
                    <div id="tree" class="left-nav-filter">
                        <form action="ShowProductByBrand" method="POST">
                            <ul>
                                <li>
                                    <label for="node-1">Brands</label>
                                    <input type="checkbox" id="node-1" />
                                    <ul>
                                        <li>
                                            <a class="category-item" for="node-1-1" href="http://localhost:8080/AmoraShop/ShowProductByBrand?brand=Chanel">Chanel</a>
                                        </li>
                                        <li>
                                            <a class="category-item" for="node-1-2" href="http://localhost:8080/AmoraShop/ShowProductByBrand?brand=Dior">Dior</a>
                                        </li>
                                        <li>
                                            <a class="category-item" for="node-1-2" href="http://localhost:8080/AmoraShop/ShowProductByBrand?brand=Prada">Prada</a>
                                        </li>
                                        <li>
                                            <a class="category-item" for="node-1-2" href="http://localhost:8080/AmoraShop/ShowProductByBrand?brand=DvG">D&G</a>
                                        </li>
                                        <li>
                                            <a class="category-item" for="node-1-2" href="http://localhost:8080/AmoraShop/ShowProductByBrand?brand=Gucci">Gucci</a>
                                        </li>
                                        <li>
                                            <a class="category-item" for="node-1-2" href="http://localhost:8080/AmoraShop/ShowProductByBrand?brand=YSL">YSL</a>
                                        </li><!-- comment -->

                                    </ul>
                                </li>
                            </ul>
                        </form>
                        <form action="ShowProductByBrandAndCategory" method="POST">
                            <ul>
                                <li>
                                    <label for="node-2">For Man</label>
                                    <input type="checkbox" id="node-2" />

                                    <ul>
                                        <li>
                                            <a class="category-item" for="node-2-1" href="http://localhost:8080/AmoraShop/ShowProductByBrandAndCategory?brand=Chanel&cateID=1">Chanel</a>
                                        </li>
                                        <li>
                                            <a class="category-item" for="node-2-2" href="http://localhost:8080/AmoraShop/ShowProductByBrandAndCategory?brand=Dior&cateID=1">Dior</a>
                                        </li>
                                        <li>
                                            <a class="category-item" for="node-2-2" href = "http://localhost:8080/AmoraShop/ShowProductByBrandAndCategory?brand=Prada&cateID=1">Prada</a>
                                        </li>
                                        <li>
                                            <a class="category-item" for="node-2-2" href = "http://localhost:8080/AmoraShop/ShowProductByBrandAndCategory?brand=DvG&cateID=1">D&G</a>
                                        </li>
                                        <li>
                                            <a class="category-item" for="node-2-2" href = "http://localhost:8080/AmoraShop/ShowProductByBrandAndCategory?brand=Gucci&cateID=1">Gucci</a>
                                        </li>

                                        <li>
                                            <a class="category-item" for="node-2-2" href = "http://localhost:8080/AmoraShop/ShowProductByBrandAndCategory?brand=YSL&cateID=1">YSL</a>
                                        </li><!-- comment -->
                                    </ul>

                                </li>
                            </ul>
                        </form>
                        <form action="ShowProductByBrandAndCategory" method="POST">
                            <ul>
                                <li>
                                    <label for="node-3">For Woman</label>
                                    <input type="checkbox" id="node-3" />

                                    <ul>
                                        <li>
                                            <a class="category-item" for="node-2-1" href="http://localhost:8080/AmoraShop/ShowProductByBrandAndCategory?brand=Chanel&cateID=2">Chanel</a>
                                        </li>
                                        <li>
                                            <a class="category-item" for="node-2-2" href="http://localhost:8080/AmoraShop/ShowProductByBrandAndCategory?brand=Dior&cateID=2">Dior</a>
                                        </li>
                                        <li>
                                            <a class="category-item" for="node-2-2" href = "http://localhost:8080/AmoraShop/ShowProductByBrandAndCategory?brand=Prada&cateID=2">Prada</a>
                                        </li>
                                        <li>
                                            <a class="category-item" for="node-2-2" href = "http://localhost:8080/AmoraShop/ShowProductByBrandAndCategory?brand=DvG&cateID=2">D&G</a>
                                        </li>
                                        <li>
                                            <a class="category-item" for="node-2-2" href = "http://localhost:8080/AmoraShop/ShowProductByBrandAndCategory?brand=Gucci&cateID=2">Gucci</a>
                                        </li>

                                        <li>
                                            <a class="category-item" for="node-2-2" href = "http://localhost:8080/AmoraShop/ShowProductByBrandAndCategory?brand=YSL&cateID=2">YSL</a>
                                        </li><!-- comment -->

                                    </ul>
                                </li>
                            </ul>
                        </form>
                    </div>
                </div>

                <div class="right-product-list col-10">
                    <div class="product-list-header">
                        <c:if test="${empty requestScope.brand}" >
                            <h2 class="category-title">All products</h2>
                        </c:if>

                        <c:if test="${not empty requestScope.brand}" >
                            <h2 class="category-title">All products ${requestScope.brand}</h2>
                        </c:if>



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

                    </div>

     
                        <c:if test="${cateID != null && brand == null}">
                             <c:choose>
                                
                                <c:when test="${totalPage <= 1}">

                                </c:when>
                                <c:otherwise>
                                    <nav aria-label="..." class="pagination-container">
                                       
                                             <ul class="pagination">
                                            <li class="page-item">
                                                <a <c:if test="${page!=1}">
                                                        href="ShowProductByCategory?page=${page-1}&cateID=${cateID}"
                                                    </c:if> class="page-link" aria-label="Next">
                                                    <span aria-hidden="true">«</span>
                                                </a>
                                            </li>

                                            <c:forEach begin="1" end="${totalPage}" var="i">
                                                <li class="page-item ${i==page?"active":""}"><a class="page-link" href="ShowProductByCategory?page=${i}&cateID=${cateID}">${i}</a></li>
                                                </c:forEach>    

                                            <li class="page-item">
                                                <a <c:if test="${page!=totalPage}">
                                                        href="ShowProductByCategory?page=${page+1}&cateID=${cateID}"
                                                    </c:if> class="page-link" aria-label="Next">
                                                    <span aria-hidden="true">»</span>
                                                </a>
                                            </li>
                                        </ul>
                                       
                                       
                                    </nav>
                                </c:otherwise>
                            </c:choose>
                        </c:if> 
                           

                    <c:if test="${cateID == null && brand == null}">
                         <c:choose>
                                <c:when test="${totalPage <= 1}">

                                </c:when>
                                <c:otherwise>
                                    <nav aria-label="..." class="pagination-container">
                                        <ul class="pagination">
                                            <li class="page-item">
                                                <a <c:if test="${page!=1}">
                                                        href="ShowProductController?page=${page-1}"
                                                    </c:if> class="page-link" aria-label="Next">
                                                    <span aria-hidden="true">«</span>
                                                </a>
                                            </li>

                                            <c:forEach begin="1" end="${totalPage}" var="i">
                                                <li class="page-item ${i==page?"active":""}"><a class="page-link" href="ShowProductController?page=${i}">${i}</a></li>
                                                </c:forEach>    

                                            <li class="page-item">
                                                <a <c:if test="${page!=totalPage}">
                                                        href="ShowProductController?page=${page+1}"
                                                    </c:if> class="page-link" aria-label="Next">
                                                    <span aria-hidden="true">»</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </c:otherwise>
                            </c:choose>
                    </c:if>
                           
                        
                    <c:if test="${cateID != null && brand != null}">
                         <c:choose>
                                <c:when test="${totalPage <= 1}">

                                </c:when>
                                <c:otherwise>
                                    <nav aria-label="..." class="pagination-container">
                                        <ul class="pagination">
                                            <li class="page-item">
                                                <a <c:if test="${page!=1}">
                                                        href="ShowProductByBrandAndCategory?page=${page-1}&cateID=${cateID}&brand=${brand}"
                                                    </c:if> class="page-link" aria-label="Next">
                                                    <span aria-hidden="true">«</span>
                                                </a>
                                            </li>

                                            <c:forEach begin="1" end="${totalPage}" var="i">
                                                <li class="page-item ${i==page?"active":""}"><a class="page-link" href="ShowProductByBrandAndCategory?page=${i}&cateID=${cateID}&brand=${brand}">${i}</a></li>
                                                </c:forEach>    

                                            <li class="page-item">
                                                <a <c:if test="${page!=totalPage}">
                                                        href="ShowProductByBrandAndCategory?page=${page+1}&cateID=${cateID}&brand=${brand}"
                                                    </c:if> class="page-link" aria-label="Next">
                                                    <span aria-hidden="true">»</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </c:otherwise>
                            </c:choose>
                    </c:if>
                    <c:if test="${cateID == null && brand != null}">
                        <c:choose>
                                <c:when test="${totalPage <= 1}">

                                </c:when>
                                <c:otherwise>
                                    <nav aria-label="..." class="pagination-container">
                                        <ul class="pagination">
                                            <li class="page-item">
                                                <a <c:if test="${page!=1}">
                                                        href="ShowProductByBrand?page=${page-1}&brand=${brand}"
                                                    </c:if> class="page-link" aria-label="Next">
                                                    <span aria-hidden="true">«</span>
                                                </a>
                                            </li>

                                            <c:forEach begin="1" end="${totalPage}" var="i">
                                                <li class="page-item ${i==page?"active":""}"><a class="page-link" href="ShowProductByBrand?page=${i}&brand=${brand}">${i}</a></li>
                                                </c:forEach>    

                                            <li class="page-item">
                                                <a <c:if test="${page!=totalPage}">
                                                        href="ShowProductByBrand?page=${page+1}&brand=${brand}"
                                                    </c:if> class="page-link" aria-label="Next">
                                                    <span aria-hidden="true">»</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </c:otherwise>
                            </c:choose>
                    </c:if>



                </div>
            </div>
        </div>

        <jsp:include page="components/footer.jsp" />
        <script src="assets/js/SortProduct.js"></script>

    </body>
</html>
