<%-- 
    Document   : subjectList
    Created on : Jun 15, 2022, 8:15:48 PM
    Author     : LAPTOP D&N
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebUni - Education Template</title>
        <meta charset="UTF-8">
        <meta name="description" content="WebUni Education Template">
        <meta name="keywords" content="webuni, education, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/styles2.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>

        <script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>

        <script>
            tinymce.init({
                selector: 'textarea#editor',
                skin: 'bootstrap',
                plugins: 'lists, link, image, media',
                toolbar: 'h1 h2 bold italic strikethrough blockquote bullist numlist backcolor | link image media | removeformat help',
                menubar: false,
            });
        </script>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="SubjectDetailWithPriceServlet?courseid=${course.id}">Return Subject List</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i
                    class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..."
                           aria-describedby="btnNavbarSearch" />
                    <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i
                            class="fas fa-search"></i></button>
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Settings</a></li>
                        <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                        <li>
                            <hr class="dropdown-divider" />
                        </li>
                        <li><a class="dropdown-item" href="#!">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            
            <div id="layoutSidenav_content">
                <form action="EditSubjectLessonServlet" method="POST">
                    <main>
                        <div class="container-fluid px-4">
                            <h1 class="mt-4">Course Name: ${course.name}</h1>
                            <img src="${course.img}" alt="Couse Image" class="img-thumbnail" style="width: 800px; height: 500px">
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item active">Dashboard</li>
                            </ol>
                        </div>
                    </main>

                    <div class="form-group">
                        <label for="formGroupExampleInput" class="col-form-label-lg" value="${course.id}" name="courseId" >Course ID: ${course.id}</label>
                        <input type="text" value="${course.id}" name="courseId" class="form-control form-control-lg" id=" formGroupExampleInput" placeholder="Example input" readonly>
                        <label for="formGroupExampleInput" class="col-form-label-lg" value="${course.packageId}" name="packageId" >Course Package ID: ${course.packageId}</label>
                        <input type="text" value="${course.packageId}" name="packageId" class="form-control form-control-lg" id=" formGroupExampleInput"placeholder="Example input"readonly>
                    </div>

                    <div class="form-group">
                        <label for="formGroupExampleInput" class="col-form-label-lg">Course Video Link</label>
                        <input type="text" value="${course.img}" name="img" class="form-control form-control-lg" id=" formGroupExampleInput"placeholder="Example input" placeholder="Input Image Link">
                    </div>

                    <br>

                    <div class="form-group">
                        <label for="formGroupExampleInput2" class="col-form-label-lg">Course Name</label>
                        <input type="text" value="${course.name}" name="course_name" class="form-control form-control-lg " id=" formGroupExampleInput2" placeholder="Input Lesson Name">
                    </div>

                    <br>
                    
                    <div class="form-group">
                        <c:if test="${course.categoryId == 1}">
                            <label for="formGroupExampleInput2" class="col-form-label-lg">Course Status: Back-end Languages</label>
                        </c:if>
                            
                        <c:if test="${course.categoryId == 2}">
                            <label for="formGroupExampleInput2" class="col-form-label-lg">Course Status: Font-end Languages</label>
                        </c:if>
                            
                        <c:if test="${course.categoryId == 3}">
                            <label for="formGroupExampleInput2" class="col-form-label-lg">Course Status: Others</label>
                        </c:if>  
                        
                        <select class="form-select form-select-lg mb-3" name="categoryId" aria-label=".form-select-lg example">                        
                            <c:forEach items="${cateList}" var="o">
                                <option value="${o.id}">${o.categoryName}</option>
                            </c:forEach>
                        </select>
                    </div>

                   <br>               

                    <div class="form-group">
                        <label for="formGroupExampleInput2" class="col-form-label-lg">Course Package: ${course.package_name}</label>
                        <br>
                        <label for="formGroupExampleInput2" class="col-form-label-lg">Course Price: ${course.price}$ </label>
                        <input type="text" value="${course.price}" name="price" class="form-control form-control-lg" id=" formGroupExampleInput" placeholder="Input Course Price">
                        <label for="formGroupExampleInput2" class="col-form-label-lg">Sales Price:  ${course.priceSale}$</label>
                        <input type="text" value="${course.priceSale}" name="priceSale" class="form-control form-control-lg" id=" formGroupExampleInput" placeholder="Input Sales">
                        <br>
                        
                    </div>

                    <br>

                    <div class="form-group">
                        <c:if test="${course.isActive == 1}">
                            <label for="formGroupExampleInput2" class="col-form-label-lg">Course Status: <a style="color: green; font-weight: bolder">Active</a></label>
                        </c:if>
                        <c:if test="${course.isActive == 0}">
                            <label for="formGroupExampleInput2" class="col-form-label-lg">Course Status: <a style="color: red; font-weight: bolder">Deactivate</a></label>
                        </c:if>
                        <select name="status" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
                            <option value="1" style="color: green; font-weight: bolder" <c:if test="${course.isActive == 1}">selected</c:if>>Active</option>
                            <option value="0" style="color: red; font-weight: bolder" <c:if test="${course.isActive == 0}">selected</c:if>>Deactivate</option>
                        </select>
                    </div>


                    <div class="container mt-4 mb-4">
                        <!--Bootstrap classes arrange web page components into columns and rows in a grid -->
                        <div class="row justify-content-md-center">
                            <div class="col-md-12 col-lg-8">
                                <h1 class="h2 mb-4">Course Description</h1>
                                <label>Describe the issue in detail</label>
                                <div class="form-group">
                                    <textarea id="editor" name="description">${course.description}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>

                    <br>

                    <div class="container">
                        <div class="row">
                            <div class="col text-center">
                                <button type="submit" class="btn btn-primary">Save Subject</button>
                            </div>
                        </div>
                    </div>
                </form>

                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2022</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/assets/demo/chart-area-demo.js"></script>
        <script src="${pageContext.request.contextPath}/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/js/datatables-simple-demo.js"></script>
    </body>
</html>
