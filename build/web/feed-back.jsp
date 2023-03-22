<%-- 
    Document   : feed-back
    Created on : Mar 8, 2023, 7:27:57 AM
    Author     : long
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">


        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER != null}">
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">


                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                <h3>FEED BACK FROM</h3>
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple" border="1">
                                    <thead>
                                        <tr>
                                            <th>image</th>
                                            <th>Product name</th>
                                            <th>brand</th>
                                            <th>capacity</th>
                                            <th>price</th>



                                        </tr>
                                    </thead>

                                    <tbody>
                                    <form action="FeedBackController" enctype="multipart/form-data" method="POST">
                                        <tr>



                                        <input type="hidden"  name="fullName" value="${sessionScope.LOGIN_USER.fullName}" readonly=""/>

                                        <input type="hidden"  name="status" value="false" readonly=""/>

                                        <input type="hidden"  name="productID" value="${sessionScope.accept.productID}" readonly=""/>

                                        <input type="hidden"  name="userID" value="${sessionScope.LOGIN_USER.userID}" readonly=""/>

                                        <input type="hidden"  name="product_name" value="${sessionScope.accept.name}" readonly=""/>
                                        <input type="hidden"  name="email" value="${sessionScope.LOGIN_USER.email}" readonly=""/>
                                        <input type="hidden"  name="phoneNumber" value="${sessionScope.LOGIN_USER.phoneNumber}" readonly=""/>

                                        <!--                                       làm mấy cái dưới này đẹp thôi, ở trên truyền HIDDEN kh cần css -->

                                        <td>
                                            <img style="width: 40px; height: 30px;" src="${sessionScope.accept.image}" /> 
                                            <input type="hidden"  name="image" value="${sessionScope.accept.image}" readonly=""/>

                                        </td> 
                                        <td>${sessionScope.accept.name}</td>
                                        <td>${sessionScope.accept.brand}</td>
                                        <td>${sessionScope.accept.capacity}ml</td>
                                        <td>${sessionScope.accept.price}</td>


                                        <!--                                       feed back content-->
                                        <textarea class="input-field" placeholder="Enter your feed back" name="feedBack" rows="3" required="re"></textarea>

                                        <!--                                       image cua khach hang gui len-->
                                        <div class="col-md-12"> Image
                                            Choose a image: <input type="file" name="pImage" class="form-control" placeholder="image" value="">
                                        </div> 

                                        <!--                                        Rate star-->
                                        <label for="rated_star">Select rating:</label>
                                        <select name="rated_star" id="rate_start">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                        </select>
                                        <br><br>


                                        <input type="submit" value="Submit Feedback">

                                        </tr>

                                    </form>




                                    </tbody>
                                </table>

                            </div>

                        </div>
                    </div>




                </main>

            </div> 
        </c:if>

    </body>

</html>
