<%-- 
    Document   : dashboard
    Created on : Jun 8, 2022, 10:53:28 PM
    Author     : dell
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.8.0/chart.min.js" integrity="sha512-sW/w8s4RWTdFFSduOTGtk4isV1+190E/GghVffMA9XczdJ2MDzSzLEubKAs5h0wzgSJOQTRYyaz73L3d6RtJSg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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
                    <div class="col-md-6"></div>
                    <div class="col-md-6 d-flex justify-content-center">
                        <div class="error" style="font-size: smaller"><c:if test="${requestScope.fillterError != null}" >${requestScope.fillterError}</c:if> </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-1 d-flex align-items-center">
                        <i class="fa fa-bars w-100 nav-bar-icon" onclick="openSubNav('subNav')" aria-hidden="true"></i>
                         <div  id="subNav" class="sub-nav-content">
                             <div class="sub-nav-content-cancel"><i class="fa fa-times sub-nav-content-cancel " onclick="closeSubNav('subNav')" aria-hidden="true"></i></div>
                             <div class="sub-nav-content-item "><i class="fa fa-tachometer sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a class="text-danger" href="#">Dashboard</a></div>
                            <div class="sub-nav-content-item "> <i class="fa fa-newspaper-o sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a href="postlist">Post Management</a></div>
                            <div class="sub-nav-content-item"><i class="fa fa-book sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a href="listSubjectServlet">Course Management</a></div>
                            <div class="sub-nav-content-item"><i class="fa fa-users sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a href="userlist">User Management</a></div>
                            <div class="sub-nav-content-item"><i class="fa fa-question-circle sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a  href="questionlist">Question Management</a></div>
                            <div class="sub-nav-content-item"><i class="fa fa-window-maximize sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a  href="listSliderServlet">Slide List</a></div>

                        </div>
                        
                    </div>
                   
                    <div class="col-md-6 d-flex justify-content-center"><h2 class="text-uppercase font-weight-bold">Information from ${requestScope.from} to ${requestScope.to}</h2></div>
                <div class="col-md-5 d-flex justify-content-center">
                    <form id="searchFormDashboard" action="dashboard" method="POST">
                        <label class="form-inner" for="from">Start date</label>
                        <input class="dashboard-input " id="from" type="date" name="from" value="${requestScope.from}">
                        <label class="form-inner" for="to">End date</label>
                        <input class="dashboard-input" id="to" type="date" name="to" value="${requestScope.to}">
                        <input class="dashboard-input btn-secondary" type="submit" value="Filter"><br>
                    </form>


                </div>
            </div>


        </div>
        <div class="main-panel  px-4">
            <div class="content">
                <div class="container-fluid  mb-2">

                    <div class="row mb-2">
                        <div class="col-md-3">
                            <div class="card card-stats " style="background-color:  #91c8ae; border: none;">
                                <div class="card-body ">
                                    <div class="row">
                                        <div class="col-5">
                                            <div class="text-center">

                                                <i class="fa fa-thin fa-user fa-4x"></i>
                                            </div>
                                        </div>
                                        <div class="col-7 d-flex align-items-center">
                                            <div class="numbers first" id="firstInfor-user">
                                                <h5 class="card-category font-weight-bold text-white">New users</h5>
                                                <h4 class="card-title text-white">${requestScope.users.size()}</h4>
                                            </div> 
                                            <!--                                            <div class="numbers second" id="secondInfor">
                                                                                            <h5 class="card-category font-weight-bold text-white">All users</h5>
                                                                                            <h4 class="card-title text-white">${requestScope.allUser.size()}</h4>
                                                                                        </div> -->
                                            <div class="numbers second" id="secondInfor-user">
                                                <h5 class="card-category font-weight-bold text-white">New bought</h5>
                                                <h4 class="card-title text-white">${requestScope.userBought.size()}</h4>
                                            </div> 
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 d-flex justify-content-center align-items-center"><button type="button" class="btn btn-light btn-first-click btn-sm w-100" onclick="openFirst('firstInfor-user', 'secondInfor-user')">New user</button> </div>
                                        <div class="col-md-6 d-flex justify-content-center align-items-center"><button type="button" class="btn btn-light btn-second-click btn-sm w-100" onclick="openFirst('secondInfor-user', 'firstInfor-user')">New bought</button></div> 


                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card card-stats" style="background-color:  #2893a9; border: none;">
                                <div class="card-body ">
                                    <div class="row">
                                        <div class="col-5">
                                            <div class="text-center">
                                                <i class="fa fa-book fa-4x" aria-hidden="true"></i>
                                            </div>
                                        </div>
                                        <div class="col-7 d-flex align-items-center">
                                            <div class="numbers first" id="firstInfor-course">

                                                <h5 class="card-title font-weight-bold text-white">New Course</h5>
                                                <h4 class="card-title text-white">${requestScope.courses.size()}</h4>
                                            </div>
                                            <div class="numbers second" id="secondInfor-course">
                                                <h5 class="card-category font-weight-bold text-white">All Course</h5>
                                                <h4 class="card-title text-white">${requestScope.allCourses.size()}</h4>
                                            </div> 
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 d-flex justify-content-center align-items-center"><button type="button" class="btn btn-light btn-first-click btn-sm w-100" onclick="openFirst('firstInfor-course', 'secondInfor-course')">New Course</button> </div>
                                        <div class="col-md-6 d-flex justify-content-center align-items-center"><button type="button" class="btn btn-light btn-second-click btn-sm w-100" onclick="openFirst('secondInfor-course', 'firstInfor-course')">All Course</button></div> 
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card card-stats " style="background-color:  #f76c66; border: none;">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-5">
                                            <div class="text-center">
                                                <i class="fa fa-check-circle fa-4x" aria-hidden="true" ></i>
                                            </div>
                                        </div>
                                        <div class="col-7 d-flex align-items-center">
                                            <div class="numbers first" id="firstInfor-register">
                                                <h5 class="card-title font-weight-bold text-white">Success Register</h5>
                                                <h4 class="card-title text-white">${requestScope.courseSuccessRegisters.size()}</h4>
                                            </div>
                                            <div class="numbers second" id="secondInfor-register">
                                                <h5 class="card-category font-weight-bold text-white">Canceled Register</h5>
                                                <h4 class="card-title text-white">${requestScope.courseCancelledRegisters.size()}</h4>
                                            </div> 
                                            <div class="numbers third" id="thirdInfor-register">
                                                <h5 class="card-category font-weight-bold text-white">Submitted Register</h5>
                                                <h4 class="card-title text-white">${requestScope.courseSubmittedRegisters.size()}</h4>
                                            </div> 
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-md-4 d-flex justify-content-center align-items-center"><button type="button" class="btn btn-light btn-first-click btn-sm w-100" onclick="openFirstCloseSecondThird('firstInfor-register', 'secondInfor-register', 'thirdInfor-register')">Success</button> </div>
                                        <div class="col-md-4 d-flex justify-content-center align-items-center"><button type="button" class="btn btn-light btn-second-click btn-sm w-100" onclick="openFirstCloseSecondThird('secondInfor-register', 'firstInfor-register', 'thirdInfor-register')">Canceled</button></div> 
                                        <div class="col-md-4 d-flex justify-content-center align-items-center"><button type="button" class="btn btn-light btn-second-click btn-sm w-100" onclick="openFirstCloseSecondThird('thirdInfor-register', 'secondInfor-register', 'firstInfor-register')">Submitted</button></div> 

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card card-stats" style="background-color:  #f7c659; border: none;">
                                <div class="card-body ">
                                    <div class="row">
                                        <div class="col-5">
                                            <div class="text-center">
                                                <i class="fa fa-money fa-4x" aria-hidden="true"></i>
                                            </div>
                                        </div>
                                        <div class="col-7 d-flex align-items-center">
                                            <div class="numbers first" id="firstInfor-revenues">
                                                <h5 class="card-category font-weight-bold text-white">New Revenue</5>
                                                    <h4 class="card-title text-white">${requestScope.revenues}$</h4>
                                            </div>
                                            <div class="numbers second" id="secondInfor-revenues">
                                                <h5 class="card-category font-weight-bold text-white">All Revenue</h5>
                                                <h4 class="card-title text-white">${requestScope.allRevenue}$</h4>
                                            </div> 
                                        </div>

                                    </div>
                                    <div class="row d-flex justify-content-center align-items-center">
                                        <div class="col-md-6 d-flex justify-content-center align-items-center"><button type="button" class="btn btn-light btn-first-click btn-sm w-100" onclick="openFirst('firstInfor-revenues', 'secondInfor-revenues')">New Revenues</button> </div>
                                        <div class="col-md-6 d-flex justify-content-center align-items-center"><button type="button" class="btn btn-light btn-second-click btn-sm w-100" onclick="openFirst('secondInfor-revenues', 'firstInfor-revenues')">All Revenues</button></div> 
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="row mb-2">  <h4 class="col-md-12 font-weight-bold">Revenues by Time</h4></div>   
                    <div class="row mb-2 ml-2">
                        <div class="col col-8"> 
                            <canvas id="myChartMonth"></canvas>                            
                        </div>
                        <div class="col col-4 position-relative"> 
                            <canvas class="position-absolute chartYear" id="myChartYear"></canvas>                            
                        </div>

                    </div>
                    <script>
                        const la = [];
                        const num = [];
                        <c:forEach items="${revenuesByMonth}" var="rbm">
                        la.push('${rbm.time}')
                        num.push(${rbm.revenues})
                        </c:forEach>
                        var myChartMonth = document.getElementById('myChartMonth').getContext('2d');
                        let massPopChart = new Chart('myChartMonth', {
                            type: 'bar', //bar,horizontalBar,pie,line,doughnut,radar,polarArea
                            data: {
                                labels: la,
                                datasets: [{
                                        label: 'Revenues($)',
                                        data: num,
                                        backgroundColor: 'rgb(145, 240, 134)',
                                        borderWidth: 4,
                                        borderColor: '#777',
                                        hoverBorderWidth: 3,
                                        hoverBorderColor: '#000'
                                    }]
                            },
                            options: {
                                plugins: {
                                    title: {
                                        display: true,
                                        text: 'Revenues by Month',
                                        font: {
                                            size: 25
                                        }

                                    }
                                }
                            }
                        });

                        const laY = [];
                        const numY = [];
                        <c:forEach items="${revenuesByYear}" var="rby">
                        laY.push('${rby.time}')
                        numY.push(${rby.revenues})
                        </c:forEach>
                        var myChartYear = document.getElementById('myChartYear').getContext('2d');
                        let massPopChartYear = new Chart('myChartYear', {
                            type: 'bar', //bar,horizontalBar,pie,line,doughnut,radar,polarArea
                            data: {
                                labels: laY,
                                datasets: [{
                                        label: 'Revenues($)',
                                        data: numY,
                                        backgroundColor: 'rgb(16, 126, 57)',
                                        borderWidth: 4,
                                        borderColor: '#777',
                                        hoverBorderWidth: 3,
                                        hoverBorderColor: '#000'
                                    }]
                            },
                            options: {
                                plugins: {
                                    title: {
                                        display: true,
                                        text: 'Revenues by Year',
                                        font: {
                                            size: 25
                                        }

                                    }
                                }
                            }
                        });

                    </script>
                    <div class="row mb-2">  <h4 class="col-md-12 font-weight-bold">Revenues by subject categories</h4></div>                    
                    <div class="row row-card-no-pd">
                        <div class="col-md-12">
                            <div class="card  ">
                                <div class="card-body">
                                    <c:forEach items="${requestScope.revenuesByCategory}" var="rbc">
                                        <div class="progress-card">
                                            <div class="d-flex justify-content-between mb-1">
                                                <span class="text-muted" id="percent" > ${rbc.category.categoryName}(<fmt:formatNumber value="${(requestScope.revenues != 0)?rbc.revenues/requestScope.revenues*100:'0'}" maxFractionDigits="2"/>%)</span>

                                                <span class="text-muted fw-bold">${rbc.revenues}$</span>
                                            </div>
                                            <div class="progress mb-2" style="height: 7px;">
                                                <div class="progress-bar bg-success"  role="progressbar" style="width: ${(rbc.revenues/requestScope.revenues)*100}%; background-color: get_random_color();" aria-valuenow="${(rbc.revenues/requestScope.revenues)*100}" aria-valuemin="0" aria-valuemax="100" data-toggle="tooltip" data-placement="top" title="${(rbc.revenues/requestScope.revenues)*100}%"></div>
                                            </div>
                                        </div>
                                    </c:forEach>


                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="card mt-4 " id="trend-success">
                        <div class="card-header bg-light">
                            <h4 class="card-title font-weight-bold ">Trending order counts</h4>
                            <p class="card-category"> <button type="button" class="btn btn-light btn-first-click active" onclick="openFirst('trend-success', 'trend-all')">Success</button> <button type="button" class="btn btn-light btn-first-click" onclick="openFirst('trend-all', 'trend-success')">All</button></p>
                        </div>
                        <div class="card-body">
                            <table class="table table-head-bg-success table-striped table-hover">
                                <thead class="thead-light">
                                    <tr>

                                        <th scope="col" colspan="1">Subject ID</th>
                                        <th scope="col" colspan="9">Name</th>
                                        <th scope="col" colspan="2">Order Counts</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.trendOrder}" var="to">
                                        <tr>
                                            <td colspan="1">${to.course.id}</td>
                                            <td colspan="9"><a href="coursedetail?id=${to.course.id}">${to.course.name}</a> </td>
                                            <td colspan="2">${to.orderNumber}</td>
                                        </tr>

                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="card mt-4 " id="trend-all">
                        <div class="card-header bg-light">
                            <h4 class="card-title font-weight-bold">Trending order counts</h4>
                            <p class="card-category"> <button type="button" class="btn btn-light btn-first-click" onclick="openFirst('trend-success', 'trend-all')">Success</button> <button type="button" class="btn btn-light btn-first-click active" onclick="openFirst('trend-all', 'trend-success')">All</button></p>
                        </div>
                        <div class="card-body">
                            <table class="table table-head-bg-success table-striped table-hover">
                                <thead class="thead-light">
                                    <tr>

                                        <th scope="col" colspan="1">Subject ID</th>
                                        <th scope="col" colspan="9">Name</th>
                                        <th scope="col" colspan="2">Order Counts</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.allTrendOrder}" var="to">
                                        <tr>
                                            <td colspan="1">${to.course.id}</td>
                                            <td colspan="9"><a href="coursedetail?id=${to.course.id}">${to.course.name}</a> </td>
                                            <td colspan="2">${to.orderNumber}</td>
                                        </tr>

                                    </c:forEach>

                                </tbody>
                            </table>
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
