<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
        <section class="single-course spad pb-0 mb-5">
            <div class="container">
                <c:if test = "${!isDone}">
                    <div class="course-meta-area mb-5">
                        <div class="row">
                            <div class="col-lg-10 offset-lg-1">
                                <h3>${quizFormat.name}</h3>
                                <div class="course-metas">
                                    <div class="course-meta">
                                        <div class="cm-info">
                                            <h6>Duration</h6>
                                            <p>${quizFormat.duration} s</p>
                                        </div>
                                    </div>
                                    <div class="course-meta">
                                        <div class="cm-info">
                                            <h6>Number</h6>
                                            <p>${quizFormat.number}</p>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-10 offset-lg-1 course-list">
                                <div class="cl-item">
                                    <h4>Description</h4>
                                    <p>${quizFormat.description}</p>
                                </div>
                                <a href="quizhandle?username=${username}&topicId=${quizFormat.subjectTopicId}"  class="btn btn-primary btn-lg float-end px-5">Start</a>
                            </div>
                        </div>                                
                    </div>
                </c:if>
                <c:if test = "${isDone}">
                    <div class="row">
                        <div class="col-lg-10 offset-lg-1 course-list">
                            <div class="d-flex flex-row mb-5">
                                <div style="min-width: 220px; min-height: 220px;" class="me-5 text-center border d-flex flex-column justify-content-center">
                                    <div class="fs-4">SCORE</div>
                                    <div class="fs-3 ${quizResult.rightAnswers/quizResult.totalAnswers*100 > 50?"text-success":"text-danger"}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${quizResult.rightAnswers/quizResult.totalAnswers*100}" />%
                                    </div>
                                    <div class="fs-5">Correct ${quizResult.rightAnswers} of ${quizResult.totalAnswers}</div>
                                    ${quizResult.rightAnswers/quizResult.totalAnswers*100 >= 50?"<div class='fs-4 text-success'>PASS THE EXAM</div>":"<div class='fs-4 text-danger'>FAILED THE EXAM</div>"}
                                </div>
                                <table style="min-height: 220px" class="table mb-0">
                                    <tr>
                                        <td>Wrong-answered questions</td>
                                        <td class="text-danger">${quizResult.totalAnswers - quizResult.rightAnswers}</td>
                                    </tr>
                                </table>
                            </div>
                            <a href="quizreview?topicId=${quiz.subjectTopicId}&quizId=${quiz.id}" class="btn btn-primary btn-lg float-end px-5">Review Test</a>
                            <a href="quizhandle?username=${username}&topicId=${quiz.subjectTopicId}" class="btn btn-primary btn-lg float-end px-5 me-2">Redo Test</a>
                        </div>
                    </div>
                </c:if>

            </div>
        </section>
        
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