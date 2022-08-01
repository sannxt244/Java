<%-- 
    Document   : home
    Created on : May 25, 2022, 11:23:00 PM
    Author     : dell
--%>

<%@page import="model.Slider"%>
<%@page import="model.Course"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
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
    <body style="position: relative">

        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!-- Header section -->
        <%@include file="header.jsp" %>
        <c:if test="${sessionScope.account.role.id == 3}">
            <div class="col-md-1 d-flex align-items-center" style="position: fixed; z-index: 5; top: 100px">
                <i class="fa fa-bars w-100 nav-bar-icon" onclick="openSubNav('subNav')" aria-hidden="true"></i>
                <div  id="subNav" class="sub-nav-content">
                    <div class="sub-nav-content-cancel"><i class="fa fa-times sub-nav-content-cancel " onclick="closeSubNav('subNav')" aria-hidden="true"></i></div>
                    <div class="sub-nav-content-item "><i class="fa fa-tachometer sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a  href="dashboard">Dashboard</a></div>
                    <div class="sub-nav-content-item "> <i class="fa fa-newspaper-o sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a href="postlist">Post Management</a></div>
                    <div class="sub-nav-content-item"><i class="fa fa-book sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a  href="listSubjectServlet">Course Management</a></div>
                    <div class="sub-nav-content-item"><i class="fa fa-users sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a class="text-danger" href="#">User Management</a></div>
                    <div class="sub-nav-content-item"><i class="fa fa-question-circle sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a  href="questionlist">Question Management</a></div>
                    <div class="sub-nav-content-item"><i class="fa fa-window-maximize sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a  href="listSliderServlet">Slide List</a></div>
                </div>
            </c:if>
        </div>
        <!-- Header section end -->


        <!-- Hero section -->
        <section class="hero-section set-bg" data-setbg="img/bg.jpg">

            <div class="container">
                <div class="hero-text text-white" style="padding-top: 200px">
                    <!-- Slider -->
                    <div class="w3-content w3-display-container" style="width: 100%; height: 48vh; margin-top: -100px;">

                        <% List<Slider> ls = (List<Slider>) request.getAttribute("sliderList"); %>
                        <% for (int i = (ls.size() - 1); i >= 0; i--) {
                        %>    
                        <a href="<%= ls.get(i).getLink()%>"><img class="mySlides" src="<%= ls.get(i).getImage_link()%>"></a>
                            <%
                                }
                            %>

                        <div class="w3-center w3-container w3-section w3-large w3-text-white w3-display-bottommiddle" style="width:100%">
                            <div class="w3-left w3-hover-text-khaki" onclick="plusDivs(-1)">&#10094;</div>
                            <div class="w3-right w3-hover-text-khaki" onclick="plusDivs(1)">&#10095;</div>
                            <% for (int i = 0; i < ls.size(); i++) {
                            %>
                            <span class="w3-badge demo w3-border w3-transparent w3-hover-white" onclick="currentDiv(<%= (i + 1)%>)"></span>
                            <%
                                }
                            %>
                            <!--<span class="w3-badge demo w3-border w3-transparent w3-hover-white" onclick="currentDiv(2)"></span>
                            <span class="w3-badge demo w3-border w3-transparent w3-hover-white" onclick="currentDiv(3)"></span> -->
                        </div>
                    </div>

                    <script>
                        var slideIndex = 1;
                        showDivs(slideIndex);

                        function plusDivs(n) {
                            showDivs(slideIndex += n);
                        }

                        function currentDiv(n) {
                            showDivs(slideIndex = n);
                        }

                        function showDivs(n) {
                            var i;
                            var x = document.getElementsByClassName("mySlides");
                            var dots = document.getElementsByClassName("demo");
                            if (n > x.length) {
                                slideIndex = 1;
                            }
                            if (n < 1) {
                                slideIndex = x.length;
                            }
                            for (i = 0; i < x.length; i++) {
                                x[i].style.display = "none";
                            }
                            for (i = 0; i < dots.length; i++) {
                                dots[i].className = dots[i].className.replace(" w3-white", "");
                            }
                            x[slideIndex - 1].style.display = "block";
                            dots[slideIndex - 1].className += " w3-white";
                        }
                    </script>
                    <!-- end Slider -->
                    <h2>Học Trực Tuyến - Kiến Thức Lập Trình</h2>
                    <p>Đây là một website học trực tuyến, mang tới những kiến thức về lập trình, những chia sẻ, kinh nghiệp của những người trong ngành, đã và đang làm việc với ngành công nghệ thông tin. Đây là nơi các bạn học tập, tiếp thu kiến thức và trau dồi kỹ năng về lập trình, giúp bạn làm chủ được tư duy và kiến thực lập trình. 5PLearn học để làm, học để ứng dụng !</p>
                </div>

            </div>
        </section>
        <!-- Hero section end -->

        <!-- search section -->
        <section class="search-section" style="margin-top: 50px;">
            <div class="container">
                <div class="search-warp">
                    <div class="section-title text-white">
                        <h2>Search your course</h2>
                    </div>
                    <div class="row">
                        <div class="col-md-10 offset-md-1">
                            <!-- search form -->
                            <form class="row course-search-form" action="home" method="get">
                                <input class="col-9" type="text" name="search" placeholder="Course">
                                <button class="site-btn">Search Course</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- search section end -->


        <!-- Courses section -->
        <section id="course" class="categories-section spad">
            <div class="container">
                <div class="section-title">
                    <h2>Course</h2>
                    <p>Dưới đây là danh sách khóa học để bạn tham khảo. Danh sách các khóa học bao gồm từng bài chứa các chủ đề và kiến thức khác nhau về từng khía cạnh cụ thể của lập trình.</p>
                </div>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3  justify-content-center">



                    <c:if test="${requestScope.searchCourses == null}">

                        <% List<Course> list = (List<Course>) request.getAttribute("courseList");
                            for (int i = 0; i < 6; i++) {
                        %> 


                        <div class="col-lg-4 col-md-6">
                            <div class="categorie-item">
                                <div style="height: 150px">
                                    <div class="ci-thumb set-bg" data-setbg="<%= list.get(i).getImg()%>"></div>
                                </div>
                                <div class="ci-text" style="height: 250px">
                                    <span><%= list.get(i).getName()%></span>
                                    <p><%= list.get(i).getDescription()%></p>
                                </div>
                                <a href="coursedetail?id=<%=list.get(i).getId()%>" class="site-btn">More</a>
                            </div>
                        </div>
                        <%
                            }
                        %>    

                    </c:if>

                    <c:if test="${requestScope.searchCourses != null}">
                        <c:forEach items="${requestScope.searchCourses}" var="c" begin="0" end="5">

                            <div class="col-lg-4 col-md-6">
                                <div class="categorie-item">
                                    <div style="height: 150px">
                                        <img class="ci-thumb set-bg w-100 h-100" style="object-fit: cover" src="${c.img}"/>
                                    </div>
                                    <div class="ci-text" style="height: 250px">
                                        <span>${c.name}</span>
                                        <p>${c.description}</p>

                                    </div>
                                    <a href="coursedetail?id=${c.id}" class="site-btn">More</a>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>



                </div>
                <div class="d-flex justify-content-end">
                    <a href="course" class="site-btn">All Courses</a>
                </div>
            </div>
        </section>
        <!-- Courses section end -->

        <!-- Blogs section -->
        <section class="categories-section spad">
            <div class="container">
                <div class="section-title">
                    <h2>Blog</h2>
                    <p>Tổng hợp các bài viết chia sẻ về kinh nghiệm tự học lập trình online và các kỹ thuật lập trình web.</p>
                </div>
                <div class="row">
                    <!-- categorie -->
                    <c:forEach items="${requestScope.blogList}" var="b" begin="0" end="5">
                        <div class="col-lg-4 col-md-6">
                            <div class="categorie-item">
                                <div style="height: 150px">
                                    <img class="ci-thumb set-bg w-100 h-100" style="object-fit: cover" src="${b.image}"/>
                                </div>
                                <div class="ci-text" style="height: 250px">
                                    <span>${b.title}</span>
                                    <p>${fn:substring(b.content, 0, 60)}...</p>

                                </div>
                                <a href="blog-detail?id=${b.id}" class="site-btn">More</a>
                            </div>
                        </div>
                    </c:forEach>

                </div>

                <div class="d-flex justify-content-end">
                    <a href="blog" class="site-btn">All Blogs</a>
                </div>
            </div>
        </section>
        <!-- Blogs section end -->


        <!-- footer section -->
        <%@include file="footer.jsp" %>
	<!-- footer section end -->

        <!--	 course section 
                <section class="course-section spad">
                        <div class="container">
                                <div class="section-title mb-0">
                                        <h2>Featured Courses</h2>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada lorem maximus mauris scelerisque, at rutrum nulla dictum. Ut ac ligula sapien. Suspendisse cursus faucibus finibus.</p>
                                </div>
                        </div>
                        <div class="course-warp">
                                <ul class="course-filter controls">
                                        <li class="control active" data-filter="all">All</li>
                                        <li class="control" data-filter=".finance">Finance</li>
                                        <li class="control" data-filter=".design">Design</li>
                                        <li class="control" data-filter=".web">Web Development</li>
                                        <li class="control" data-filter=".photo">Photography</li>
                                </ul>                                       
                                <div class="row course-items-area">
                                         course 
                                        <div class="mix col-lg-3 col-md-4 col-sm-6 finance">
                                                <div class="course-item">
                                                        <div class="course-thumb set-bg" data-setbg="img/courses/1.jpg">
                                                                <div class="price">Price: $15</div>
                                                        </div>
                                                        <div class="course-info">
                                                                <div class="course-text">
                                                                        <h5>Art & Crafts</h5>
                                                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                                        <div class="students">120 Students</div>
                                                                </div>
                                                                <div class="course-author">
                                                                        <div class="ca-pic set-bg" data-setbg="img/authors/1.jpg"></div>
                                                                        <p>William Parker, <span>Developer</span></p>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                         course 
                                        <div class="mix col-lg-3 col-md-4 col-sm-6 design">
                                                <div class="course-item">
                                                        <div class="course-thumb set-bg" data-setbg="img/courses/2.jpg">
                                                                <div class="price">Price: $15</div>
                                                        </div>
                                                        <div class="course-info">
                                                                <div class="course-text">
                                                                        <h5>IT Development</h5>
                                                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                                        <div class="students">120 Students</div>
                                                                </div>
                                                                <div class="course-author">
                                                                        <div class="ca-pic set-bg" data-setbg="img/authors/2.jpg"></div>
                                                                        <p>William Parker, <span>Developer</span></p>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                         course 
                                        <div class="mix col-lg-3 col-md-4 col-sm-6 web">
                                                <div class="course-item">
                                                        <div class="course-thumb set-bg" data-setbg="img/courses/3.jpg">
                                                                <div class="price">Price: $15</div>
                                                        </div>
                                                        <div class="course-info">
                                                                <div class="course-text">
                                                                        <h5>Graphic Design</h5>
                                                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                                        <div class="students">120 Students</div>
                                                                </div>
                                                                <div class="course-author">
                                                                        <div class="ca-pic set-bg" data-setbg="img/authors/3.jpg"></div>
                                                                        <p>William Parker, <span>Developer</span></p>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                         course 
                                        <div class="mix col-lg-3 col-md-4 col-sm-6 photo">
                                                <div class="course-item">
                                                        <div class="course-thumb set-bg" data-setbg="img/courses/4.jpg">
                                                                <div class="price">Price: $15</div>
                                                        </div>
                                                        <div class="course-info">
                                                                <div class="course-text">
                                                                        <h5>IT Development</h5>
                                                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                                        <div class="students">120 Students</div>
                                                                </div>
                                                                <div class="course-author">
                                                                        <div class="ca-pic set-bg" data-setbg="img/authors/4.jpg"></div>
                                                                        <p>William Parker, <span>Developer</span></p>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                         course 
                                        <div class="mix col-lg-3 col-md-4 col-sm-6 finance">
                                                <div class="course-item">
                                                        <div class="course-thumb set-bg" data-setbg="img/courses/5.jpg">
                                                                <div class="price">Price: $15</div>
                                                        </div>
                                                        <div class="course-info">
                                                                <div class="course-text">
                                                                        <h5>IT Development</h5>
                                                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                                        <div class="students">120 Students</div>
                                                                </div>
                                                                <div class="course-author">
                                                                        <div class="ca-pic set-bg" data-setbg="img/authors/5.jpg"></div>
                                                                        <p>William Parker, <span>Developer</span></p>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                         course 
                                        <div class="mix col-lg-3 col-md-4 col-sm-6 design">
                                                <div class="course-item">
                                                        <div class="course-thumb set-bg" data-setbg="img/courses/6.jpg">
                                                                <div class="price">Price: $15</div>
                                                        </div>
                                                        <div class="course-info">
                                                                <div class="course-text">
                                                                        <h5>Socia Media</h5>
                                                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                                        <div class="students">120 Students</div>
                                                                </div>
                                                                <div class="course-author">
                                                                        <div class="ca-pic set-bg" data-setbg="img/authors/6.jpg"></div>
                                                                        <p>William Parker, <span>Developer</span></p>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                         course 
                                        <div class="mix col-lg-3 col-md-4 col-sm-6 web">
                                                <div class="course-item">
                                                        <div class="course-thumb set-bg" data-setbg="img/courses/7.jpg">
                                                                <div class="price">Price: $15</div>
                                                        </div>
                                                        <div class="course-info">
                                                                <div class="course-text">
                                                                        <h5>IT Development</h5>
                                                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                                        <div class="students">120 Students</div>
                                                                </div>
                                                                <div class="course-author">
                                                                        <div class="ca-pic set-bg" data-setbg="img/authors/7.jpg"></div>
                                                                        <p>William Parker, <span>Developer</span></p>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                         course 
                                        <div class="mix col-lg-3 col-md-4 col-sm-6 photo">
                                                <div class="course-item">
                                                        <div class="course-thumb set-bg" data-setbg="img/courses/8.jpg">
                                                                <div class="price">Price: $15</div>
                                                        </div>
                                                        <div class="course-info">
                                                                <div class="course-text">
                                                                        <h5>HTML 5</h5>
                                                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                                        <div class="students">120 Students</div>
                                                                </div>
                                                                <div class="course-author">
                                                                        <div class="ca-pic set-bg" data-setbg="img/authors/8.jpg"></div>
                                                                        <p>William Parker, <span>Developer</span></p>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                </div>
                        </div>
                </section>
                 course section end 
        
        
                 signup section 
                <section class="signup-section spad">
                        <div class="signup-bg set-bg" data-setbg="img/signup-bg.jpg"></div>
                        <div class="container-fluid">
                                <div class="row">
                                        <div class="col-lg-6">
                                                <div class="signup-warp">
                                                        <div class="section-title text-white text-left">
                                                                <h2>Sign up to became a teacher</h2>
                                                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada lorem maximus mauris scelerisque, at rutrum nulla dictum. Ut ac ligula sapien. Suspendisse cursus faucibus finibus.</p>
                                                        </div>
                                                         signup form 
                                                        <form class="signup-form">
                                                                <input type="text" placeholder="Your Name">
                                                                <input type="text" placeholder="Your E-mail">
                                                                <input type="text" placeholder="Your Phone">
                                                                <label for="v-upload" class="file-up-btn">Upload Course</label>
                                                                <input type="file" id="v-upload">
                                                                <button class="site-btn">Search Couse</button>
                                                        </form>
                                                </div>
                                        </div>
                                </div>
                        </div>
                </section>
                 signup section end 
        
        
                 banner section 
                <section class="banner-section spad">
                        <div class="container">
                                <div class="section-title mb-0 pb-2">
                                        <h2>Join Our Community Now!</h2>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada lorem maximus mauris scelerisque, at rutrum nulla dictum. Ut ac ligula sapien. Suspendisse cursus faucibus finibus.</p>
                                </div>
                                <div class="text-center pt-5">
                                        <a href="#" class="site-btn">Register Now</a>
                                </div>
                        </div>
                </section>
                 banner section end 
        
        
                 footer section 
                <footer class="footer-section spad pb-0">
                        <div class="footer-top">
                                <div class="footer-warp">
                                        <div class="row">
                                                <div class="widget-item">
                                                        <h4>Contact Info</h4>
                                                        <ul class="contact-list">
                                                                <li>1481 Creekside Lane <br>Avila Beach, CA 931</li>
                                                                <li>+53 345 7953 32453</li>
                                                                <li>yourmail@gmail.com</li>
                                                        </ul>
                                                </div>
                                                <div class="widget-item">
                                                        <h4>Engeneering</h4>
                                                        <ul>
                                                                <li><a href="">Applied Studies</a></li>
                                                                <li><a href="">Computer Engeneering</a></li>
                                                                <li><a href="">Software Engeneering</a></li>
                                                                <li><a href="">Informational Engeneering</a></li>
                                                                <li><a href="">System Engeneering</a></li>
                                                        </ul>
                                                </div>
                                                <div class="widget-item">
                                                        <h4>Graphic Design</h4>
                                                        <ul>
                                                                <li><a href="">Applied Studies</a></li>
                                                                <li><a href="">Computer Engeneering</a></li>
                                                                <li><a href="">Software Engeneering</a></li>
                                                                <li><a href="">Informational Engeneering</a></li>
                                                                <li><a href="">System Engeneering</a></li>
                                                        </ul>
                                                </div>
                                                <div class="widget-item">
                                                        <h4>Development</h4>
                                                        <ul>
                                                                <li><a href="">Applied Studies</a></li>
                                                                <li><a href="">Computer Engeneering</a></li>
                                                                <li><a href="">Software Engeneering</a></li>
                                                                <li><a href="">Informational Engeneering</a></li>
                                                                <li><a href="">System Engeneering</a></li>
                                                        </ul>
                                                </div>
                                                <div class="widget-item">
                                                        <h4>Newsletter</h4>
                                                        <form class="footer-newslatter">
                                                                <input type="email" placeholder="E-mail">
                                                                <button class="site-btn">Subscribe</button>
                                                                <p>*We don’t spam</p>
                                                        </form>
                                                </div>
                                        </div>
                                </div>
                        </div>
                        <div class="footer-bottom">
                                <div class="footer-warp">
                                        <ul class="footer-menu">
                                                <li><a href="#">Terms & Conditions</a></li>
                                                <li><a href="#">Register</a></li>
                                                <li><a href="#">Privacy</a></li>
                                        </ul>
                                        <div class="copyright"> Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. 
        Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
         Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. </div>
                                </div>
                        </div>
                </footer> 
                 footer section end -->
        <!--	 course section 
                <section class="course-section spad">
                        <div class="container">
                                <div class="section-title mb-0">
                                        <h2>Featured Courses</h2>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada lorem maximus mauris scelerisque, at rutrum nulla dictum. Ut ac ligula sapien. Suspendisse cursus faucibus finibus.</p>
                                </div>
                        </div>
                        <div class="course-warp">
                                <ul class="course-filter controls">
                                        <li class="control active" data-filter="all">All</li>
                                        <li class="control" data-filter=".finance">Finance</li>
                                        <li class="control" data-filter=".design">Design</li>
                                        <li class="control" data-filter=".web">Web Development</li>
                                        <li class="control" data-filter=".photo">Photography</li>
                                </ul>                                       
                                <div class="row course-items-area">
                                         course 
                                        <div class="mix col-lg-3 col-md-4 col-sm-6 finance">
                                                <div class="course-item">
                                                        <div class="course-thumb set-bg" data-setbg="img/courses/1.jpg">
                                                                <div class="price">Price: $15</div>
                                                        </div>
                                                        <div class="course-info">
                                                                <div class="course-text">
                                                                        <h5>Art & Crafts</h5>
                                                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                                        <div class="students">120 Students</div>
                                                                </div>
                                                                <div class="course-author">
                                                                        <div class="ca-pic set-bg" data-setbg="img/authors/1.jpg"></div>
                                                                        <p>William Parker, <span>Developer</span></p>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                         course 
                                        <div class="mix col-lg-3 col-md-4 col-sm-6 design">
                                                <div class="course-item">
                                                        <div class="course-thumb set-bg" data-setbg="img/courses/2.jpg">
                                                                <div class="price">Price: $15</div>
                                                        </div>
                                                        <div class="course-info">
                                                                <div class="course-text">
                                                                        <h5>IT Development</h5>
                                                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                                        <div class="students">120 Students</div>
                                                                </div>
                                                                <div class="course-author">
                                                                        <div class="ca-pic set-bg" data-setbg="img/authors/2.jpg"></div>
                                                                        <p>William Parker, <span>Developer</span></p>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                         course 
                                        <div class="mix col-lg-3 col-md-4 col-sm-6 web">
                                                <div class="course-item">
                                                        <div class="course-thumb set-bg" data-setbg="img/courses/3.jpg">
                                                                <div class="price">Price: $15</div>
                                                        </div>
                                                        <div class="course-info">
                                                                <div class="course-text">
                                                                        <h5>Graphic Design</h5>
                                                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                                        <div class="students">120 Students</div>
                                                                </div>
                                                                <div class="course-author">
                                                                        <div class="ca-pic set-bg" data-setbg="img/authors/3.jpg"></div>
                                                                        <p>William Parker, <span>Developer</span></p>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                         course 
                                        <div class="mix col-lg-3 col-md-4 col-sm-6 photo">
                                                <div class="course-item">
                                                        <div class="course-thumb set-bg" data-setbg="img/courses/4.jpg">
                                                                <div class="price">Price: $15</div>
                                                        </div>
                                                        <div class="course-info">
                                                                <div class="course-text">
                                                                        <h5>IT Development</h5>
                                                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                                        <div class="students">120 Students</div>
                                                                </div>
                                                                <div class="course-author">
                                                                        <div class="ca-pic set-bg" data-setbg="img/authors/4.jpg"></div>
                                                                        <p>William Parker, <span>Developer</span></p>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                         course 
                                        <div class="mix col-lg-3 col-md-4 col-sm-6 finance">
                                                <div class="course-item">
                                                        <div class="course-thumb set-bg" data-setbg="img/courses/5.jpg">
                                                                <div class="price">Price: $15</div>
                                                        </div>
                                                        <div class="course-info">
                                                                <div class="course-text">
                                                                        <h5>IT Development</h5>
                                                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                                        <div class="students">120 Students</div>
                                                                </div>
                                                                <div class="course-author">
                                                                        <div class="ca-pic set-bg" data-setbg="img/authors/5.jpg"></div>
                                                                        <p>William Parker, <span>Developer</span></p>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                         course 
                                        <div class="mix col-lg-3 col-md-4 col-sm-6 design">
                                                <div class="course-item">
                                                        <div class="course-thumb set-bg" data-setbg="img/courses/6.jpg">
                                                                <div class="price">Price: $15</div>
                                                        </div>
                                                        <div class="course-info">
                                                                <div class="course-text">
                                                                        <h5>Socia Media</h5>
                                                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                                        <div class="students">120 Students</div>
                                                                </div>
                                                                <div class="course-author">
                                                                        <div class="ca-pic set-bg" data-setbg="img/authors/6.jpg"></div>
                                                                        <p>William Parker, <span>Developer</span></p>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                         course 
                                        <div class="mix col-lg-3 col-md-4 col-sm-6 web">
                                                <div class="course-item">
                                                        <div class="course-thumb set-bg" data-setbg="img/courses/7.jpg">
                                                                <div class="price">Price: $15</div>
                                                        </div>
                                                        <div class="course-info">
                                                                <div class="course-text">
                                                                        <h5>IT Development</h5>
                                                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                                        <div class="students">120 Students</div>
                                                                </div>
                                                                <div class="course-author">
                                                                        <div class="ca-pic set-bg" data-setbg="img/authors/7.jpg"></div>
                                                                        <p>William Parker, <span>Developer</span></p>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                         course 
                                        <div class="mix col-lg-3 col-md-4 col-sm-6 photo">
                                                <div class="course-item">
                                                        <div class="course-thumb set-bg" data-setbg="img/courses/8.jpg">
                                                                <div class="price">Price: $15</div>
                                                        </div>
                                                        <div class="course-info">
                                                                <div class="course-text">
                                                                        <h5>HTML 5</h5>
                                                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                                        <div class="students">120 Students</div>
                                                                </div>
                                                                <div class="course-author">
                                                                        <div class="ca-pic set-bg" data-setbg="img/authors/8.jpg"></div>
                                                                        <p>William Parker, <span>Developer</span></p>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                </div>
                        </div>
                </section>
                 course section end 
        
        
                 signup section 
                <section class="signup-section spad">
                        <div class="signup-bg set-bg" data-setbg="img/signup-bg.jpg"></div>
                        <div class="container-fluid">
                                <div class="row">
                                        <div class="col-lg-6">
                                                <div class="signup-warp">
                                                        <div class="section-title text-white text-left">
                                                                <h2>Sign up to became a teacher</h2>
                                                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada lorem maximus mauris scelerisque, at rutrum nulla dictum. Ut ac ligula sapien. Suspendisse cursus faucibus finibus.</p>
                                                        </div>
                                                         signup form 
                                                        <form class="signup-form">
                                                                <input type="text" placeholder="Your Name">
                                                                <input type="text" placeholder="Your E-mail">
                                                                <input type="text" placeholder="Your Phone">
                                                                <label for="v-upload" class="file-up-btn">Upload Course</label>
                                                                <input type="file" id="v-upload">
                                                                <button class="site-btn">Search Couse</button>
                                                        </form>
                                                </div>
                                        </div>
                                </div>
                        </div>
                </section>
                 signup section end 
        
        
                 banner section 
                <section class="banner-section spad">
                        <div class="container">
                                <div class="section-title mb-0 pb-2">
                                        <h2>Join Our Community Now!</h2>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada lorem maximus mauris scelerisque, at rutrum nulla dictum. Ut ac ligula sapien. Suspendisse cursus faucibus finibus.</p>
                                </div>
                                <div class="text-center pt-5">
                                        <a href="#" class="site-btn">Register Now</a>
                                </div>
                        </div>
                </section>
                 banner section end 
        
        
                 footer section 
                <footer class="footer-section spad pb-0">
                        <div class="footer-top">
                                <div class="footer-warp">
                                        <div class="row">
                                                <div class="widget-item">
                                                        <h4>Contact Info</h4>
                                                        <ul class="contact-list">
                                                                <li>1481 Creekside Lane <br>Avila Beach, CA 931</li>
                                                                <li>+53 345 7953 32453</li>
                                                                <li>yourmail@gmail.com</li>
                                                        </ul>
                                                </div>
                                                <div class="widget-item">
                                                        <h4>Engeneering</h4>
                                                        <ul>
                                                                <li><a href="">Applied Studies</a></li>
                                                                <li><a href="">Computer Engeneering</a></li>
                                                                <li><a href="">Software Engeneering</a></li>
                                                                <li><a href="">Informational Engeneering</a></li>
                                                                <li><a href="">System Engeneering</a></li>
                                                        </ul>
                                                </div>
                                                <div class="widget-item">
                                                        <h4>Graphic Design</h4>
                                                        <ul>
                                                                <li><a href="">Applied Studies</a></li>
                                                                <li><a href="">Computer Engeneering</a></li>
                                                                <li><a href="">Software Engeneering</a></li>
                                                                <li><a href="">Informational Engeneering</a></li>
                                                                <li><a href="">System Engeneering</a></li>
                                                        </ul>
                                                </div>
                                                <div class="widget-item">
                                                        <h4>Development</h4>
                                                        <ul>
                                                                <li><a href="">Applied Studies</a></li>
                                                                <li><a href="">Computer Engeneering</a></li>
                                                                <li><a href="">Software Engeneering</a></li>
                                                                <li><a href="">Informational Engeneering</a></li>
                                                                <li><a href="">System Engeneering</a></li>
                                                        </ul>
                                                </div>
                                                <div class="widget-item">
                                                        <h4>Newsletter</h4>
                                                        <form class="footer-newslatter">
                                                                <input type="email" placeholder="E-mail">
                                                                <button class="site-btn">Subscribe</button>
                                                                <p>*We don’t spam</p>
                                                        </form>
                                                </div>
                                        </div>
                                </div>
                        </div>
                        <div class="footer-bottom">
                                <div class="footer-warp">
                                        <ul class="footer-menu">
                                                <li><a href="#">Terms & Conditions</a></li>
                                                <li><a href="#">Register</a></li>
                                                <li><a href="#">Privacy</a></li>
                                        </ul>
                                        <div class="copyright"> Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. 
        Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
         Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. </div>
                                </div>
                        </div>
                </footer> 
                 footer section end -->


        <!--====== Javascripts & Jquery ======-->
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/circle-progress.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
</html>
