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
        <%@include file="header.jsp" %>
        <!-- Header section -->
        
        <div class="main-panel">
            <div class="content">
                <div class="container-fluid  mb-2">
                    <div class="row">

                        <div class="col-md-10 ">
                            <h3>Slider</h3>
                            <table class="table">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Title</th>                     
                                        <th scope="col">Content</th>
                                        <th scope="col">Link</th>
                                        <th scope="col">Img-Link</th>
                                        <th scope="col">Status</th>
                                        <th scope="col" class="bg-info"><a href="slideradd.jsp">Add +</a>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.listSlider}" var="i">
                                        <tr>
                          
                                            <th scope="row">${i.id}</th>
                                            <td>${i.title}</td>
                                            <td>${i.content}</td>
                                            <td>${i.link}</td>
                                            <td>
                                                <img height="50px" width="100px" src="${i.img_link}">
                                            </td>
                                            
                                            <td>
                                                    
                                                <form id="hideAndShow${i.id}" action="updateSliderServlet" method="POST"><input type="hidden" name="id" value="${i.id}">
                                                    <select name="isActive" onchange="submitForm('hideAndShow${i.id}')">                                                           
                                                        <option value="1" <c:if test="${i.isActive == true}">selected="selected"</c:if>>Show</option>
                                                        <option value="0" <c:if test="${i.isActive == false}">selected="selected"</c:if>>Hide</option>
                                                        </select>
                                                </form>       
                                                       
                                            </td>
                                            <td>
                                                <a href="sliderdetail.jsp?sid=${i.id}&stitle=${i.title}&scontent=${i.content}&slink=${i.link}&simg_link=${i.img_link}">update</a> |
                                                <a href="#" onclick="Mess(${i.id})">delete</a> 
                                            </td>
                                            
                                            
                                                <script>
                                                    function Mess(id){
                                                    var option = confirm('Are you sure to delete');
                                                        if(option === true){
                                                            window.location.href = 'deleteSliderServlet?sid='+id;
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
                            function submitForm(formId){
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
