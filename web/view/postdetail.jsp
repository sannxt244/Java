<%-- 
    Document   : postdetail
    Created on : Jun 12, 2022, 1:42:12 PM
    Author     : dell
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="description" content="WebUni Education Template">
        <meta name="keywords" content="webuni, education, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Favicon -->   
        <link href="img/favicon.ico" rel="shortcut icon"/>

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,400i,500,500i,600,600i,700,700i,800,800i" rel="stylesheet">

        <!-- Stylesheets -->
        <link href="css/all.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/font-awesome.min.css"/>
        <link rel="stylesheet" href="css/owl.carousel.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
        <!-- Slider -->
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"/>

        <style>
            .mySlides {display:none}
            .w3-left, .w3-right, .w3-badge {cursor:pointer}
            .w3-badge {height:13px;width:13px;padding:0}
            .w3-content img {
                width: 100%;
                height: 100%;
                border-radius: 1%;
            }
        </style>
        <!-- end Slider -->

    </head>
    <body>
        <div class="main h-100 " >
            <!-- Page Preloder -->
            <div id="preloder">
                <div class="loader"></div>
            </div>

            <!-- Header section -->
           <%@include file="../header.jsp" %>
                <!-- Header section end -->

                <div class="main-content mt-2 h-80">
                    <div class="container-fluid h-100">
                        <div class="row">
                            <div class="col-md-12">
                                <h3 class="font-weight-bold">Blog Detail</h3>
                            </div>
                        </div>
                        <div class=" row d-flex justify-content-center align-items-center">                       
                            <div class="col-md-6 card shadow-lg p-3 mb-5 bg-white rounded">

                                <form class="card-body Form-postDetail" action="postdetail" method="POST" enctype="multipart/form-data"> 
                                    <input type="hidden" name="blogId" value="${blog.id}">
                                <input type="hidden" name="blogId" value="${page}">
                                <input type="hidden" name="blogId" value="${categoryId}">
                                <input type="hidden" name="blogId" value="${search}">

                                <div class="mb-3 row ">

                                    <div class="col-sm-12 d-flex justify-content-center">
                                        <img  src="${blog.image}" width="50%" alt="thumbnail"/>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label for="title" class="col-sm-2 col-form-label">Title</label>
                                    <div class="col-sm-10">
                                        <input value = "${blog != null? blog.title:""}" type="text" name="title" required="required" class="form-control" id="title"/>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label for="content" class="col-sm-2 col-form-label">Content</label>
                                    <div class="col-sm-10">
                                        <textarea  class="form-control" rows="5" id="content"  name="content">${blog.content}</textarea>

                                    </div>
                                </div>   
                                <div class="mb-3 row d-flex justify-content-end">
                                    <div class="col-sm-12 col-form-label error">${content_error}</div>

                                </div>
                                <div class="mb-3 row">
                                    <label for="categoryIdAfterUpdate" class="col-sm-2 col-form-label">Category</label>
                                    <div class="col-sm-10">
                                        <select name="categoryIdAfterUpdate" id="categoryIdAfterUpdate" class="form-control">
                                            <c:forEach items="${categories}" var="c">
                                                <option ${c.id == blog.categoryId?"selected":""} value="${c.id}">${c.categoryName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label for="isActive" class="col-sm-2 col-form-label">Status</label>
                                    <div class="col-sm-10">
                                        <select name="isActive" id="isActive" class="form-control">                                           
                                            <option ${blog.isActived == true?"selected":""} value="1">Show</option>
                                            <option ${blog.isActived == false?"selected":""} value="0">Hide</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="mb-3 row d-flex justify-content-end">
                                    <label for="image" class="col-sm-2 col-form-label">Thumbnail</label>
                                    <div class="col-sm-10">

                                        <input type="file" id="image" name="image"  class="form-control" id="inputPassword"/>

                                    </div>
                                </div>
                                <div class="mb-3 row d-flex justify-content-end">
                                    <div class="col-sm-12 col-form-label error">${file_Error}</div>

                                </div>
                                <div class="d-flex justify-content-end">
                                    <button type="submit" class="btn btn-primary">Update</button>
                                </div>
                            </form>



                        </div>
                    </div>


                </div>
            </div>



        </div>


        <!--====== Javascripts & Jquery ======-->
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/circle-progress.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>


    </body>
</html>
