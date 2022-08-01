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
            <a class="navbar-brand ps-3" href="PricePackageListServlet">Return to Package List</a>
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

                <form action="PackageDetail" method="POST">
                      
                    <br>
                     <br>
                      <br>
                           <br>
                   <div class="form-group">
                        <label for="formGroupExampleInput" class="col-form-label-lg">ID: </label>
                        <input type="text" name="package_id" value="${pc.id}" readonly name="vid_id" class="form-control form-control-lg" id=" formGroupExampleInput"placeholder="Example input">
                    </div>
                                
                    <br>
                     <br>
                      <br>
                    <div class="form-group">
                        <label for="formGroupExampleInput" class="col-form-label-lg text-center">Package Name: </label>
                        <input type="text" name="package_name" value="${pc.package_name}"class="form-control form-control-lg " id=" formGroupExampleInput2" placeholder="Input Duration (Month)">
                    </div>

                    <br>
                     <br>
                      <br>
                      
                    <div class="form-group">
                        <label for="formGroupExampleInput" class="col-form-label-lg text-center">Duration: </label>
                        <input type="number" name="duration" value="${pc.duration}"class="form-control form-control-lg " id=" formGroupExampleInput2" placeholder="Input Duration (Month)">
                    </div>

                    <br>
                     <br>
                      <br> <br> <br> <br> <br>
                      
                    <div class="container">
                        <div class="row">
                            <div class="col text-center">
                                <button type="submit" class="btn btn-primary">Save Package</button>
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
        <script src="../js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="../assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="../js/datatables-simple-demo.js"></script>
    </body>
</html>
