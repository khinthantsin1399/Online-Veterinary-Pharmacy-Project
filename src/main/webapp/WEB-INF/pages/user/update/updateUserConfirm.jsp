<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
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
        <h2 class="cmn-ttl">Edit User Confirm Form</h2>
        <form:form method="post"
          action="${pageContext.request.contextPath}/user/update"
          modelAttribute="updateUserConfirmForm" class="form clearfix">
          <input type=hidden name="id"
            value="${updateUserConfirmForm.id }" />

          <label for="name">User Name</label>
          <span class="input">${ updateUserConfirmForm.username }</span>
          <form:input type="hidden" class="form-control" id="username"
            placeholder="Enter user name" name="username"
            path="username" value="${ updateUserConfirmForm.username }" />

          <label for="email">Email</label>
          <span class="input"> ${ updateUserConfirmForm.email }</span>
          <form:input type="hidden" class="form-control" id="email"
            placeholder="Enter email" name="email" path="email"
            value="${ updateUserConfirmForm.email }" />

          <form:input type="hidden" class="form-control" id="password"
            placeholder="Enter password" name="password" path="password"
            value="${ updateUserConfirmForm.password }" />

          <c:if test="${ updateUserConfirmForm.type == 1}">
            <label for="type">Type </label>
            <span class="input"> Admin</span>
          </c:if>
          <c:if test="${ updateUserConfirmForm.type == 0}">
            <label for="type">Type </label>
            <span class="input"> PHARMACIST</span>
          </c:if>
          <c:if test="${ updateUserConfirmForm.type == 2}">
            <label for="type">Type </label>
            <span class="input"> USER</span>
          </c:if>
          <form:input type="hidden" class="form-control" id="type"
            name="type" path="type"
            value="${ updateUserConfirmForm.type }" />

          <label for="phone">Phone No</label>
          <span class="input">${ updateUserConfirmForm.phone }</span>
          <form:input type="hidden" class="form-control" id="phone"
            placeholder="Enter phone no" name="phone" path="phone"
            value="${ updateUserConfirmForm.phone }" />

          <label for="address">Address</label>
          <span class="input">${ updateUserConfirmForm.address }</span>
          <form:input type="hidden" class="form-control" id="address"
            placeholder="Enter address" name="address" path="address"
            value="${ updateUserConfirmForm.address }" />

          <div class="clearfix">
            <button type="submit" name="updateUser" class="cmn-btn">Update</button>
            <button type="submit" name="cancel" class="cmn-btn"
              value="Cancel">Back</button>
          </div>
        </form:form>
      </div>
    </div>
  </div>
</body>
</html>