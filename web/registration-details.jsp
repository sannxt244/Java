<%@page import="dal.san.StateDBContext"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.State"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

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

        <!--====== Javascripts & Jquery ======-->
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/circle-progress.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>
        <script>

            var Packages = [];
            <c:forEach var="Package" items="${Packages}">
            var Package = {id: ${Package.id}, name: '${Package.name}', duration: ${Package.duration}};
            Packages.push(Package);
            </c:forEach>
            var PricePackages = [];
            <c:forEach var="PricePackage" items="${PricePackages}">
            var PricePackage = {packageId: ${PricePackage.packageId}, price: ${PricePackage.price}, priceSale: ${PricePackage.priceSale}};
            PricePackages.push(PricePackage);
            </c:forEach>
            $(document).ready(function () {
                handleChange(Packages[0].id);
                handleDateChange(Packages[0].duration);
            });
            console.log(Packages);
            console.log(PricePackages);

            const handleChange = (value) => {
                PricePackages.map(item => {
                    if (item.packageId == value) {
                        document.getElementById('price').value = item.price;
                        document.getElementById('priceSale').value = item.priceSale;
                        var pack = Packages.find(item => item.id == value);
                        handleDateChange(pack.duration);
                    }
                });
            };

            const handleDateChange = (duration) => {
                document.getElementById('registrationTime').valueAsDate = new Date();
                document.getElementById('validFrom').valueAsDate = new Date();
                var validFrom = document.getElementById('validFrom').valueAsDate;
                document.getElementById('validTo').valueAsDate = new Date(validFrom.setMonth(validFrom.getMonth() + duration));
            };
        </script>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>
        <!-- Header section -->
        <%@include file="header.jsp" %>
        <!-- Header section end -->
        <%
            ArrayList<State> states = new ArrayList<State>();
            StateDBContext stateDBC = new StateDBContext();
            states = stateDBC.findAll();
        %>
        <div>
            <c:if test="${action == 'add'}">
                <form action="add-registration" method="POST">
                    <input type="hidden" name="courseId" value="${courseId}"/>
                    <table class="mx-auto">
                        <div style="margin: 3rem 26rem 1rem" class="d-flex justify-content-between ">
                            <div class="d-flex">
                                <h3 class="me-3">Username: </h3> <input required name="username" type="text"/>
                            </div>
                            <button type="submit" class="btn btn-success px-4">Add</button>
                        </div>
                        <tr class="mb-2">
                            <td class="px-5 py-2">Course</td>
                            <td class="px-5 py-2">
                                <input class="px-4 py-2" type="hidden" name="course" value="${Course.id}"/>
                                <input readonly class="px-4 py-2" type="text" value="${Course.name}"/>
                            </td>
                            <td class="px-5 py-2">Package</td>
                            <td class="px-5 py-2">
                                <select name="package" onchange="handleChange(this.value)">
                                    <c:forEach var="Package" items="${Packages}">
                                        <option value="${Package.id}">${Package.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="px-5 py-2">Price</td>
                            <td class="px-5 py-2"><input id="price" class="px-4 py-2" type="text"/></td>
                            <td class="px-5 py-2">Sale Price</td>
                            <td class="px-5 py-2"><input id="priceSale" class="px-4 py-2" type="text"/></td>
                        </tr>
                        <tr>
                            <td class="px-5 py-2">Gender</td>
                            <td class="px-5 py-2">
                                <input type="radio" name="gender" value="1" id="maleRadio" checked/><label for="maleRadio">&nbsp;&nbsp;Male</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="radio" name="gender" value="0" id="femaleRadio"/><label for="femaleRadio">&nbsp;&nbsp;Female</label>
                            </td>
                            <td required class="px-5 py-2">Email</td>
                            <td class="px-5 py-2"><input class="px-4 py-2" type="text" name="email"/></td>
                        </tr>
                        <tr>
                            <td required class="px-5 py-2">Mobile</td>
                            <td class="px-5 py-2"><input class="px-4 py-2" type="text" name="mobile"/></td>
                            <td class="px-5 py-2">Registration time</td>
                            <td class="px-5 py-2"><input class="px-4 py-2" id="registrationTime" type="date" value=""/></td>
                        </tr>
                        <tr>
                            <td class="px-5 py-2">Status</td>
                            <td class="px-5 py-2">
                                <select name="state" class="custom-select w-100" id="inputGroupSelect02">
                                    <%
                                        for (State state : states) {
                                    %>
                                    <option value="<%=state.getId()%>"><%=state.getState()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </td>
                            <td class="px-5 py-2">Created by</td>
                            <td class="px-5 py-2"><input class="px-4 py-2" type="text" name="createdBy" value="${Created}"/></td>
                        </tr>
                        <tr>
                            <td class="px-5 py-2">Valid from</td>
                            <td class="px-5 py-2"><input id="validFrom" class="px-4 py-2" type="date"/></td>
                            <td class="px-5 py-2">Valid to</td>
                            <td class="px-5 py-2"><input id="validTo" class="px-4 py-2" type="date"/></td>
                        </tr>
                    </table>
                </form>
            </c:if>
            <c:if test="${action == 'update'}">
                <div style="margin: 3rem 26rem 1rem" class="d-flex justify-content-between ">
                    <h3>Username: ${Username}</h3>
                    <!--<button type="button" class="btn btn-success px-4">Edit</button>-->
                </div>
                <form action="registration-details" method="POST">
                    <input type="hidden" name="course" value="${Course.id}"/>
                    <input type="hidden" name="username" value="${Username}"/>
                    <table class="mx-auto">
                        <tr class="mb-2">
                            <td class="px-5 py-2">Course</td>
                            <td class="px-5 py-2"><input readonly class="px-4 py-2" type="text" value="${Course.name}"/></td>
                            <td class="px-5 py-2">Package</td>
                            <td class="px-5 py-2"><input readonly class="px-4 py-2" type="text" value="${Package}"/></td>
                        </tr>
                        <tr>
                            <td class="px-5 py-2">Price</td>
                            <td class="px-5 py-2"><input readonly class="px-4 py-2" type="text" value="${Price}"/></td>
                            <td class="px-5 py-2">Sale Price</td>
                            <td class="px-5 py-2"><input readonly class="px-4 py-2" type="text" value="${SalePrice}"/></td>
                        </tr>
                        <tr>
                            <td class="px-5 py-2">Gender</td>
                            <td class="px-5 py-2">
                                <input readonly type="radio" name="gender" value="male" id="maleRadio" ${Gender == true?"checked":""}/><label for="maleRadio">&nbsp;&nbsp;Male</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input readonly type="radio" name="gender" value="female" id="femaleRadio" ${Gender == false?"checked":""}/><label for="femaleRadio">&nbsp;&nbsp;Female</label>
                            </td>
                            <td class="px-5 py-2">Email</td>
                            <td class="px-5 py-2">
                                <input readonly class="px-4 py-2" type="text" value="${Email}"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="px-5 py-2">Mobile</td>
                            <td class="px-5 py-2">
                                <input readonly class="px-4 py-2" type="text" value="${Mobile}"/>
                            </td>
                            <td class="px-5 py-2">Registration time</td>
                            <td class="px-5 py-2">
                                <input readonly class="px-4 py-2" type="text" value="${RegistrationTime}"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="px-5 py-2">Status</td>
                            <td class="px-5 py-2">
                                <select class="custom-select w-100" id="inputGroupSelect02" onchange="this.form.submit()" name="status">
                                    <%
                                        for (State state : states) {
                                            int stateId = (Integer) request.getAttribute("Status");
                                            if (state.getId() == stateId) {
                                    %>
                                    <option selected value="<%=state.getId()%>"><%= state.getState()%></option>
                                    <%
                                        }
                                        if (state.getId() != stateId) {
                                    %>
                                    <option value="<%=state.getId()%>"><%= state.getState()%></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </td>
                            <td class="px-5 py-2">Valid from</td>
                            <td class="px-5 py-2">
                                <input readonly class="px-4 py-2" type="text" value="${ValidFrom}"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="px-5 py-2">Valid to</td>
                            <td class="px-5 py-2">
                                <input readonly class="px-4 py-2" type="text" value="${ValidTo}"/>
                            </td>
                            <td class="px-5 py-2">Notes</td>
                            <td class="px-5 py-2">
                                <input class="px-4 py-2" type="text" value="None"/>
                            </td>
                        </tr>
                    </table>
                </form>    
            </c:if>
        </div>

        <!-- footer section end -->



    </body>
</html>