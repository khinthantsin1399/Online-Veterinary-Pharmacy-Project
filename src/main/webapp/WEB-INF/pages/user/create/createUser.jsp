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
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css">
  <link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
  
</head>
<body>
<div class="wrapper">
  <div class="container">
  
     <div class="sec-form">
       
          <h2 class="cmn-ttl">User
            Registration Form</h2>
          <c:url var="addAction" value="/user/create/confirm"></c:url>
          <form:form action="${addAction}" modelAttribute="userForm"
            method="POST" class="form clearfix">
            <c:if test="${errorMsg != null }">
              <div class="text-center">
                <strong class="text-danger">${errorMsg }</strong>
              </div>
            </c:if>
           
              <label for="name" class="required">User Name</label>
              <form:input type="text" class="input" id="username"
                placeholder="Enter user name" name="username"
                path="username" value="${ userForm.username }" />
              <form:errors path="username"  class="text-danger error"/>
            
           
              <label for="email" class="required">Email</label>
              <form:input type="text" class="input" id="email"
                placeholder="Enter email" name="email" path="email"
                value="${ userForm.email }" />
              <form:errors path="email"  class="text-danger error"/>
           
           
              <label for="password" class="required">Password</label>
              <form:input type="password" class="input"
                id="password" placeholder="Enter password"
                name="password" path="password" />
              <form:errors path="password" class="text-danger error" />
           
            <c:if test="${LOGIN_USER.type == 1 }">
          
                <label for="type" class="required">Type</label>
                <form:select path="type" id="type" class="form-select input"
                  aria-label="Default select example"
                  value="${ userForm.type }">
                  <c:if test="${ userForm.type == null }">
                    <option value="0">PHARMACIST</option>
                    <option value="1">Admin</option>
                  </c:if>
                  <c:if test="${ userForm.type == 0 }">
                    <option value="0" selected>PHARMACIST</option>
                    <option value="1">Admin</option>
                  </c:if>
                  <c:if test="${ userForm.type == 1 }">
                    <option value="0">PHARMACIST</option>
                    <option value="1" selected>Admin</option>
                  </c:if>
                </form:select>
                <form:errors path="type" class="text-danger error" />
             
            </c:if>
            <c:if test="${LOGIN_USER.type == null }">
              <form:input type="hidden" path="type" value="0"
                name="type" />
            </c:if>
           
              <label for="phone" class="required">Phone No:</label>
              <form:input type="text" class="input" id="phone"
                placeholder="Enter phone no" name="phone" path="phone"
                value="${ userForm.phone }" />
              <form:errors path="phone"  class="text-danger error"/>
           
           
              <label for="address" class="required">Address :</label>
              <form:textarea type="text" class="input"
                id="address" placeholder="Enter address" name="address"
                path="address" value="${ userForm.address }" />
              <form:errors path="address"  class="text-danger error" />
            
            <div class="clearfix">
              <button type="submit" class="cmn-btn">Confirm</button>
              <button type="submit" name="cancel"
                class="cmn-btn" value="Cancel">Back</button>
            </div>
          </form:form>
        </div>
     </div>
   </div>
 
</body>
</html>