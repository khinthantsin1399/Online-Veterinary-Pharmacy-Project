<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE nav PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<nav class="navbar"> <security:authorize
	access="hasAnyRole('PHARMACIST', 'ADMIN', 'USER')" var="isLoggedin" />
<!-- NAVIGATION MENUS --> <c:choose>
	<c:when test="${isLoggedin}">
		<c:choose>
			<c:when test="${LOGIN_USER.type == '2'}">
				<ul class="nav-links">
					<div class="logo">
						<i class="fa-solid fa-shield-dog"></i>
					</div>
					<!-- USING CHECKBOX HACK -->
					<input type="checkbox" id="checkbox_toggle" />
					<label for="checkbox_toggle" class="hamburger">&#9776;</label>
					<div class="menu">
						<li><a href="${pageContext.request.contextPath}/userMedicineList">Home</a></li>
                        <%-- <li><a href="${pageContext.request.contextPath}/userMedicineList">Medicine List</a></li> --%>
                        <li><a href="${pageContext.request.contextPath}/orderList">Order History</a></li>
						<li class="services menu-hide"><a class="profileDropUserbtn"
							onclick="myProfileUserFunction()"><i class="fa-solid fa-user"></i>
								${LOGIN_USER.username} <i class="fa fa-caret-down"></i></a> <!-- DROPDOWN MENU -->
							<ul class="dropdown-content" id="profileDropUserDown">
								<li><c:url value="/profile/detail?id=${LOGIN_USER.id}"
										var="profileDetail" /><a href="${profileDetail}">Profile</a></li>
								<div class="dropdown-divider"></div>
								<li><c:url value="/logout" var="logoutUrl" /> <a
									href="${logoutUrl}">Logout</a></li>
							</ul></li>
					</div>
				</ul>
				<ul class="nav-profile">
					<div class="menu">
						<li class="services"><a class="profileDropbtn"
							onclick="myProfileFunction()"><i class="fa-solid fa-user"></i>
								${LOGIN_USER.username} <i class="fa fa-caret-down"></i></a> <!-- DROPDOWN MENU -->
							<ul class="dropdown-content" id="profileDropDown">
								<li><c:url value="/profile/detail?id=${LOGIN_USER.id}"
										var="profileDetail" /><a href="${profileDetail}">Profile</a></li>
								<div class="dropdown-divider"></div>
								<li><c:url value="/logout" var="logoutUrl" /> <a
									href="${logoutUrl}">Logout</a></li>
							</ul></li>
					</div>
				</ul>
			</c:when>
			<c:otherwise>
				<ul class="nav-links">
					<div class="logo">
						<i class="fa-solid fa-shield-dog"></i>
					</div>
					<!-- USING CHECKBOX HACK -->
					<input type="checkbox" id="checkbox_toggle" />
					<label for="checkbox_toggle" class="hamburger">&#9776;</label>
					<div class="menu">
						<li><a href="#">Home</a></li>
						<li><a href="${pageContext.request.contextPath}/user/list">Users</a></li>
						<li class="services"><a class="dropbtn"
							onclick="myFunction()">Medicines <i class="fa fa-caret-down"></i>
						</a>
							<ul class="dropdown-content" id="myDropdown">
								<li><c:url value="/medicineList" var="medicineList" /><a
									href="${medicineList}">Medicine List</a></li>
								<div class="dropdown-divider-login"></div>
								<li><a
									href="${pageContext.request.contextPath}/categoryList">Category
										List</a></li>
							</ul></li>
						<li><a href="${pageContext.request.contextPath}/orderList">Order
								Reports</a></li>
						<li class="services hide-hamburger"><a
							class="profileHideDropbtn" onclick="myProfileHideFunction()"><i
								class="fa-solid fa-user"></i> ${LOGIN_USER.username} <i
								class="fa fa-caret-down"></i></a>
							<ul class="dropdown-content" id="profileHideDropDown">
								<li><c:url value="/profile/detail?id=${LOGIN_USER.id}"
										var="profileDetail" /><a href="${profileDetail}">Profile</a></li>
								<div class="dropdown-divider-login"></div>
								<li><c:url value="/logout" var="logoutUrl" /> <a
									href="${logoutUrl}">Logout</a></li>
							</ul></li>
					</div>
				</ul>
				<ul class="nav-profile">
					<div class="menu">
						<li class="services"><a class="profileDropbtn"
							onclick="myProfileFunction()"><i class="fa-solid fa-user"></i>
								${LOGIN_USER.username} <i class="fa fa-caret-down"></i></a>
							<ul class="dropdown-content" id="profileDropDown">
								<li><c:url value="/profile/detail?id=${LOGIN_USER.id}"
										var="profileDetail" /><a href="${profileDetail}">Profile</a></li>
								<div class="dropdown-divider-login"></div>
								<li><c:url value="/logout" var="logoutUrl" /> <a
									href="${logoutUrl}">Logout</a></li>
							</ul></li>
					</div>
				</ul>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<ul class="nav-links">
			<div class="logo">
				<i class="fa-solid fa-shield-dog"></i>
			</div>
			<!-- USING CHECKBOX HACK -->
			<input type="checkbox" id="checkbox_toggle" />
			<label for="checkbox_toggle" class="hamburger">&#9776;</label>
			<div class="menu menu-hide">
				<li><a href="/">Login</a></li>
				<li><a href="/">Register</a></li>
			</div>
		</ul>
		<ul class="nav-profile">
			<div class="menu">
				<li class="services"><a class="profileDropbtn"
					onclick="myProfileFunction()"><i class="fa-solid fa-list-check"></i>
						Settings <i class="fa fa-caret-down"></i></a>
					<ul class="dropdown-content" id="profileDropDown">
						<li><c:url value="/login" var="login" /><a href="${login }">Login</a></li>
						<div class="dropdown-divider-login"></div>
						<li><c:url value="/user/register" var="register" /><a
							href="${register}">Register</a></li>
					</ul></li>
			</div>
		</ul>
	</c:otherwise>
</c:choose> </nav>

<script>
	/* When the user clicks on the button, 
	toggle between hiding and showing the dropdown content */
	function myFunction() {
		document.getElementById("myDropdown").classList.toggle("show");
	}

	function myProfileFunction() {
		document.getElementById("profileDropDown").classList
				.toggle("profileShow");
	}

	function myProfileHideFunction() {
		document.getElementById("profileHideDropDown").classList
				.toggle("profileHideShow");
	}

	function myProfileUserFunction() {
		document.getElementById("profileDropUserDown").classList
				.toggle("profileUserShow");
	}
	// Close the dropdown if the user clicks outside of it
	window.onclick = function(e) {
		if (!e.target.matches('.dropbtn')) {
			var myDropdown = document.getElementById("myDropdown");
			if (myDropdown.classList.contains('show')) {
				myDropdown.classList.remove('show');
			}
		}
		if (!e.target.matches('.profileDropbtn')) {
			var profileDropDown = document.getElementById("profileDropDown");
			if (profileDropDown.classList.contains('profileShow')) {
				profileDropDown.classList.remove('profileShow');
			}
		}
		if (!e.target.matches('.profileHideDropbtn')) {
			var profileHideDropDown = document
					.getElementById("profileHideDropDown");
			if (profileHideDropDown.classList.contains('profileHideShow')) {
				profileHideDropDown.classList.remove('profileHideShow');
			}
		}

		if (!e.target.matches('.profileDropUserbtn')) {
			var profileDropDown = document
					.getElementById("profileDropUserDown");
			if (profileDropDown.classList.contains('profileUserShow')) {
				profileDropDown.classList.remove('profileUserShow');
			}
		}
	}
</script>