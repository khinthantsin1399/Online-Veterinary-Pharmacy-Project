<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Veterinary Pharmacy | ADD FORM</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

</head>
<body>
	<div class="wrapper">

		<div class="container">
			<div class="sec-form">
				<h2 class="cmn-ttl">Category Add Form</h2>
				
				
   
            <form:form class="form" action="createCategoryConfirm"
              method="POST" id="form" modelAttribute="createCategoryForm">
              
              
               <c:if test="${errorMsg != null }">
                    <div class="alert alert-danger">
                      <strong>${errorMsg }</strong>
                    </div>
                  </c:if>
              
               <label for="category_code">Category Code</label>
                    <form:input path="category_code"
                      value="${createCategoryForm.category_code }"
                     class="input" placeholder="Enter Category Code" />
                     <form:errors path="category_code" class="text-danger" />
              
               <label for="category_name">Category Name</label>
                    <form:input path="category_name"
                      value="${createCategoryForm.category_name }"
                     class="input" placeholder="Enter Category Name" />
                        <form:errors path="category_name" class="text-danger" />
                     
                       <button type="submit" class="btn"
                    name="confirmCategory">Confirm</button>
                    
                  <button type="reset" class="btn"
                    name="clear">Reset</button>
                      </form:form>
			</div>
		</div>
	</div>
</body>
</html>