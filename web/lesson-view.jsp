<%-- 
    Document   : lesson-view
    Created on : Jun 9, 2022, 8:53:09 AM
    Author     : sannx
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>WebUni - Education Template</title>
        <meta charset="UTF-8">
        <meta name="description" content="WebUni Education Template">
        <meta name="keywords" content="webuni, education, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Favicon -->   
        <link href="img/favicon.ico" rel="shortcut icon"/>

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,400i,500,500i,600,600i,700,700i,800,800i" rel="stylesheet">

        <!-- Stylesheets -->
        <link rel="stylesheet" href="css/font-awesome.min.css"/>
        <link rel="stylesheet" href="css/owl.carousel.css"/>
        <link rel="stylesheet" href="css/style.css"/>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!-- Header section -->
        <%@include file="header.jsp" %>
        <!-- Header section end -->

        <!-- single course section -->
        <section class="single-course spad pb-0">
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 offset-lg-1 course-list">
                        <div class="cl-item">
                            <h1 class="mb-5 text-black-50">${lesson.name}</h1>
                            ${lesson.htmlContent}
                            <c:if test="${lesson.videoLink.length() > 0}">
                                <video controls>
                                    <source src="${lesson.videoLink}">
                                </video>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- single course section end -->
        <div class="position-fixed fixed-top" style="margin-top: 100px;">
            <button class="btn btn-dark translate-middle" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasWithBothOptions" aria-controls="offcanvasWithBothOptions">></button>
        </div>
        <div class="offcanvas offcanvas-start" data-bs-scroll="true" tabindex="-1" id="offcanvasWithBothOptions" aria-labelledby="offcanvasWithBothOptionsLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasWithBothOptionsLabel"></h5>
                <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <c:forEach var="topic" items="${topicList}">
                    <p>
                        <a style="width:100%;text-align: left;" class="btn btn-dark" data-bs-toggle="collapse" href="#multiCollapseExample${topic.id}" role="button" aria-expanded="false" aria-controls="multiCollapseExample1">${topic.name}</a>
                    </p>
                    <div class="row">
                        <div class="col">
                            <div class="collapse multi-collapse mb-3" id="multiCollapseExample${topic.id}">
                                <ul class="list-group list-group-numbered">
                                    <c:forEach var="lesson" items="${topic.lessonList}">
                                        <li class="list-group-item"><a class="text-decoration-none text-dark" href="lesson?course=${courseId}&lesson=${lesson.id}">${lesson.name}</a></li>
                                        </c:forEach>
                                        <c:if test="${topic.quizFormat != null}">
                                        <li class="list-group-item"><a class="text-decoration-none text-dark" href="question?course=${courseId}&topic=${topic.id}">${topic.quizFormat.name}</a></li>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- footer section -->
        <%@include file="footer.jsp" %>
	<!-- footer section end -->


        <!--====== Javascripts & Jquery ======-->
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/circle-progress.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>