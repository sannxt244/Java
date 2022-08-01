<%-- 
    Document   : lessondetail
    Created on : Jul 19, 2022, 10:15:27 PM
    Author     : HDC
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
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!-- Header section -->
        <%@include file="header.jsp" %>
        <!-- Header section end -->

        <div class="main-panel mt-4">
            <div class="content">
                <div class="container-fluid  mb-2">
                    <div class="row d-flex justify-content-center">                       
                        <div class="col-md-6 ">
                            <form action="updateLessonServlet" method="POST" enctype="multipart/form-data"> 
                                
                               <c:forEach items="${requestScope.listlessonbyid}" var="lessonbyid">
                                <div class="mb-3 row">
                                    <label for="name" class="col-sm-2 col-form-label">Name</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="name" value="${lessonbyid.name}" required="required" class="form-control" id="name"/>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label for="content" class="col-sm-2 col-form-label">Content</label>
                                    <div class="col-sm-10">
                                        <textarea rows="50" cols="100">
                                        <input type="text" name="content" value="${lessonbyid.htmlContent}" required="required" class="form-control" id="content"/>
                                        </textarea>
                                    </div>
                                </div>
                                    
                                <div class="mb-3 row">
                                    <label for="video" class="col-sm-2 col-form-label">Video</label>
                                    <div class="col-sm-10">
                                        <input type="file" name="video" required="required" class="form-control" id="video"/>
                                    </div>
                                    
                                    <div class="col-sm-10">
                                        <video width="320" height="240" controls>
                                        <source src="${lessonbyid.videoLink}" type="video/mp4">
                                        Your browser does not support the video tag.
                                        </video>
                                    </div>
                                </div>   
                                    
                                <div class="mb-3 row">
                                    <label for="order" class="col-sm-2 col-form-label">Order</label>
                                    <div class="col-sm-10">
                                        <input type="number" name="order" value="${lessonbyid.order}" required="required" class="form-control" id="order"/>
                                       
                                    </div>
                                </div> 
                                <p style="color: red">${requestScope.mess}</p>         
                                <div class="mb-3 row">
                                    <label class="col-sm-2 col-form-label">Type</label>
                                    <div class="col-sm-10">
                                        ${lessonbyid.type}
                                       
                                    </div>
                                </div> 
                                <input type="hidden" name="id" value="${lessonbyid.id}">
                                
                                <div class="d-flex justify-content-end">
                                    <button type="submit" class="btn btn-primary">Update</button>
                                </div>
                               </c:forEach>
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
