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
		<div class="sec-list userMedicine">
			<div class="banner"></div>
			<div class="container">
				<!-- <h2 class="cmn-ttl">Medicine List</h2> -->
				<c:if test="${errorMsg != null }">
					<div class="alert alert-danger">
						<strong>${errorMsg }</strong>
					</div>
				</c:if>
				<c:if test="${completeMsg != null  && completeMsg != ''}">
					<div class="alert alert-success">
						<strong>${completeMsg }</strong>
					</div>
				</c:if>
				<c:remove var="completeMsg" />
				<div class="search-sec">
					<c:url var="addAction" value="/searchMedicineUser"></c:url>
					<form:form action="${addAction}" modelAtrribute="medicineForm"
						method="post" id="form" class="searchForm-mr">
						<ul class="btn-list clearfix">
							<li><label><input type="text" class="search-box description-txt"
									aria-controls="example1" name="search-input"
									value="${searchData }" placeholder="Search..."></label></li>
							<li><input name="searchMedicine" type="submit"
								value="Search" class="cmn-link"></li>
							<c:if test="${isCart}">
								<li><a href="${pageContext.request.contextPath}/viewCart"
									class="cart cmn-link"><i class="fa fa-shopping-cart"
										aria-hidden="true"></i></a></li>
							</c:if>
						</ul>
					</form:form>

				</div>
				<div class="sec-medicine user-sec-medicine clearfix">
					<ul class="medicine-list clearfix">
						<c:if test="${!empty MedicineList}">
							<c:forEach items="${MedicineList}" var="medicine"
								varStatus="loop">
								<li class="heightline-medicine"><c:if
										test="${empty medicine.image }">
										<img src="<c:url value='/resources/img/noimage.png'/>"
											alt="Medicine picture">
									</c:if> <c:if test="${not empty medicine.image }">
										<img src="${medicine.image}" alt="product image">
									</c:if>
									<p class="medicine-txt">${medicine.medicine_name}</p>
									<p class="medicine-txt">${medicine.amount}MMK</p> <a
									href=" <c:url value='detailMedicine?id=${medicine.id}'/>"
									class="cmn-link">View Detail</a> <a class="cmn-link"
									href="${pageContext.request.contextPath}/addToCart?id=${medicine.id}">ADD
										TO CART</a></li>

							</c:forEach>
						</c:if>
					</ul>
					<c:if test="${empty MedicineList}">
						<p class="search-msg">
							<i class="fa fa-frown" aria-hidden="true"></i>No Product Found!
						</p>
					</c:if>
				</div>
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
										<li><a class="page-link active"> ${i} <span
												class="sr-only">(current)</span>
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
	<script src="<c:url value="/resources/js/lib/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/lib/jquery.heightLine.js"/>"></script>
	<script src="<c:url value="/resources/js/common.js"/>"></script>
</body>
</html>