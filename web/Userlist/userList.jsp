<%-- 
    Document   : userList
    Created on : Jun 22, 2022, 4:44:34 AM
    Author     : Minh Lee
--%>

<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>User List</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<link href="css/main.css" rel="stylesheet" type="text/css"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        <link href="css/main.css" rel="stylesheet" type="text/css"/>

<style>
body {
    color: #566787;
    background: #f5f5f5;
    font-family: 'Varela Round', sans-serif;
    font-size: 13px;
}
.table-responsive {
    margin: 30px 0;
}
.table-wrapper {
    min-width: 1000px;
    background: #fff;
    padding: 20px 25px;
    border-radius: 3px;
    box-shadow: 0 1px 1px rgba(0,0,0,.05);
}
.table-title {
    padding-bottom: 15px;
    background: #299be4;
    color: #fff;
    padding: 16px 30px;
    margin: -20px -25px 10px;
    border-radius: 3px 3px 0 0;
}
.table-title h2 {
    margin: 5px 0 0;
    font-size: 24px;
}
.table-title .btn {
    color: #566787;
    float: right;
    font-size: 13px;
    background: #fff;
    border: none;
    min-width: 50px;
    height: 50px;
    border-radius: 2px;
    border: none;
    outline: none !important;
    margin-left: 10px;
}
.table-title .btn:hover, .table-title .btn:focus {
    color: #566787;
    background: #f2f2f2;
}
.table-title .btn i {
    float: left;
    font-size: 21px;
    margin-right: 5px;
}
.table-title .btn span {
    float: left;
    margin-top: 2px;
}
table.table tr th, table.table tr td {
    border-color: #e9e9e9;
    padding: 12px 15px;
    vertical-align: middle;
}
table.table tr th:first-child {
    width: 60px;
}
table.table tr th:last-child {
    width: 100px;
}
table.table-striped tbody tr:nth-of-type(odd) {
    background-color: #fcfcfc;
}
table.table-striped.table-hover tbody tr:hover {
    background: #f5f5f5;
}
table.table th i {
    font-size: 13px;
    margin: 0 5px;
    cursor: pointer;
}	
table.table td:last-child i {
    opacity: 0.9;
    font-size: 22px;
    margin: 0 5px;
}
table.table td a {
    font-weight: bold;
    color: #566787;
    display: inline-block;
    text-decoration: none;
}
table.table td a:hover {
    color: #2196F3;
}
table.table td a.settings {
    color: #2196F3;
}
table.table td a.delete {
    color: #F44336;
}
table.table td i {
    font-size: 19px;
}
table.table .avatar {
    border-radius: 50%;
    vertical-align: middle;
    margin-right: 10px;
}
.status {
    font-size: 30px;
    margin: 2px 2px 0 0;
    display: inline-block;
    vertical-align: middle;
    line-height: 10px;
}
.text-success {
    color: #10c469;
}
.text-info {
    color: #62c9e8;
}
.text-warning {
    color: #FFC107;
}
.text-danger {
    color: #ff5b5b;
}
.pagination {
    float: right;
    margin: 0 0 5px;
}
.pagination li a {
    border: none;
    font-size: 13px;
    min-width: 30px;
    min-height: 30px;
    color: #999;
    margin: 0 2px;
    line-height: 30px;
    border-radius: 2px !important;
    text-align: center;
    padding: 0 6px;
}
.pagination li a:hover {
    color: #666;
}	
.pagination li.active a, .pagination li.active a.page-link {
    background: #03A9F4;
}
.pagination li.active a:hover {        
    background: #0397d6;
}
.pagination li.disabled i {
    color: #ccc;
}
.pagination li i {
    font-size: 16px;
    padding-top: 6px
}
.hint-text {
    float: left;
    margin-top: 10px;
    font-size: 13px;
}
</style>
<script>
$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();
});
</script>
</head>
<body style="position: relative">
     <!-- Header section -->
        <div class="header bg-dark py-2">
            <div class="container">
                <div class="row">
                    <div class="col-lg-2 col-md-2">
                        <div class="site-logo">
                            <img src="img/logo.png" alt="">
                        </div>
                        <div class="nav-switch">
                            <i class="fa fa-bars"></i>
                        </div>
                    </div>
                    <div class="col-lg-10 col-md-10">
                        <c:if test="${sessionScope.account == null}">
                            <a href="register" class="col-1 site-btn header-btn">Sign Up</a>
                            <a href="login" class="col-1 site-btn header-btn"  style="margin-right: 20px">Login</a>
                        </c:if>

                        <c:if test="${sessionScope.account != null}">
                            <a href="logout" id="logout" style="margin-left: 20px;" class="site-btn header-btn" >Log Out</a>
                            <a href="profile?userName=${sessionScope.account.userName}" class="col-1 site-btn header-btn">User Profile</a>

                            <script type="text/javascript">
//                                function logOut() {
//
//                                    document.getElementById('logout').href = "logout";
//                                }
//                            </script>
                            </c:if>
                        <nav class="col-8 main-menu">
                            <ul>
                                <li><a href="home">Home</a></li>
                                <li><a href="course">Courses</a></li>
                                <li><a href="blog">Blog</a></li>
                               
                                </ul>
                            </nav>



                        </div>
                    </div>
                </div>
            </div>
            <!-- Header section end -->
    <div class="col-md-1 d-flex align-items-center" style="position: fixed; z-index: 3; top: 100px">
                        <i class="fa fa-bars w-100 nav-bar-icon" onclick="openSubNav('subNav')" aria-hidden="true"></i>
                         <div  id="subNav" class="sub-nav-content">
                             <div class="sub-nav-content-cancel"><i class="fa fa-times sub-nav-content-cancel " onclick="closeSubNav('subNav')" aria-hidden="true"></i></div>
                             <div class="sub-nav-content-item "><i class="fa fa-tachometer sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a  href="dashboard">Dashboard</a></div>
                            <div class="sub-nav-content-item "> <i class="fa fa-newspaper-o sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a href="postlist">Post Management</a></div>
                            <div class="sub-nav-content-item"><i class="fa fa-book sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a  href="#">Course Management</a></div>
                            <div class="sub-nav-content-item"><i class="fa fa-users sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a class="text-danger" href="#">User Management</a></div>
                            <div class="sub-nav-content-item"><i class="fa fa-question-circle sub-nav-content-icon fa-2x" aria-hidden="true"></i> <a  href="questionlist">Question Management</a></div>
                         

                        </div>
                        
                    </div>
    <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/circle-progress.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-5">
                        <h2>User <b>Management</b></h2>
                    </div>
                    <div class="col-sm-7">
                        <a href="#" class="btn d-flex align-items-center "><i class="material-icons" id="searchIcon" style="margin-top:3px;">search</i> <span>
                                <form id="searchForm" action="userlist" method="post"><input type="text" name="search" /></form></span></a>	
                                
                                <script type="text/javascript">
                                    $(document).ready(function(){
                                     $('#searchIcon').click(function(){

                                             $('#searchForm').submit();   
                                     });
                                    })
                                    

                                </script>
                        <!--<a href="#" class="btn btn-secondary d-flex align-items-center"><i class="material-icons">&#xE147;</i> <span>Add New User</span></a>-->
                        					
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>						
                        <th onclick="">Date Created</th>
                        <th id="roleTitle" onclick="sortRole()">Role</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="tbody">
                    
                    <%!
                        int userNumber = 0;
                        List<Integer> pagels = new ArrayList<Integer>();
                    %>
                <%  if (userNumber != 0) { userNumber = 0; pagels = new ArrayList<Integer>();}%>
                    <c:forEach items="${requestScope.user}" var="u">    <!-- user list -->
                    <tr id="user<%=++userNumber%>" > <!-- userNumber=1 -->
                        
                        <td> <%=userNumber%> </td>
                        <td><a href="userdetail?userName=${u.userName}"><img src="${u.avatar}" class="avatar" alt=" " style="max-width:30px"> ${u.userName}</a></td>
                        <td>${u.registerDate}</td>                        
                        <td>${u.role.roleName}</td>
                        <td><c:if test="${u.verify == true }"><span class="status text-success">&bull;</span> Activated</c:if>
                            <c:if test="${u.verify == false}"><span class="status text-warning">&bull;</span> Deactivated</c:if></td>
                        <td>
                            <a href="#" class="edit" onclick="edit('${u.userName}',<%=userNumber%>, '${u.role.roleName}', '${u.verify}')" title="Editing" data-toggle="tooltip"><i class="material-icons">build</i></a>
                            <a href="deleteuser?userName=${u.userName}#user<%=userNumber%>" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE5C9;</i></a>
                            
                        <script type="text/javascript">
                                
                            let set = 0; 
                            function edit(userName,u,r,v){
                                
                                if (set==1){
                                    
                                    let s = document.getElementById('user'+(u)).getElementsByTagName('td');
                                    var tempr = s[3].getElementsByTagName('select')[0].value;
                                    var tempv = s[4].getElementsByTagName('select')[0].value;
                                    
                                    let as = s[5].getElementsByTagName('a')[0];
                                    as.href= 'editrs?userName='+userName+'&role='+tempr+"&verify="+tempv;
                                    
                                }
                                if (set==0){
                                    alert("You can edit Role and Status for user " + u + "!");
                                    let s = document.getElementById('user'+(u)).getElementsByTagName('td');
                                     
                                    if (r == 'admin')
                                        s[3].innerHTML = "<select name='role'> <option value='admin' selected='' >admin</option> <option value='expert'>expert</option> <option value='customer'>customer</option> </select>";
                                    else if (r == 'expert')
                                        s[3].innerHTML = "<select name='role'> <option value='admin' >admin</option> <option value='expert' selected=''>expert</option> <option value='customer'>customer</option> </select>";
                                    else if (r == 'customer')
                                            s[3].innerHTML = "<select name='role'> <option value='admin' >admin</option> <option value='expert'>expert</option> <option value='customer' selected=''>customer</option> </select>";
                            
                                    if (v == 'true'){
                                        s[4].innerHTML = "<select name='verify'> <option value='true' selected='' >Activated</option> <option value='false'>Deactivated</option> </select>";
                                        }
                                    else
                                        s[4].innerHTML = "<select name='verify'> <option value='true' >Activated</option> <option value='false' selected=''>Deactivated</option> </select>";

                                }
                                    set++;
                            }
                        </script>
                        </td>
                    </tr>
                    
                    
                    <% 
                        if (userNumber%50==0){
                            pagels.add(userNumber);
                        }
                    
                    %>
                    
                    </c:forEach>
                    
                    <%
                            if (pagels.get(pagels.size()-1) != userNumber){
                                pagels.add(userNumber);
                            }
                    %>
                    
<!--                    <script type="text/javascript">
                    
                        function sortRole(){
                            
//                            // hidden all user
//                                for (var j = 0; j < <%= pagels.get(pagels.size()-1) %>; j++) {
//                                    document.getElementById('user'+(j+1)).style.setProperty('display','none');
//                                }  
//                            //
                            
                            <%! List<User> tempUser = new ArrayList<User>(); %> // store user list after sort by role
                        <% List<User> userls = (List<User>) request.getAttribute("user");
                           
                           final int uSize = userls.size();
                           
                            for (int i = 0; i < uSize; i++) {
                                
                                for (int j = 0; j < userls.size(); j++) {
                                        if (userls.get(j).getRole().getRoleName().equals("admin")){
                                            tempUser.add(userls.get(j));
                                            userls.remove(j);
                                        }
                                    }
                                
                                for (int j = 0; j < userls.size(); j++) {
                                        if (userls.get(j).getRole().getRoleName().equals("expert")){
                                            tempUser.add(userls.get(j));
                                            userls.remove(j);
                                        }
                                    }
                                
                                for (int j = 0; j < userls.size(); j++) {
                                        if (userls.get(j).getRole().getRoleName().equals("customer")){
                                            tempUser.add(userls.get(j));
                                            userls.remove(j);
                                        }
                                    }
                            }
                            
                            request.setAttribute("tempUser", tempUser);
                        %>  // for sort Role admin - expert - customer                        
                                
                                document.getElementById('tbody').innerHTML="";
                        }
                        
                    
                    </script>-->
                </tbody>
                
                
                
                
            </table>
                <!--check-->
                
                <!--<p id="check">Yolo</p>-->
                <!--end check-->
                
            <div class="clearfix">
<!--                <div class="hint-text">Showing <b>50</b> out of <b><%=pagels.get(pagels.size()-1)%></b> entries</div>-->
                <ul class="pagination" id="pagination">
                    <li class="page-item"><a href="#pagination" class="page-link" onclick="previous()">Previous</a></li>
                    <% for (int i = 0; i < pagels.size(); i++) {
                            %>
                    
                    <li id="page-item<%=(i+1)%>"><a href="#page-item<%=(i+1)%>" class="page-link" onclick="paginate(<%=(i+1)%>, <%=pagels.get(i)%> )"><%=(i+1)%></a></li> <!--Ex: page 1,2,3,4,5... -->
                    
                    <%
                        }
                    %>
                    
                    <li class="page-item"><a href="#pagination" class="page-link" onclick="next()">Next</a></li>
                    
                </ul>
                    
                    
            </div>
                    
                    <script type="text/javascript">
                        
                        // hidden all user
                        for (var j = 0; j < <%= pagels.get(pagels.size()-1) %>; j++) {
                            document.getElementById('user'+(j+1)).style.setProperty('display','none');
                        }   
                        
                        let x = document.getElementById('page-item1').getElementsByTagName('a');
                        x[0].style.setProperty('background', '#299be4');
                        x[0].style.setProperty('color', 'black');
                        
                                    for (var j = 0; j < <%= pagels.get(0)%>; j++) {
                                        document.getElementById('user'+(j+1)).style.removeProperty('display');
                                        document.getElementById('user'+(j+1)).setAttribute('visibility','visible');
                                        
                                    }
                                
                        // Previous and Next (Paging)
                        const pn = [0,0]; //  [k,h] these variables store h(pagels.get(i)) using for previous or next (paging)
                        //
                        
                        function paginate(k,h){ // k = order number, h = pagels.get(i) -> return all results of list
                            
                                x[0].style.removeProperty('background');
                                x[0].style.removeProperty('color');
                                
                                x = document.getElementById('page-item'+k).getElementsByTagName('a');
                                x[0].style.setProperty('background', '#299be4');
                                x[0].style.setProperty('color', 'black');
                             
                            if (x[0].style.getPropertyValue('background') != null){
                                // hidden all user
                                for (var j = 0; j < <%= pagels.get(pagels.size()-1) %>; j++) {
                                    document.getElementById('user'+(j+1)).style.setProperty('display','none');
                                }  
                                // end hidden --
                                
                                if ((k-1) == 0)
                                    for (var j = 0; j < <%= pagels.get(0) %>; j++) {
                                        document.getElementById('user'+(j+1)).style.removeProperty('display');
                                        document.getElementById('user'+(j+1)).setAttribute('visibility','visible');
                                    }
                                else
                                    for (var j = h-50; j < h; j++) {
                                        document.getElementById('user'+(j+1)).style.removeProperty('display');
                                        document.getElementById('user'+(j+1)).setAttribute('visibility','visible');
                                    }
                            }
                            
                            pn[0] = k;
                            pn[1] = h
                        }
                        
                        // Previous
                        function previous(){    // k = pn[0]-1, h = pn[1]-50
                            let k = pn[0]-1, h = pn[1]-50;
                                if (k == 0) {
                                    k = pn[0];
                                    h = pn[1];
                                }
                            pn[0] = k;
                            pn[1] = h;
                            
                            x[0].style.removeProperty('background');
                            x[0].style.removeProperty('color');
                                
                                x = document.getElementById('page-item'+k).getElementsByTagName('a');
                                x[0].style.setProperty('background', '#299be4');
                                x[0].style.setProperty('color', 'black');
                                
                        if (x[0].style.getPropertyValue('background') != null){
                                // hidden all user
                                for (var j = 0; j < <%= pagels.get(pagels.size()-1) %>; j++) {
                                    document.getElementById('user'+(j+1)).style.setProperty('display','none');
                                }  
                                // end hidden --
                                
                                if ((k-1) == 0)
                                    for (var j = 0; j < <%= pagels.get(0) %>; j++) {
                                        document.getElementById('user'+(j+1)).style.removeProperty('display');
                                        document.getElementById('user'+(j+1)).setAttribute('visibility','visible');
                                    }
                                else
                                    for (var j = h-50; j < h; j++) {
                                        document.getElementById('user'+(j+1)).style.removeProperty('display');
                                        document.getElementById('user'+(j+1)).setAttribute('visibility','visible');
                                    }
                            }
                        }
                        
                        // Next
                        function next(){    // k = pn[0]+1, h = pn[1]+50
                            let k = pn[0]+1, h = pn[1]+50;
                            if (k > <%= pagels.size() %>) {
                                    k = pn[0];
                                    h = pn[1];
                                }
                            pn[0] = k;
                            pn[1] = h;
                            
                            x[0].style.removeProperty('background');
                            x[0].style.removeProperty('color');
                                
                                x = document.getElementById('page-item'+k).getElementsByTagName('a');
                                x[0].style.setProperty('background', '#299be4');
                                x[0].style.setProperty('color', 'black');
                                
                        if (x[0].style.getPropertyValue('background') != null){
                                // hidden all user
                                for (var j = 0; j < <%= pagels.get(pagels.size()-1) %>; j++) {
                                    document.getElementById('user'+(j+1)).style.setProperty('display','none');
                                }  
                                // end hidden --
                                
                                if ((k-1) == 0 || (k-1) == -1)
                                    for (var j = 0; j < <%= pagels.get(0) %>; j++) {
                                        document.getElementById('user'+(j+1)).style.removeProperty('display');
                                        document.getElementById('user'+(j+1)).setAttribute('visibility','visible');
                                    }
                                else
                                    for (var j = h-50; j < h; j++) {
                                        document.getElementById('user'+(j+1)).style.removeProperty('display');
                                        document.getElementById('user'+(j+1)).setAttribute('visibility','visible');
                                    }
                            }
                        }
                        
                        
                    </script>
        </div>
    </div>
</div>     
</body>
</html>
