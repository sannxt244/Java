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
        <script>
            function showTopic() {

                const idCourse = document.getElementById('selectCourse').value;
            <c:forEach items="${courses}" var="a">
                if (idCourse == ${a.id}) {
                    let  topic0 = document.createElement("option")
                    topic0.setAttribute("value","${-1}")   
                    topic0.setAttribute("hidden","hidden")
                    document.getElementById('topic${a.id}').appendChild(topic0)
                    document.getElementById('topic${a.id}').style.display = 'block'
                    document.getElementById('topic0').style.display = 'none'
                } else {
                    if (idCourse == 0) {
                        document.getElementById('topic0').style.display = 'block'
                    }
                    document.getElementById('topic${a.id}').style.display = 'none'
                }
            </c:forEach>
            }

        </script>
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

                                <form class="card-body Form-postDetail" action="questiondetail" method="POST"> 
                                    <input type="hidden" name="questionId" value="${question.id}">
                                <input type="hidden" name="pageId" value="${page}">
                                <input type="hidden" name="categoryId" value="${categoryId}">
                                <input type="hidden" name="search" value="${search}">
                                <div class="row ">
                                    <div class="col-md-12">
                                        <div class="row">
                                            <div class="col-md-2 d-flex justify-content-center align-items-center">Course</div>
                                            <div class="col-md-10">
                                                <select class="w-100 form-control" onchange="showTopic()" id="selectCourse" name="courseId">
                                                    <c:forEach items="${courses}" var="c">
                                                        <option    ${c.id==courseId? "selected":""}  value="${c.id}">${c.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row my-2">
                                    <div id="topics" class="col-md-12">
                                        <div class="row">
                                            <div class="col-md-2 d-flex justify-content-center align-items-center">Topic</div>
                                            <div class="col-md-10">
                                                <select name="topicId0" id="topic0" class="topicFilter w-100 form-control-sm">  
                                                     <option hidden="hidden" value="${-1}"></option>
                                                </select>
                                                <c:forEach items="${courses}" var="c">
                                                    <select name="topicId" id="topic${c.id}" class="topicFilter w-100 form-control-sm">   
                                                        <c:forEach items="${c.topics}" var="t">
                                                            
                                                            <option ${question.topicId == t.id?"selected":""} value="${t.id}">
                                                                ${t.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </c:forEach></div>
                                        </div>

                                    </div>
                                </div>                                                  
                                <div class="mb-3 row">
                                    <label for="content" class="col-sm-2 col-form-label d-flex justify-content-center align-items-center">Content</label>
                                    <div class="col-sm-10">
                                        <textarea  class="form-control" rows="5" id="content" required="required"  name="content">${question.content}</textarea>

                                    </div>
                                </div>                               

                                <div class="mb-3 row">
                                    <label for="isActive" class="col-sm-2 col-form-label d-flex justify-content-center align-items-center">Status</label>
                                    <div class="col-sm-10">
                                        <select name="isActive" id="isActive" class="form-control">                                           
                                            <option ${question.isActived == true?"selected":""} value="1">Show</option>
                                            <option ${question.isActived == false?"selected":""} value="0">Hide</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="mb-3 row">
                                    <div class="col-sm-2 col-form-label d-flex justify-content-center align-items-center ">Solution</div>

                                    <div class="col-sm-10 d-flex justify-content-center align-items-center">Answers</div>
                            
                                </div>
                                <c:forEach items="${question.answerList}" var="a">
                                    <div class="mb-3 row ">
                                        <div class="col-sm-2 d-flex justify-content-center align-items-center">
                                            <input required="required"  class="form-control-sm  " type="radio" name="isSolution" value="${a.id}" ${a.isSolution == true?"checked":""}/>
                                        </div>
                                        <div class="col-sm-10 d-flex justify-content-center align-items-center">
                                            <input value = "${a.content}" type="text" name="answers" required="required" class="form-control"/>
                                        </div>
<!--                                        <div  class="col-sm-2"> 
                                            <button class="btn btn-primary">+</button> 
                                            <button onclick="removeField(this)" type="button" class="btn btn-primary">-</button> 
                                        </div>-->

                                    </div>
                                </c:forEach>
<!--                                <div class="mb-3 row field">
                                    <div class="col-sm-2 d-flex justify-content-center align-items-center">
                                        <input required="required"  class="form-control-sm  " type="radio" name="isSolution" value="${a.id}"/>
                                    </div>
                                    <div class="col-sm-8 d-flex justify-content-center align-items-cente">
                                        <input value = "${a.content}" type="text" name="answers" required="required" class="form-control" id="answer${question.getAnswerList().size()+1}"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-primary">+</button> 
                                        <button type="button" class="btn btn-primary">-</button> 
                                    </div>

                                </div>-->


                                <div class="mb-3 row">
                                    <label for="explain" class="col-sm-2 col-form-label  d-flex justify-content-center align-items-center">Explain</label>
                                    <div class="col-sm-10">
                                        <textarea  class="form-control" id="explain" required="required"  name="explain">${explain}</textarea>
                                    </div>

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
        <script>
            document.getElementById('topic${courseId}').style.display = 'block';

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
