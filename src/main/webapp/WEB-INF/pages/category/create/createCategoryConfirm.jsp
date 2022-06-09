<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Veterinary Pharmacy | Category Create Confirm</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
  <div class="wrapper">
    <div class="sec-list">
      <div class="container">
        <div class="sec-form">
          <h2 class="cmn-ttl">Category Create Confirm</h2>

          <c:url var="createCategoryConfirm" value="/insertCategory"></c:url>
          <form:form class="form clearfix" action="insertCategory"
            method="POST" id="form" modelAttribute="categoryForm">
            <c:if test="${errorMsg != null }">
              <div class="alert alert-danger">
                <strong>${errorMsg }</strong>
              </div>
            </c:if>

            <input type="hidden" name="id"
              value="${categoryForm.category_id }">

            <ul class="confirm-list">
              <li><b>Category Code</b> <a class="input">${categoryForm.category_code }
                  <form:input path="category_code" type="hidden"
                    name="category_code"
                    value="${categoryForm.category_code}"
                    class="form-control" />
              </a></li>
              <li><b>Category Name</b> <a class="input">${categoryForm.category_name }
                  <form:input path="category_name" type="hidden"
                    name="category_name"
                    value="${categoryForm.category_name}"
                    class="form-control" />
              </a></li>
            </ul>
            <div class="clearfix">
              <button type="submit" class="cmn-btn" name="addCategory">Add</button>
              <button type="submit" class="cmn-btn" name="cancel">Cancel</button>
            </div>
          </form:form>
        </div>
      </div>
    </div>
  </div>
</body>
</html>