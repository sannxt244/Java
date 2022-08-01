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
                            <a class="btn btn-success" href="PricePackageListServlet">Package</a>
                            <table class="table">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Name</th>                     
                                        <th scope="col">Created by</th>
                                        <th scope="col">Description</th>
                                        <th scope="col">Package</th>
                                        <th scope="col">Img</th>
                                        <th scope="col">Status</th>
                                        <th scope="col" class="bg-info"><a href="subjectadd.jsp">Update +</a>
                                        <th scope="col">Lessons</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.listSubject}" var="i">
                                        <tr>
                                            <th scope="row">${i.id}</th>
                                            <td>${i.name}</td>
                                            <td>${i.created_by}</td>
                                            <td>${i.description}</td>
                                            <td>${i.package_name}</td>
                                            <td>
                                                <img height="50px" width="150px" src="${i.img}">
                                            </td>
                                            <td>
                                                <form id="hideAndShow${i.id}" action="deleteSubjectServlet" method="POST"><input type="hidden" name="id" value="${i.id}">
                                                    <select name="isActive">                                                           
                                                        <option value="1" <c:if test="${i.isActive == 1}">selected="seubmitForm('hideAndShow${i.id}')lected"</c:if>>Active</option>
                                                        <option value="0" <c:if test="${i.isActive == 0}">selected="selected"</c:if>>Inactive</option>
                                                        </select>
                                                    </form>       
                                                </td>
                                                <td>
                                                    <a href="subjectdetail?courseid=${i.id}&packageId=${i.packageId}">update</a>
                                            </td>

                                            <td>
                                                <c:if test="${i.categoryId == 1}">
                                                    <label for="formGroupExampleInput2" class="col-form-label-lg"> Back-end Languages</label>
                                                </c:if>

                                                <c:if test="${i.categoryId == 2}">
                                                    <label for="formGroupExampleInput2" class="col-form-label-lg">Font-end Languages</label>
                                                </c:if>

                                                <c:if test="${i.categoryId == 3}">
                                                    <label for="formGroupExampleInput2" class="col-form-label-lg">Others</label>
                                                </c:if>  
                                            </td>
                                    <script>
                                        function Mess(id) {
                                            var option = confirm('Are you sure to delete');
                                            if (option === true) {
                                                window.location.href = 'deleteSubjectServlet?sid=' + id;
                                            }
                                        }
                                    </script>

                                    <th scope="col"></th>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                            </form>

                            <div class="d-flex justify-content-center">
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
                                            <li class="page-item"><a class="page-link" href="postlist?page=${page + 1}&search=${search}">Next</a></li>
                                            </c:if>

                                    </ul>
                                </nav>
                            </div>
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
