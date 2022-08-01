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
        <%@include file="../header.jsp" %>
            <!-- Header section end -->
            <div class="container-fluid body">
                <div class="row">
                      <div class="col-md-1 d-flex align-items-center">
                        <i class="fa fa-bars w-100 nav-bar-icon" onclick="openSubNav('subNav')" aria-hidden="true"></i>
                         <div  id="subNav" class="sub-nav-content">
                             <div class="sub-nav-content-cancel"><i class="fa fa-times sub-nav-content-cancel " onclick="closeSubNav('subNav')" aria-hidden="true"></i></div>
                             <c:if test="${sessionScope.account.role.id == 3}">  <div class="sub-nav-content-item "><i class="fa fa-tachometer sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a  href="dashboard">Dashboard</a></div></c:if>
                            <div class="sub-nav-content-item "> <i class="fa fa-newspaper-o sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a class="text-danger" href="#">Post Management</a></div>
                            <div class="sub-nav-content-item"><i class="fa fa-book sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a href="#">Course Management</a></div>
                             <c:if test="${sessionScope.account.role.id == 3}"> <div class="sub-nav-content-item"><i class="fa fa-users sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a href="userlist">User Management</a></div></c:if>
                            <div class="sub-nav-content-item"><i class="fa fa-question-circle sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a href="questionlist">Question Management</a></div>
                        </div>
                        
                    </div>
                    <div class="col-md-11 d-flex justify-content-center">
                        <form id="searchFormDashboard" action="postlist" method="POST">
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
                        <div class="col-md-2">

                            <h3 class="d-flex justify-content-center">Category</h3>
                            <ul class="list-group">
                                <li class="list-group-item"> <a href="postlist?categoryId=0">All</a></li>                                                                        
                                    <c:forEach items="${requestScope.categories}" var="c">
                                    <li class="list-group-item"><a href="postlist?categoryId=${c.id}">${c.categoryName}</a></li>
                                    </c:forEach>
                            </ul>

                        </div>
                        <div class="col-md-10 ">
                            <h3>Blogs</h3>

                            <table class="table">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Title</th>                     
                                        <th scope="col">Category</th>
                                        <th scope="col">Create Date</th>
                                        <th scope="col">Author</th>
                                        <th scope="col"></th>
                                        <th scope="col"></th>
                                        <th scope="col"></th>
                                        <th scope="col"></th>
                                        <th scope="col" class="bg-info"><a href="addblog" target="blank">Add +</a></td>


                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.blogs}" var="i">
                                        <tr>
                                            <th scope="row">${i.id}</th>
                                            <td>${i.title}</td>

                                            <c:forEach items="${requestScope.categories}" var="c"> 
                                                <c:if test="${c.id == i.categoryId}">
                                                    <td>${c.categoryName}</td>
                                                </c:if>
                                            </c:forEach>
                                            <td>${i.createdDate}</td>
                                            <td>${i.createdBy}</td>

                                            <td><a href="blogdetail?id=${i.id}">View</a></td>
                                            <td><a href="postdetail?blogId=${i.id}&page=${page}&categoryId=${categoryId}&search=${search}" target="_blank">Detail</a></td>
                                            <td></td>
                                            <td>
                                                <form id="hideAndShow${i.id}" action="postdetail" method="POST">
                                                    <input type="hidden" name="fromPostlist" value="1"/>
                                                    <input type="hidden" name="blogId" value="${i.id}"/>
                                                    <input type="hidden" name="page" value="${page}"/>
                                                    <input type="hidden" name="search" value="${search}"/>
                                                    <input type="hidden" name="categoryId" value="${categoryId}"/>
                                                    <select name="isActive" onchange="submitForm('hideAndShow${i.id}')">                                                           
                                                        <option value="1" <c:if test="${i.isActived == true}">selected="selected"</c:if>>Show</option>
                                                        <option value="0" <c:if test="${i.isActived == false}">selected="selected"</c:if>>Hide</option>
                                                        </select>
                                                    </form>
                                                </td>
                                                <th scope="col"></th>
                                            </tr>
                                    </c:forEach>                                      
                                </tbody>
                            </table>

                            <div class="d-flex justify-content-center mb-5">
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination">
                                        <c:if test="${page>1}">
                                            <li class="page-item"><a class="page-link" href="postlist?page=${page - 1}&categoryId=${categoryId}&search=${search}">Previous</a></li>
                                            </c:if>
                                            <c:if test="${totalPage<10}">
                                                <c:forEach begin="${start}" end="${totalPage}" var="p">
                                                <li class="page-item ${(page eq p)? " active":""} " ><a class="page-link" href="postlist?page=${p}&categoryId=${categoryId}&search=${search}">${p}</a></li>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${totalPage>=10 && page < 10}">
                                                <c:forEach begin="${1}" end="${10}" var="p">
                                                <li class="page-item ${(page eq p)? " active":""} " ><a class="page-link" href="postlist?page=${p}&categoryId=${categoryId}&search=${search}">${p}</a></li>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${totalPage>=10 && page >= 10}">
                                                <c:forEach begin="${start}" end="${end}" var="p">
                                                <li class="page-item ${(page eq p)? " active":""} " ><a class="page-link" href="postlist?page=${p}&categoryId=${categoryId}&search=${search}">${p}</a></li>
                                                </c:forEach>
                                            </c:if>



                                        <c:if test="${page < totalPage}">
                                            <li class="page-item"><a class="page-link" href="postlist?page=${page + 1}&categoryId=${categoryId}&search=${search}">Next</a></li>
                                            </c:if>

                                    </ul>
                                </nav>
                            </div>
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
