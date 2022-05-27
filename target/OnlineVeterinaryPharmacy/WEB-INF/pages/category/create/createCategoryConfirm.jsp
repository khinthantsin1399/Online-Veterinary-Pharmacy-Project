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

		<div class="container">
			<div class="sec-form">
				<h2 class="cmn-ttl">Category Create Confirm</h2>
				
				 <c:url var="createStudentConfirm" value="/insertCategory"></c:url>
            <form:form class="form" action="insertCategory"
              method="POST" id="form" modelAttribute="categoryForm">
              <input type="hidden" name="id" value="${categoryForm.id }">
              
               <ul class="list-group list-group-unbordered mb-3">
                    <li class="list-group-item"><b>Category Code</b>
                      <a class="float-right">${categoryForm.category_code }
                        <form:input path="category_code " type="hidden"
                          name="category_code r"
                          value="${categoryForm.category_code }"
                          class="form-control" />
                    </a></li>
                    <li class="list-group-item"><b>Category Name</b> <a
                      class="float-right">${categoryForm.name } <form:input
                          path="category_name" type="hidden" name="category_name"
                          value="${categoryForm.category_name}"
                          class="form-control" /></a></li>
                          </ul>
                          
                 <button type="submit" class="btn"
                    name="addCategory">Add</button>
                  <button type="submit" class="btn"
                    name="cancel">Cancel</button>
              </form:form>
</div>
</div>
</div>
</body>
</html>