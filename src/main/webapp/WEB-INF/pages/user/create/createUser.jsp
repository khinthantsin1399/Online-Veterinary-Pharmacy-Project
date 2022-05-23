<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
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
          <h2 class="text-primary text-center mb-4">User
            Registration Form</h2>
          <c:url var="addAction" value="/createUserConfirm"></c:url>
          <form:form action="${addAction}" modelAttribute="userForm"
            method="POST">
            <c:if test="${errorMsg != null }">
              <div class="text-center">
                <strong class="text-danger">${errorMsg }</strong>
              </div>
            </c:if>
            <div class="form-group w-75" style="margin: 0 auto;">
              <label for="name">User Name :</label>
              <form:input type="text" class="form-control" id="username"
                placeholder="Enter user name" name="username"
                path="username" value="${ userForm.username }" />
              <form:errors path="username" style="color:red;" />
            </div>
            <div class="form-group w-75" style="margin: 0 auto;">
              <label for="email">Email :</label>
              <form:input type="text" class="form-control" id="email"
                placeholder="Enter email" name="email" path="email"
                value="${ userForm.email }" />
              <form:errors path="email" style="color:red;" />
            </div>
            <div class="form-group w-75" style="margin: 0 auto;">
              <label for="password">Password :</label>
              <form:input type="password" class="form-control"
                id="password" placeholder="Enter password"
                name="password" path="password" />
              <form:errors path="password" style="color:red;" />
            </div>
            <div class="form-group w-75" style="margin: 0 auto;">
              <label for="type">Type :</label>
              <form:select path="type" id="type" class="form-select"
                aria-label="Default select example"
                value="${ userForm.type }">
                <c:if test="${ userForm.type == null }">
                  <option value="0">User</option>
                  <option value="1">Admin</option>
                </c:if>
                <c:if test="${ userForm.type == 0 }">
                  <option value="0" selected>User</option>
                  <option value="1">Admin</option>
                </c:if>
                <c:if test="${ userForm.type == 1 }">
                  <option value="0">User</option>
                  <option value="1" selected>Admin</option>
                </c:if>
              </form:select>
              <form:errors path="type" style="color:red;" />
            </div>
            <div class="form-group w-75" style="margin: 0 auto;">
              <label for="phone">Phone No:</label>
              <form:input type="text" class="form-control" id="phone"
                placeholder="Enter phone no" name="phone" path="phone"
                value="${ userForm.phone }" />
              <form:errors path="phone" style="color:red;" />
            </div>
            <div class="form-group w-75" style="margin: 0 auto;">
              <label for="address">Address :</label>
              <form:textarea type="text" class="form-control"
                id="address" placeholder="Enter address" name="address"
                path="address" value="${ userForm.address }" />
              <form:errors path="address" style="color:red;" />
            </div>
            <div class="col-md-12 text-center mt-3">
              <button type="submit" class="btn btn-primary align-center">Confirm</button>
              <button type="reset" name="cancel"
                class="btn btn-secondary" value="Cancel">Back</button>
            </div>
          </form:form>
        </div>
      </div>
    </div>
  </div>
</body>
</html>