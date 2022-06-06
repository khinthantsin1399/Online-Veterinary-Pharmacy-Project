<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Veterinary Pharmacy | Category List</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

</head>
<body>
	<div class="wrapper">
		<div class="sec-list">
			<div class="container">
				<h2 class="cmn-ttl">Category List</h2>
			 <c:if test="${completeMsg != null  && completeMsg != ''}">
            <div class="alert alert-success">
              <strong>${completeMsg }</strong>
            </div>
          </c:if>
          <c:remove var="completeMsg" />
				  <div class="row">
              <div class="col-sm-12 col-md-12">
                <div class="search-sec">
                  <c:url var="addAction" value="/searchCategory"></c:url>
                  <form:form action="${addAction}"
                    modelAtrribute="categoryForm" method="post" id="form"
                    class="searchForm-mr">
                    <label><input type="text"
                      class="form-control form-control-sm search-text-pd"
                      aria-controls="example1" name="search-input"
                      value="${searchData }"></label>
                    <input name="searchCategory" type="submit"
                      value="Search" class="btn btn-secondary">
                    <a
                      href="${pageContext.request.contextPath}/createCategory"
                      class="btn btn-info">Add</a>
                    
                    <c:if test="${!empty categoryList }">
                      <input type="submit" class="btn btn-info"
                        value="Download" name="downloadExcel">
                    </c:if>
                  </form:form>
                </div>
              </div>
				 
				 
				
				<table class="tbl-student">
				 <c:if test="${errorMsg != null }">
                    <div class="alert alert-danger">
                      <strong>${errorMsg }</strong>
                    </div>
                  </c:if>
					<tr>
					
						<th>Category Code</th>
						<th>Category Name</th>
						<th>Action</th>
					</tr>
					<c:forEach items="${CategoryList}" var="category" varStatus="loop">
						<tr>
						
						
							<td>${category.category_code}</td>
							<td>${category.category_name}</td>
							<td><a
								href="${pageContext.request.contextPath}/updateCategory?id=${category.category_id}"
								>Update</a> <a
								href="${pageContext.request.contextPath}/deleteCategory?id=${category.category_id}"
								class="cmn-link"
								onclick="if (!(confirm('Are you sure you want to delete this item?'))) return false">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
				
			 <c:if test="${noOfPages > 0}">
      <div class="row">
        <div class="col-sm-12 col-md-6">
          <div class="dataTables_paginate paging_simple_numbers"
            id="example1_paginate">
            <ul class="pagination">
              <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                  href="?recordsPerPage=${recordsPerPage}&currentPage=${currentPage - 1}&search_input=${searchData }">Previous</a>
                </li>
              </c:if>
              <c:forEach begin="1" end="${noOfPages }" var="i">
                <c:choose>
                  <c:when test="${currentPage eq i}">
                    <li class="page-item active"><a
                      class="page-link"> ${i} <span class="sr-only">(current)</span>
                    </a></li>
                  </c:when>
                  <c:otherwise>
                    <li class="page-item"><a class="page-link"
                      href="?recordsPerPage=${recordsPerPage}&currentPage=${i}&search_input=${searchData }">
                        ${i} </a></li>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
              <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                  href="?recordsPerPage=${recordsPerPage}&currentPage=${currentPage + 1}&search_input=${searchData }">Next</a>
                </li>
              </c:if>
            </ul>
          </div>
        </div>
      </div>
    </c:if>
			</div>	
			</div>
		</div>
	</div>
</body>
</html>