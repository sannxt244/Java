<%@page import="dal.san.StateDBContext"%>
<%@page import="model.State"%>
<%@page import="java.util.ArrayList"%>
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
        <script>
            $(document).ready(function () {
                handleInput();
            });

            function handleInput() {
                const filterForm = document.getElementById('filterForm');
                const filterFormElements = Array.from(filterForm.elements);
                filterFormElements.forEach(element => {
                    console.log(element.value);
                });
                $.ajax({
                    url: 'registrations',
                    type: 'POST',
                    dataType: 'html',
                    data: {
                        course: ${param.course},
                        searchName: filterFormElements[0].value,
                        searchFrom: filterFormElements[1].value,
                        searchTo: filterFormElements[2].value,
                        searchStatus: filterFormElements[3].value,
                        searchEmail: filterFormElements[4].value,
                        sortField: document.getElementById('sortField').innerHTML
                    }
                }).done((result) => {
                    console.log("done")
                    document.getElementById("registrations").innerHTML = result;
                });
            }
        </script>
        <style>
            .btn__hover:hover {
                cursor: pointer;
                background-color: #97a5e1;
            }
            .btn__hover {
                
            }
        </style>
        <style>
            #filterForm {
                background-color: #333;
                padding: 57px 17px;
                color: white;
                border-radius: 14px;
            }

            #filterForm > * {
                margin-bottom: 10px;
                display: flex;
                align-items: center;
            }

            #filterForm > div > label {
                font-weight: bold;
                width: 100px;
            }

            #filterForm > div > input, select {
                padding: 5px 10px;
                width: 200px;
            }
        </style>
    </head>
    <body>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>
        <!-- Header section -->
        <%@include file="header.jsp" %>
        <!-- Header section end -->
        <!-- registrations section -->
        <div>
            <div style="margin: 3rem 26rem 1rem 3rem" class="d-flex justify-content-between ">
                <h3>Registrations</h3>
                <a href="registration-details?action=add&course=${param.course}&created=${"ahurnell9l"}" class="btn btn-success px-4">Add</a>
            </div>
        </div>
        <div class="d-flex justify-content-around mt-3">
            <table class="table w-75 table-bordered">
                <thead>
                    <tr style="background-color: #acbcff">
                        <th class="btn__hover" scope="col" onclick="handleSort('id')" id="id">ID</th>
                        <th class="btn__hover" scope="col" onclick="handleSort('email')" id="email">Email</th>
                        <th class="btn__hover" scope="col" onclick="handleSort('registrationTime')" id="registrationTime">Registration time</th>
                        <th class="btn__hover" scope="col" onclick="handleSort('subject')" id="subject">Subject</th>
                        <th class="btn__hover" scope="col" onclick="handleSort('package')" id="package">Package</th>
                        <th class="btn__hover" scope="col" onclick="handleSort('totalCost')" id="totalCost">Total Cost</th>
                        <th class="btn__hover" scope="col" onclick="handleSort('status')" id="status">Status</th>
                        <th class="btn__hover" scope="col" onclick="handleSort('validFrom')" id="validFrom">Valid From</th>
                        <th class="btn__hover" scope="col" onclick="handleSort('validTo')" id="validTo">Valid to</th>
                        <th class="btn__hover" scope="col">Last Updated</th>
                        <th class="btn__hover" scope="col">Action</th>
                    </tr>
                </thead>
                <tbody id="registrations">
                </tbody>
            </table>

            <form id="filterForm" action="registrations" method="POST">
                <div>
                    <label>Name</label>
                    <input type="text" oninput="handleInput()"/>
                </div>
                <div>
                    <label>From</label>
                    <input type="date" onchange="handleInput()"/>
                </div>
                <div>
                    <label>To</label>
                    <input type="date" onchange="handleInput()"/>
                </div>
                <div>
                    <label>Status</label>
                    <%
                        ArrayList<State> states = new ArrayList<State>();
                        StateDBContext stateDBC = new StateDBContext();
                        states = stateDBC.findAll();
                    %>
                    <select class="custom-select custom-select-lg" onchange="handleInput()">
                        <option selected value="">All</option>
                        <%
                            for (State state : states) {
                        %>
                        <option value="<%=state.getId()%>"><%=state.getState()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div>
                    <label>Email</label>
                    <input type="text" oninput="handleInput()"/>
                </div>
            </form>
        </div>
        <div style="display: none" id="down">&#9662;</div>
        <div style="display: none" id="up">&#9652;</div>
        <div style="display: none" id="sortField"></div>
        <script>
            const down = document.getElementById('down').innerHTML;
            const up = document.getElementById('up').innerHTML;
            const fields = ["ID", "Email", "Registration time", "Subject", "Package", "Total cost", "Status", "Valid from", "Valid to"];
            const fieldsId = ["id", "email", "registrationTime", "subject", "package", "totalCost", "status", "validFrom", "validTo"];
            const sortFields = ["[user].[username]", "[user].[email]", "[Course_Register].[date_register]", "[course].[name]", "[Package].[package_name]", "[Price_Package].[price]", "[State].[state]", "[valid_from]", "[valid_to]"];
            function handleSort(fieldName)
            {
                for (var i = 0; i < fieldsId.length; i++) {
                    if (fieldName === fieldsId[i]) {
                        var sortField = document.getElementById(fieldName).innerHTML;
                        if (sortField.includes(down)) {
                            //console.log('down');
                            document.getElementById('sortField').innerHTML = sortFields[i] + " ASC";
                            document.getElementById(fieldName).innerHTML = fields[i] + up;
                        } else if (sortField.includes(up)) {
                            //console.log('up');
                            document.getElementById('sortField').innerHTML = sortFields[i];
                            document.getElementById(fieldName).innerHTML = fields[i];
                        } else {
                            //console.log('null');
                            document.getElementById('sortField').innerHTML = sortFields[i] + " DESC";
                            document.getElementById(fieldName).innerHTML = fields[i] + down;
                        }
                    } else {
                        document.getElementById(fieldsId[i]).innerHTML = fields[i];
                    }
                }
                console.log(document.getElementById('sortField').innerHTML);
                handleInput();
            }
        </script>
        <!-- registrations section -->

        <!-- footer section end -->



    </body>
</html>