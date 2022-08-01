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
	<link rel="stylesheet" href="css/bootstrap.min.css"/>
	<link rel="stylesheet" href="css/font-awesome.min.css"/>
	<link rel="stylesheet" href="css/owl.carousel.css"/>
	<link rel="stylesheet" href="css/style.css"/>


	<!--[if lt IE 9]>
	  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

</head>
<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header section -->
        <%@include file="header.jsp" %>
	<!-- Header section end -->


	<!-- single course section -->
	<section class="single-course spad pb-0">
		<div class="container">
			<div class="course-meta-area">
				<div class="row">
					<div class="col-lg-10 offset-lg-1">
						<h3>${course.name}</h3>
						<div class="course-metas">
							<div class="course-meta">
								<div class="course-author">
									<div class="ca-pic set-bg" data-setbg="img/authors/2.jpg"></div>
									<h6>Teacher</h6>
									<p>${course.created_by}</p>
								</div>
							</div>
							<div class="course-meta">
								<div class="cm-info">
									<h6>Category</h6>
									<p>${category.categoryName}</p>
								</div>
							</div>
							<div class="course-meta">
								<div class="cm-info">
									<h6>Students</h6>
									<p>120 Registered Students</p>
								</div>
							</div>
							<div class="course-meta">
								<div class="cm-info">
									<h6>Reviews</h6>
									<p>2 Reviews <span class="rating">
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star is-fade"></i>
									</span></p>
								</div>
							</div>
						</div>
                                                    <a href="#" class="site-btn price-btn">Price: $${pricePackage.priceSale} <del>${pricePackage.price}</del></a>
                                                    <a href="courseregister?id=${course.id}" style="min-width: 200px;" class="site-btn buy-btn">Register</a><br/>
					</div>
				</div>
			</div>
			<img src="img/courses/single.jpg" alt="" class="course-preview">
			<div class="row">
				<div class="col-lg-10 offset-lg-1 course-list">
					<div class="cl-item">
						<h4>Course Description</h4>
						<p>${course.description}</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- single course section end -->


	<!-- Page -->
	<section class="realated-courses spad">
		<div class="course-warp">
                    
			<h2 class="rc-title">Featured Courses</h2>
			<div class="rc-slider owl-carousel">
				<!-- course -->
				<div class="course-item">
					<div class="course-thumb set-bg" data-setbg="img/courses/1.jpg">
						<div class="price">Price: $15</div>
					</div>
					<div class="course-info">
						<div class="course-text">
							<h5>Art & Crafts</h5>
							<p>Lorem ipsum dolor sit amet, consectetur</p>
							<div class="students">120 Students</div>
						</div>
						<div class="course-author">
							<div class="ca-pic set-bg" data-setbg="img/authors/1.jpg"></div>
							<p>William Parker, <span>Developer</span></p>
						</div>
					</div>
				</div>
				<!-- course -->
				<div class="course-item">
					<div class="course-thumb set-bg" data-setbg="img/courses/2.jpg">
						<div class="price">Price: $15</div>
					</div>
					<div class="course-info">
						<div class="course-text">
							<h5>IT Development</h5>
							<p>Lorem ipsum dolor sit amet, consectetur</p>
							<div class="students">120 Students</div>
						</div>
						<div class="course-author">
							<div class="ca-pic set-bg" data-setbg="img/authors/2.jpg"></div>
							<p>William Parker, <span>Developer</span></p>
						</div>
					</div>
				</div>
				<!-- course -->
				<div class="course-item">
					<div class="course-thumb set-bg" data-setbg="img/courses/3.jpg">
						<div class="price">Price: $15</div>
					</div>
					<div class="course-info">
						<div class="course-text">
							<h5>Graphic Design</h5>
							<p>Lorem ipsum dolor sit amet, consectetur</p>
							<div class="students">120 Students</div>
						</div>
						<div class="course-author">
							<div class="ca-pic set-bg" data-setbg="img/authors/3.jpg"></div>
							<p>William Parker, <span>Developer</span></p>
						</div>
					</div>
				</div>
				<!-- course -->
				<div class="course-item">
					<div class="course-thumb set-bg" data-setbg="img/courses/4.jpg">
						<div class="price">Price: $15</div>
					</div>
					<div class="course-info">
						<div class="course-text">
							<h5>IT Development</h5>
							<p>Lorem ipsum dolor sit amet, consectetur</p>
							<div class="students">120 Students</div>
						</div>
						<div class="course-author">
							<div class="ca-pic set-bg" data-setbg="img/authors/4.jpg"></div>
							<p>William Parker, <span>Developer</span></p>
						</div>
					</div>
				</div>
				<!-- course -->
				<div class="course-item">
					<div class="course-thumb set-bg" data-setbg="img/courses/5.jpg">
						<div class="price">Price: $15</div>
					</div>
					<div class="course-info">
						<div class="course-text">
							<h5>IT Development</h5>
							<p>Lorem ipsum dolor sit amet, consectetur</p>
							<div class="students">120 Students</div>
						</div>
						<div class="course-author">
							<div class="ca-pic set-bg" data-setbg="img/authors/5.jpg"></div>
							<p>William Parker, <span>Developer</span></p>
						</div>
					</div>
				</div>
				<!-- course -->
			</div>
		</div>
	</section>
	<!-- Page end -->


	<!-- banner section -->
	<section class="banner-section spad">
		<div class="container">
			<div class="section-title mb-0 pb-2">
				<h2>Join Our Community Now!</h2>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada lorem maximus mauris scelerisque, at rutrum nulla dictum. Ut ac ligula sapien. Suspendisse cursus faucibus finibus.</p>
			</div>
			<div class="text-center pt-5">
				<a href="#" class="site-btn">Register Now</a>
			</div>
		</div>
	</section>
	<!-- banner section end -->


	<!-- footer section -->
        <%@include file="footer.jsp" %>
	<!-- footer section end -->


	<!--====== Javascripts & Jquery ======-->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/mixitup.min.js"></script>
	<script src="js/circle-progress.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>
</body>
</html>