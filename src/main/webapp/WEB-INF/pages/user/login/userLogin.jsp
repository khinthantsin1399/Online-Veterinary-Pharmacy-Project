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
   <link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
 <div class="wrapper">
  <div class="container">
  
     <div class="sec-form">
        <%-- <a class="btn btn-primary" href="<c:url value ="/"/>">Back</a> --%>
     
    
    
        <div class="cmn-ttl">Login</div>
       
          <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger text-center" role="alert">
              ${errorMsg}</div>
          </c:if>
        

            <c:url value="/login" var="loginUrl" />
            <form:form action="${loginUrl}" method="POST" class="form clearfix login">
              <input type="hidden" name="${_csrf.parameterName}"
                value="${_csrf.token}" />
              
                <label for="email" >E-Mail
                  Address</label> <input type="email" name="email" id="email"
                  class="input" /> <span class="form-text">
                  <strong></strong>
                </span>

             
             
                <label for="password" class="control-label">Password</label>
                <input type="password" name="password" id="password"
                  class="input" /> <span class="form-text">
                  <strong></strong>
                </span>

          
              <!-- <div class="form-group">
                <div class="checkbox">
                  <label> <input type="checkbox" name="remember">
                    Remember Me
                  </label>
                </div>
              </div> -->
              <button type="submit" class="cmn-btn">Login</button>
              <div class="clearfix">
                <c:url value="/password/reset" var="pwReset" />
                <a href="#" class="medicine-link">Forgot Your Password?</a>
             
                <c:url value="/user/register" var="register" />
                <a href="${register}" class="medicine-link">Sign Up?</a>
             
               </div>
            
            </form:form>
          </div>
        </div>
      </div>
</body>
</html>
