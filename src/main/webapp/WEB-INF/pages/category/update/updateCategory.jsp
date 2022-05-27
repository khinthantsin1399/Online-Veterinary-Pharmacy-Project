<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper">

		<div class="container">
			<div class="sec-form">
				<h2 class="cmn-ttl">Category Update Form</h2>
 <c:url var="updateCategory" value="/updateCategoryConfirm"></c:url>
            <form:form class="form" action="updateCategoryConfirm"
              method="POST" id="form" modelAttribute="category">
              <form:input type="hidden" path="category_id"
                value="${category.category_id}" />
              
                 
                 
                    <label for="category_code">Category Code</label> <input
                      class="form-control" type="text"
                      name="category_code"
                      value="${category.category_code}" />
                    
               
                    <label for="category_name">Category Name</label> <input
                      class="form-control" name="category_name"
                      value="${category.category_name}">
                      
                       <button type="submit" class="btn btn-info">Update</button>
                  <a class="btn" href="${pageContext.request.contextPath}/categoryList">Back</a>
                  <a class="btn" href="${pageContext.request.contextPath}/updateCategory?id=${category.category_id}">Reset</a>
                 
                     
                  </form:form>
                  
                  </div>
                  </div>
                  </div>
</body>
</html>