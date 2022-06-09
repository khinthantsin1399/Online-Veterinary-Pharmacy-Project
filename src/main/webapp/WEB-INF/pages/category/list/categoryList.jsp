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

				<div class="search-sec">
					<c:url var="addAction" value="/searchCategory"></c:url>
					<form:form action="${addAction}" modelAtrribute="categoryForm"
						method="post" id="form">
						<ul class="btn-list clearfix">
							<li><label><input type="text"
									class="search-box description-txt"
									aria-controls="example1" name="search-input"
									value="${searchData }" placeholder="Search..."></label></li>
							<li><input name="searchCategory" type="submit"
								value="Search" class="cmn-link"></li>
                            <c:if test="${LOGIN_USER.type == '0' }">
    							<li><a
    								href="${pageContext.request.contextPath}/createCategory"
    								class="cmn-link">Add</a></li>
                            </c:if>

							<%-- <c:if test="${!empty CategoryList }">
								<li><input type="submit" class="cmn-link" value="Download"
									name="downloadExcel"></li>
							</c:if> --%>
						</ul>
					</form:form>
				</div>

				<table class="tbl">
					<c:if test="${errorMsg != null }">
						<div class="alert alert-danger">
							<strong>${errorMsg }</strong>
						</div>
					</c:if>
					<tr>
						<th>Category Code</th>
						<th>Category Name</th>
                        <c:if test="${LOGIN_USER.type == '0' }">
						  <th>Action</th>
                        </c:if>
					</tr>
					<c:forEach items="${CategoryList}" var="category" varStatus="loop">
						<tr>
							<td>${category.category_code}</td>
							<td>${category.category_name}</td>
                            <c:if test="${LOGIN_USER.type == '0' }">
    							<td><a class="cmn-link"
    								href="${pageContext.request.contextPath}/updateCategory?id=${category.category_id}">Update</a>
    								<a
    								href="${pageContext.request.contextPath}/deleteCategory?id=${category.category_id}"
    								class="cmn-link"
    								onclick="if (!(confirm('Are you sure you want to delete this item?'))) return false">Delete</a></td>
                            </c:if>
						</tr>
					</c:forEach>
				</table>

				<c:if test="${noOfPages > 0}">
					
						
							<div class="pagination-sec">
								<ul class="pagination">
									<c:if test="${currentPage != 1}">
										<li><a class="page-link"
											href="?recordsPerPage=${recordsPerPage}&currentPage=${currentPage - 1}&search_input=${searchData }">Previous</a>
										</li>
									</c:if>
									<c:forEach begin="1" end="${noOfPages }" var="i">
										<c:choose>
											<c:when test="${currentPage eq i}">
												<li><a class="page-link active">
														${i} <span class="sr-only">(current)</span>
												</a></li>
											</c:when>
											<c:otherwise>
												<li><a class="page-link"
													href="?recordsPerPage=${recordsPerPage}&currentPage=${i}&search_input=${searchData }">
														${i} </a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${currentPage lt noOfPages}">
										<li><a class="page-link"
											href="?recordsPerPage=${recordsPerPage}&currentPage=${currentPage + 1}&search_input=${searchData }">Next</a>
										</li>
									</c:if>
								</ul>
							</div>
						
					
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>