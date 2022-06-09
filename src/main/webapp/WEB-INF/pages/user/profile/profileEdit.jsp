<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<!-- CSS only -->
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css">
</head>
<body  style="background:#b2dfdb">
 <div class="wrapper">
		<div class="container">
			<div class="sec-form">
          <h2 class="cmn-ttl">Profile Edit
            Form</h2>
          <c:url var="addAction" value="/profile/update"></c:url>
          <form:form action="${addAction}"
            modelAttribute="profileEditForm" method="POST" class="form clearfix profile-form" >
            <c:if test="${errorMsg != null }">
              <div class="text-center">
                <strong class="text-danger">${errorMsg }</strong>
              </div>
            </c:if>
            <input type=hidden name="id" value="${profileEditForm.id }" />
           
              <label for="name">User Name</label>
              <form:input type="text" class="input" id="username"
                placeholder="Enter user name" name="username"
                path="username" value="${ profileEditForm.username }" />
              <form:errors path="username" class="text-danger user-error" />
          
              <label for="email">Email</label>
              <form:input type="text" class="input" id="email"
                placeholder="Enter email" name="email" path="email"
                value="${ profileEditForm.email }" />
              <form:errors path="email"  class="text-danger user-error" />
           
            <!--   <label for="password">Password</label>--> 
              <form:input type="hidden" class="form-control"
                id="password" placeholder="Enter password"
                name="password" path="password"
                value="${ profileEditForm.password }" />
              <form:errors path="password"  class="text-danger user-error"/>
            
              <label for="type">Type</label>
              <form:select path="type" id="type" class="input"
                aria-label="Default select example"
                value="${ profileEditForm.type }">
                <c:choose>
                  <c:when test="${profileEditForm.type == 1}">
                    <c:if test="${ profileEditForm.type == 0 }">
                      <option value="0" selected>PHARMACIST</option>
                      <option value="1">ADMIN</option>
                      <option value="2">USER</option>
                    </c:if>
                    <c:if test="${ profileEditForm.type == 1 }">
                      <option value="0">PHARMACIST</option>
                      <option value="1" selected>ADMIN</option>
                      <option value="2">USER</option>
                    </c:if>
                    <c:if test="${ profileEditForm.type == 2 }">
                      <option value="0">PHARMACIST</option>
                      <option value="1">ADMIN</option>
                      <option value="2" selected>USER</option>
                    </c:if>
                  </c:when>
                  <c:otherwise>
                    <c:if test="${profileEditForm.type == 0}">
                      <option value="0" selected>PHARMACIST</option>
                    </c:if>
                    <c:if test="${profileEditForm.type == 2}">
                      <option value="2" selected>USER</option>
                    </c:if>
                  </c:otherwise>
                </c:choose>
              </form:select>
              <form:errors path="type"  class="text-danger user-error" />
           
              <label for="phone">Phone No</label>
              <form:input type="text" class="input" id="phone"
                placeholder="Enter phone no" name="phone" path="phone"
                value="${ profileEditForm.phone }" />
              <form:errors path="phone"  class="text-danger user-error" />
           
              <label for="address">Address</label>
              <form:textarea type="text" class="input"
                id="address" placeholder="Enter address" name="address"
                path="address" value="${ profileEditForm.address }" />
              <form:errors path="address"  class="text-danger user-error" />
          <div class="clearfix">
              <button type="submit" name="editProfile"
                class="cmn-btn">Edit</button>
              <button type="submit" name="cancelProfile"
                class="cmn-btn" value="Cancel">Back</button>
            </div>
          </form:form>
        </div>
      </div>
    </div>
 
</body>
</html>