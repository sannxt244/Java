<%-- 
    Document   : postlist
    Created on : Jun 11, 2022, 11:38:30 PM
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
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!-- Header section -->
        <div class="header bg-dark py-2">
            <div class="container">
                <div class="row">
                    <div class="col-lg-2 col-md-2">
                        <div class="site-logo">
                            <img src="img/logo.png" alt="">
                        </div>
                        <div class="nav-switch">
                            <i class="fa fa-bars"></i>
                        </div>
                    </div>
                    <div class="col-lg-10 col-md-10">
                        <c:if test="${sessionScope.account == null}">
                            <a href="register" class="col-1 site-btn header-btn">Sign Up</a>
                            <a href="login" class="col-1 site-btn header-btn"  style="margin-right: 20px">Login</a>
                        </c:if>

                        <c:if test="${sessionScope.account != null}">
                            <a href="logout" id="logout" style="margin-left: 20px;" class="site-btn header-btn" >Log Out</a>
                            <a href="profile?userName=${sessionScope.account.userName}" class="col-1 site-btn header-btn">User Profile</a>

                            <script type="text/javascript">
//                                function logOut() {
//
//                                    document.getElementById('logout').href = "logout";
//                                }
//                            </script>
                            </c:if>
                        <nav class="col-12 main-menu">
                            <ul>
                                <li><a href="home">Home</a></li>
                                <li><a href="course">Courses</a></li>
                                <li><a href="blog">Blog</a></li>
                                <li><a href="listSubjectServlet">Subject List</a></li>
                                <li><a href="AdminQuizListServlet">Quiz List</a></li>
                            </ul>
                        </nav>



                    </div>
                </div>
            </div>
        </div>

        <!-- Search -->
        <div class="container-fluid body">
            <div class="row">
                <div class="col-md-12 d-flex justify-content-center">
                    <form id="searchFormDashboard" action="listSubjectServlet" method="GET">
                        <input value="${search}" type="text" name="search" placeholder="Enter blog's title that you want to find..."/>
                        <input class="dashboard-input btn-secondary " type="submit" value="Search"><br>
                    </form>


                </div>
            </div>
        </div>

        <div class="main-panel">
            <div class="content">
                <div class="container-fluid  mb-2">
                    <div class="row">

                        <div class="col-md-12 ">
                            
                            
                            <div class="col">
                                <a href="listSubjectServlet"  class="text-center btn btn-primary">Subject List</a>
                            </div>
                            <br>
                            <table class="table">
                                <thead class="thead-dark">
                                    <tr>

                                        <th scope="col" class="text-center">Package ID</th>
                                        <th scope="col" class="text-center">Package Name</th>
                                        <th scope="col" class="text-center">Duration</th>
                                        <th scope="col" class="text-center">Update</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${pricePackageList}" var="o">                                       
                                        <tr>
                                            <td class="text-center">${o.id}</td>
                                            <td class="text-center"><a href="PackageDetail?pid=${o.id}">${o.package_name}</a></td>
                                            <td class="text-center">${o.duration}</td>
                                            <td>

                                                <div class="col text-center">
                                                    <a href="PackageDetail?pid=${o.id}"  class="text-center btn btn-primary">Update</a>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                            </form>


                        </div>
                    </div>


                </div>
            </div>
        </div>


        <script>
            function submitForm(formId) {
                document.getElementById(formId).submit();
            }
        </script>

        <!--====== Javascripts & Jquery ======-->
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/circle-progress.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>
