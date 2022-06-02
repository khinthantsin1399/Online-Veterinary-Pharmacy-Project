<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
  uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-info">
  <div class="container">
    <button class="navbar-toggler" type="button" data-toggle="collapse"
      data-target="#navbarTogglerDemo01"
      aria-controls="navbarTogglerDemo01" aria-expanded="false"
      aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="#"> <i
      class="fa-solid fa-shield-dog" style="font-size: 2em;"></i>
    </a>
    <security:authorize
      access="hasAnyRole('PHARMACIST', 'ADMIN', 'USER')"
      var="isLoggedin" />
    <c:choose>
      <c:when test="${isLoggedin}">
        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
          <c:choose>
            <c:when test="${LOGIN_USER.type == '2'}">
              <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item active"><a class="nav-link"
                  href="#">Home </a></li>
              </ul>
              <ul class="navbar-nav ">
                <!-- PROFILE DROPDOWN - scrolling off the page to the right -->
                <li class="nav-item dropdown"><a href="#"
                  class="nav-link dropdown-toggle" id="navDropDownLink"
                  data-toggle="dropdown" aria-haspopup="true"
                  aria-expanded="false"><i class="fa-solid fa-user"></i>
                    ${LOGIN_USER.username} </a>
                  <div class="dropdown-menu"
                    aria-labelledby="navDropDownLink">
                    <c:url value="/profile/detail?id=${LOGIN_USER.id}"
                      var="profileDetail" />
                    <a class="dropdown-item" href="${profileDetail}">Profile</a>
                    <div class="dropdown-divider"></div>
                    <c:url value="/logout" var="logoutUrl" />
                    <a class="dropdown-item" href="${logoutUrl}">Logout</a>
                  </div></li>
              </ul>
            </c:when>
            <c:otherwise>
              <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item active"><a class="nav-link"
                  href="#">Home </a></li>
                <li class="nav-item active"><a class="nav-link"
                  href="${pageContext.request.contextPath}/user/list">Users
                </a></li>
                <li class="nav-item dropdown active"><a href="#"
                  class="nav-link dropdown-toggle" id="navDropDownLink"
                  data-toggle="dropdown" aria-haspopup="true"
                  aria-expanded="false"> Medicines </a>
                  <div class="dropdown-menu"
                    aria-labelledby="navDropDownLink">
                    <c:url value="/medicineList" var="medicineList" />
                    <a class="dropdown-item" href="${medicineList}">Medicine
                      List</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item"
                      href="${pageContext.request.contextPath}/categoryList">Category
                      List</a>
                  </div></li>
                <li class="nav-item active"><a class="nav-link"
                  href="${pageContext.request.contextPath}/orderList">Order
                    Reports </a></li>
              </ul>
              <!-- <form id="searchForm" class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search"
                  placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0"
                  type="submit">Search</button>
              </form> -->
              <ul class="navbar-nav ">
                <!-- PROFILE DROPDOWN - scrolling off the page to the right -->
                <li class="nav-item dropdown active"><a href="#"
                  class="nav-link dropdown-toggle" id="navDropDownLink"
                  data-toggle="dropdown" aria-haspopup="true"
                  aria-expanded="false"><i class="fa-solid fa-user"></i>
                    ${LOGIN_USER.username} </a>
                  <div class="dropdown-menu"
                    aria-labelledby="navDropDownLink">
                    <c:url value="/profile/detail?id=${LOGIN_USER.id}"
                      var="profileDetail" />
                    <a class="dropdown-item" href="${profileDetail}">Profile</a>
                    <div class="dropdown-divider"></div>
                    <c:url value="/logout" var="logoutUrl" />
                    <a class="dropdown-item" href="${logoutUrl}">Logout</a>
                  </div></li>
              </ul>
            </c:otherwise>
          </c:choose>
        </div>
      </c:when>
      <c:otherwise>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
          <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
          </ul>
          <ul class="navbar-nav ">
            <!-- PROFILE DROPDOWN - scrolling off the page to the right -->
            <li class="nav-item dropdown active"><a href="#"
              class="nav-link dropdown-toggle" id="navDropDownLink"
              data-toggle="dropdown" aria-haspopup="true"
              aria-expanded="false"><i
                class="fa-solid fa-list-check"></i>Settings</a>
              <div class="dropdown-menu"
                aria-labelledby="navDropDownLink">
                <a class="dropdown-item" href="#">Login</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Register</a>
              </div></li>
          </ul>
        </div>
      </c:otherwise>
    </c:choose>
  </div>
</nav>