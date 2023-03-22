<%-- 
    Document   : create-product
    Created on : Feb 11, 2023, 1:05:20 PM
    Author     : thaiq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    </head>
    <body>
        <form class="form-horizontal" action="CreateProductController" enctype="multipart/form-data"  method="POST">
            <fieldset>

                <!-- Form Name -->
                <legend style="padding: 30px 20px; font-size: 24px; font-weight: 800; color: darkcyan;">PRODUCT INSERT</legend>



                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="product_name"> NAME</label>  
                    <div class="col-md-4">
                        <input id="product_name" name="newproductname" class="form-control input-md" type="text">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="available_quantity">QUANTITY</label>  
                    <div class="col-md-4">
                        <input id="available_quantity" name="newquantity"  class="form-control input-md" required="" type="number">

                    </div>
                </div>
                
                <div class="form-group"> 
                    <div class="col-md-4">
                        <input type="hidden" name="newstatus" value="NEW">                       
                    </div>
                </div>

                <!-- Textarea -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="product_description">PRODUCT DESCRIPTION</label>
                    <div class="col-md-4">                     
                        <textarea class="form-control" id="product_description" name="newdescription"></textarea>
                    </div>
                </div>


                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="product_weight">CAPACITY</label>  
                    <div class="col-md-4">
                        <input id="product_weight" name="newcapacity" class="form-control input-md" required="" type="text">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="percentage_discount">BRAND</label>  
                    <div class="col-md-4">
                        <select name="newbrand" id="newbrand">
                            <option value="Gucci">Gucci</option>
                            <option value="Dior">Dior</option>
                            <option value="YSL">YSL</option>
                            <option value="Hermes">Hermes</option>
                            <option value="Burberry">Burberry</option>
                        </select>
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="percentage_discount">PRICE</label>  
                    <div class="col-md-4">
                        <input id="percentage_discount" name="newprice" class="form-control input-md" required="" type="number">

                    </div>
                </div>

                <!-- Select Basic -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="product_categorie"> CATEGORY</label>
                    <div class="col-md-4">
                        <input type="radio" name="newcategoryid" value="1" checked="checked">MALE 
                        <input type="radio" name="newcategoryid" value="2">FEMALE
                    </div>
                </div>


                <!-- File Button --> 
                <div class="col-md-12"> Image
                    Choose a file: <input type="file" name="newimage" class="form-control" placeholder="image" value="">
                </div>


                <!-- Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="singlebutton"></label>
                    <div class="col-md-4">
                        <input type="submit" name="action" value="Create"/>
                    </div>
                </div>

            </fieldset>
        </form>
</html>
