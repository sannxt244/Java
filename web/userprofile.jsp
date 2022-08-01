<%-- 
    Document   : userprofile
    Created on : Jun 1, 2022, 10:54:35 AM
    Author     : Minh Lee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        
        <style>
            body{margin-top:20px;
background-color:#f2f6fc;
color:#69707a;
}
.img-account-profile {
    height: 10rem;
}
.rounded-circle {
    border-radius: 50% !important;
}
.card {
    box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
}
.card .card-header {
    font-weight: 500;
}
.card-header:first-child {
    border-radius: 0.35rem 0.35rem 0 0;
}
.card-header {
    padding: 1rem 1.35rem;
    margin-bottom: 0;
    background-color: rgba(33, 40, 50, 0.03);
    border-bottom: 1px solid rgba(33, 40, 50, 0.125);
}
.form-control, .dataTable-input {
    display: block;
    width: 100%;
    padding: 0.875rem 1.125rem;
    font-size: 0.875rem;
    font-weight: 400;
    line-height: 1;
    color: #69707a;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #c5ccd6;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    border-radius: 0.35rem;
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.nav-borders .nav-link.active {
    color: #0061f2;
    border-bottom-color: #0061f2;
}
.nav-borders .nav-link {
    color: #69707a;
    border-bottom-width: 0.125rem;
    border-bottom-style: solid;
    border-bottom-color: transparent;
    padding-top: 0.5rem;
    padding-bottom: 0.5rem;
    padding-left: 0;
    padding-right: 0;
    margin-left: 1rem;
    margin-right: 1rem;
}

.btn-danger-soft {
    color: #000;
    background-color: #f1e0e3;
    border-color: #f1e0e3;
}
        </style>
        
    </head>
    <body>
        <div class="container-xl px-4 mt-4">
            <!-- Account page navigation-->
            
            <c:set value="${requestScope.user}" var="u"/>
            
            
            <nav class="nav nav-borders">
                <a class="nav-link ms-0" href="home">Home</a>
                <a class="nav-link active ms-0" href="profile?userName=${u.userName}" target="__blank">Profile</a>
            </nav>
            <hr class="mt-0 mb-4">
            <div class="row">
                <div class="col-xl-4">
                    <!-- Profile picture card-->
                    <div class="card mb-4 mb-xl-0">
                        <div class="card-header">Profile Picture</div>
                        <div class="card-body text-center">
                            <!-- Profile picture image-->
                            <img class="img-account-profile rounded-circle mb-2" src="${u.avatar}" alt="avatar" style="max-width:200px">
                             <!--Profile picture help block-->
                            <div class="small font-italic text-muted mb-4">Image Address link</div>
                             Profile picture upload button<br/>
                             

<!--                            <button class="btn btn-primary" type="button">Upload new image</button>-->
                            <form action="uploadavatar" method="post" enctype="multipart/form-data">
                                <input type="text" name="userName" value="${u.userName}" style="display: none;" />
                                <input type="text" id="myFile" name="imgURL">
                                <input type="submit">
                            </form>
                                
                        </div>
                    </div>
                </div>
                <div class="col-xl-8">
                    <!-- Account details card-->
                    <div class="card mb-4">
                        <div class="card-header">Account Details</div>
                            
                        
                        <div class="card-body">
                            <form action="profile" method="post">
                                <!-- Form Group (username)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputUsername">Username</label>
                                    <input class="form-control" id="inputUsername" name="Username" type="text" placeholder="Enter your username" value="${u.userName}">
                                </div>
                                <!-- Form Row-->
                                <div class="row gx-3 mb-3">
<!--                                     Form Group (first name)
-->                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputFullName">Full Name</label>
                                        <input class="form-control" id="inputFullName" name="Fullname" type="text" placeholder="Enter your full name" value="${u.fullName}" required="">
                                        <p style="color: red;">${requestScope.messFullname}</p>
                                    </div><!--
                                     Form Group (last name)
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputLastName">Last name</label>
                                        <input class="form-control" id="inputLastName" type="text" placeholder="Enter your last name" value="Luna">
                                    </div>-->
                                </div>
                                <!-- Form Row        -->
                                <div class="row gx-3 mb-3">
                                    <!-- Form Group (organization name)-->
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputGender">Gender</label>
                                        <select class="form-control" id="inputGender" name="Gender" type="text" placeholder="Enter your gender">
                                            <option value="false" <c:if test="${u.gender == false}">selected=""</c:if>>Male</option>
                                            <option value="true" <c:if test="${u.gender == true}">selected=""</c:if>>Female</option>
                                        </select>
                                        
                                    </div>
                                    <!-- Form Group (location)-->
<!--                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputLocation">Location</label>
                                        <input class="form-control" id="inputLocation" type="text" placeholder="Enter your location" value="San Francisco, CA">
                                    </div> -->
                                </div>
                                <!-- Form Group (email address)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputEmailAddress">Email address</label>
                                    <input class="form-control" id="inputEmailAddress" name="Email" type="email" placeholder="Enter your email address" value="${u.email}">
                                    <p style="color: red;">${requestScope.messEmail}</p>
                                </div>
                                <!-- Form Row-->
                                <div class="row gx-3 mb-3">
                                    <!-- Form Group (phone number)-->
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputPhone">Phone number</label>
                                        <input class="form-control" id="inputPhone" name="Phone" type="tel" placeholder="Enter your phone number" value="${u.phoneNumber}" >
                                        <p style="color: red;">${requestScope.messPhone}</p>
                                    </div>

                                </div>
                                <!-- Save changes button-->
                                <button id="change" class="btn btn-primary" type="button" onclick="edit();">Edit</button>
                                
                                
                            </form>
                            <form action="changePassword" method="post">
                                <button id="change password" class="btn btn-primary" type="button" onclick="changepassword();">Change Password</button>

                                <div id="password-area" class="col-md-6" style="margin-top: 20px;" >
                                    <input name="username" type="text" value="${u.userName}" hidden="">
                                    <label class="small mb-1" for="inputPassWord">Old Password</label>
                                    <input class="form-control" id="inputPassword" name="op" type="password"  placeholder="Enter your old Password" required="">
                                    <p style="color: red">${requestScope.message1}</p>
                                    
                                    <label class="small mb-1" for="inputPassWord">New Password</label>
                                    <input class="form-control" id="inputPassword" name="np" type="password" pattern=".{8,}" title="8 or more charaters" placeholder="Enter your new Password" required="">
                                    <p style="color: red">${requestScope.message2}</p>
                                </div>
                            </form>
                                    <!--Edit-->
                                    <script>
                                        
                                                document.getElementById('inputUsername').setAttribute('readonly', true);
                                                document.getElementById('inputFullName').setAttribute('readonly', true);
                                                document.getElementById('inputGender').setAttribute('disabled', true);
                                                document.getElementById('inputEmailAddress').setAttribute('readonly', true);
                                                document.getElementById('inputPhone').setAttribute('readonly', true);
                                        
    
                                        var x = 1;
                                        
                                        function edit(){
                                            
                                            
                                            if (x==1){
                                                alert('Now, You can edit your profile');
                                                x=2;
                                                document.getElementById('change').innerHTML = 'Save Changes';
                                                
                                                document.getElementById('inputFullName').removeAttribute('readonly');
                                                document.getElementById('inputGender').removeAttribute('disabled');
                                                document.getElementById('inputEmailAddress').removeAttribute('readonly');
                                                document.getElementById('inputPhone').removeAttribute('readonly');
                                                
                                            }
                                            else {
                                                document.getElementById('change').setAttribute('type','submit');
                                            }
                                        }
                                        
                                        
                                        // Change Password
                                        <c:if test="${requestScope.message1 == null && requestScope.message2 == null}"> 
                                            document.getElementById('password-area').style.visibility = "hidden";
                                        </c:if> 
                                            
                                        <c:if test="${requestScope.message1 != null || requestScope.message2 != null}"> 
                                            x = 2;
                                        </c:if> 
                                        
                                        function changepassword() {
                                            if (x==1){
//                                                alert(x);
                                                <c:if test="${requestScope.message1 == null && requestScope.message2 == null}">
                                                    document.getElementById('password-area').style.visibility = "visible";
                                                </c:if>
                                                    
                                                x=2;
                                            }
                                            else {
                                                document.getElementById('change password').setAttribute('type','submit');
                                                alert('Password has been changed !');
                                            }
                                                
                                        }
                                    </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
