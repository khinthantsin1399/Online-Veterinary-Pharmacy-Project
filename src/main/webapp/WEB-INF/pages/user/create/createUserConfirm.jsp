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
        <h2 class="cmn-ttl">User Registration Confirm Form</h2>
        <form:form method="post"
          action="${pageContext.request.contextPath}/user/save"
          modelAttribute="userConfirmForm" class="form clearfix">

          <label for="name">User Name</label>
          <span class="input"> ${ userConfirmForm.username }</span>
          <form:input type="hidden" id="username"
            placeholder="Enter user name" name="username"
            path="username" value="${ userConfirmForm.username }" />

          <label for="email">Email</label>
          <span class="input">${ userConfirmForm.email }</span>
          <form:input type="hidden" id="email" placeholder="Enter email"
            name="email" path="email" value="${ userConfirmForm.email }" />

          <form:input type="hidden" id="password"
            placeholder="Enter password" name="password" path="password"
            value="${ userConfirmForm.password }" />

          <c:if test="${ userConfirmForm.type == 1}">
            <label for="type">Type</label>
            <span class="input"> ADMIN</span>
          </c:if>
          <c:if test="${ userConfirmForm.type == 0}">
            <label for="type">Type</label>
            <span class="input"> PHARMACIST</span>
          </c:if>
          <c:if test="${ userConfirmForm.type == 2}">
            <label for="type">Type</label>
            <span class="input"> USER</span>
          </c:if>
          <form:input type="hidden" id="type"
            placeholder="Enter password" name="type" path="type"
            value="${ userConfirmForm.type }" />

          <label for="phone">Phone No</label>
          <span class="input">${ userConfirmForm.phone }</span>
          <form:input type="hidden" id="phone"
            placeholder="Enter phone no" name="phone" path="phone"
            value="${ userConfirmForm.phone }" />

          <label for="address">Address</label>
          <span class="input"> ${ userConfirmForm.address }</span>
          <form:input type="hidden" id="address"
            placeholder="Enter address" name="address" path="address"
            value="${ userConfirmForm.address }" />

          <div class="clearfix">
            <button type="submit" name="addUser" class="cmn-btn">Submit</button>
            <button type="submit" name="cancel" class="cmn-btn"
              value="Cancel">Back</button>
          </div>
        </form:form>
      </div>
    </div>
  </div>

</body>
</html>