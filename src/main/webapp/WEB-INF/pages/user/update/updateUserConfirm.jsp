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
</head>
<body>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card mt-5">
          <h2 class="text-primary text-center mb-4">Edit User
            Confirm Form</h2>
          <form:form method="post"
            action="${pageContext.request.contextPath}/user/update"
            modelAttribute="updateUserConfirmForm">
            <input type=hidden name="id"
              value="${updateUserConfirmForm.id }" />
            <div class="form-group w-75" style="margin: 0 auto;">
              <label for="name">User Name :</label> ${ updateUserConfirmForm.username }
              <form:input type="hidden" class="form-control"
                id="username" placeholder="Enter user name"
                name="username" path="username"
                value="${ updateUserConfirmForm.username }" />
            </div>
            <div class="form-group w-75" style="margin: 0 auto;">
              <label for="email">Email :</label> ${ updateUserConfirmForm.email }
              <form:input type="hidden" class="form-control" id="email"
                placeholder="Enter email" name="email" path="email"
                value="${ updateUserConfirmForm.email }" />
            </div>
            <div class="form-group w-75" style="margin: 0 auto;">
              <form:input type="hidden" class="form-control"
                id="password" placeholder="Enter password"
                name="password" path="password"
                value="${ updateUserConfirmForm.password }" />
            </div>
            <div class="form-group w-75" style="margin: 0 auto;">
              <c:if test="${ updateUserConfirmForm.type == 1}">
                <label for="type">Type :</label>
            Admin
            </c:if>
              <c:if test="${ updateUserConfirmForm.type == 0}">
                <label for="type">Type :</label>
            PHARMACIST
            </c:if>
              <form:input type="hidden" class="form-control" id="type"
                placeholder="Enter password" name="type" path="type"
                value="${ updateUserConfirmForm.type }" />
            </div>
            <div class="form-group w-75" style="margin: 0 auto;">
              <label for="phone">Phone No:</label> ${ updateUserConfirmForm.phone }
              <form:input type="hidden" class="form-control" id="phone"
                placeholder="Enter phone no" name="phone" path="phone"
                value="${ updateUserConfirmForm.phone }" />
            </div>
            <div class="form-group w-75" style="margin: 0 auto;">
              <label for="address">Address :</label> ${ updateUserConfirmForm.address }
              <form:input type="hidden" class="form-control"
                id="address" placeholder="Enter address" name="address"
                path="address"
                value="${ updateUserConfirmForm.address }" />
            </div>
            <div class="col-md-12 text-center mt-3">
              <button type="submit" name="updateUser"
                class="btn btn-primary align-center">Update</button>
              <button type="submit" name="cancel"
                class="btn btn-secondary" value="Cancel">Back</button>
            </div>
          </form:form>
        </div>
      </div>
    </div>
  </div>
</body>
</html>