<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
  <div class="row">
    <div class="col-lg-12">
      <div class="pull-right">
        <%-- <a class="btn btn-primary" href="<c:url value ="/"/>">Back</a> --%>
      </div>
    </div>
    <div class="col-lg-6 mx-auto content">
      <div class="card card-default input-form">
        <div class="card-header text-center">Login</div>
        <div class="card-body">
          <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger text-center" role="alert">
              ${errorMsg}</div>
          </c:if>
          <div class="form-horizontal col-md-9 mx-auto">

            <c:url value="/login" var="loginUrl" />
            <form:form action="${loginUrl}" method="POST">
              <input type="hidden" name="${_csrf.parameterName}"
                value="${_csrf.token}" />
              <div class="form-group">
                <label for="email" class="control-label">E-Mail
                  Address</label> <input type="email" name="email" id="email"
                  class="form-control" /> <span class="form-text">
                  <strong></strong>
                </span>

              </div>
              <div class="form-group">
                <label for="password" class="control-label">Password</label>
                <input type="password" name="password" id="password"
                  class="form-control" /> <span class="form-text">
                  <strong></strong>
                </span>

              </div>
              <!-- <div class="form-group">
                <div class="checkbox">
                  <label> <input type="checkbox" name="remember">
                    Remember Me
                  </label>
                </div>
              </div> -->
              <div class="form-group">
                <c:url value="/password/reset" var="pwReset" />
                <a href="${pwReset}">Forgot Your Password?</a>
              </div>
              <div class="form-group col-md-12 text-center">
                <button type="submit" class="btn btn-primary">Login</button>
              </div>
            </form:form>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
