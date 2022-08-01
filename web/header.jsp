<style>
    .link {
        text-decoration: none;
        color: white;
    }
</style>
<header style="background-color: #333333; padding-top: 10px;padding-bottom: 10px">
    <div class="d-flex mx-5">

        <div class="col-lg-2 col-md-2 d-flex align-items-center">
            <a class="site-logo" href="home">
                <img src="img/logo.png" alt="">
            </a>
            <div class="nav-switch">
                <i class="fa fa-bars"></i>
            </div>
        </div>
        <div class="col-lg-19 col-md-10 d-flex align-items-center justify-content-between">
            <div class="text-light">
                <ul style="list-style: none; font-size: 20px" class="text-light d-flex">
                    <li class="mx-4"><a class="link" href="home">Home</a></li>
                    <li class="mx-4"><a class="link" href="course">Courses</a></li>
                    <li class="mx-4"><a class="link" href="blog">Blog</a></li>
                    <c:if test="${sessionScope.account.role.id == 1}">
                        <li class="mx-4"><a class="link" href="mycourseServlet">My Courses</a></li>
                    </c:if>
                    <c:if test="${sessionScope.account.role.id == 1}">
                        <li class="mx-4"><a class="link" href="myregistrationsServlet">My Registrations</a></li>
                    </c:if>

                </ul>
            </div>
            <div class="d-flex align-items-center">
                <c:if test="${sessionScope.account == null}">
                    <a href="register" class="link site-btn header-btn" style="margin-right: 20px">Sign Up</a>
                    <a href="login" class="link site-btn header-btn"  style="margin-right: 20px">Login</a>
                </c:if>
                <c:if test="${sessionScope.account != null}">
                    <div style="height: 50px; width: 50px; margin-right: 20px">
                        <img style="border-radius: 100%;object-fit: cover" class="w-100 h-100" src="${sessionScope.account.avatar}"/>
                    </div>
                        <a href="profile?userName=${sessionScope.account.userName}" class="link text-light" style="font-size: 20px; ">${sessionScope.account.userName}</a>
                    <a href="logout" id="logout" style="margin-left: 20px; " class="link site-btn header-btn" onclick="logOut()">Log Out</a>
                </c:if>
            </div>
        </div>
    </div>

    <!--                                            <script type="text/javascript">
                                                    function logOut() {
                                                     
                                                                document.getElementById('logout').href = "logout";
                                                    }
                                                </script>-->
</header>