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
        <h2 class="cmn-ttl">Medicine List</h2>
        <c:if test="${errorMsg != null }">
          <div class="alert alert-danger">
            <strong>${errorMsg }</strong>
          </div>
        </c:if>
        <div class="row">
          <div class="col-sm-12 col-md-12">
            <div class="search-sec">
              <c:url var="addAction" value="/searchMedicine"></c:url>
              <form:form action="${addAction}"
                modelAtrribute="medicineForm" method="post" id="form"
                class="searchForm-mr">
                <label><input type="text"
                  class="form-control form-control-sm search-text-pd"
                  aria-controls="example1" name="search-input"
                  value="${searchData }"></label>
                <input name="searchMedicine" type="submit"
                  value="Search" class="btn btn-secondary">
                <c:if test="${isCart}">
                  <a href="${pageContext.request.contextPath}/viewCart"
                    class="btn btn-info">View Cart</a>
                </c:if>
              </form:form>
            </div>
          </div>
        </div>
        <div class="sec-work clearfix">
          <ul class="work-list clearfix">
            <c:forEach items="${MedicineList}" var="medicine"
              varStatus="loop">
              <li><c:if test="${empty medicine.image }">
                  <img src="<c:url value='/resources/img/noimage.png'/>"
                    alt="Medicine picture">
                </c:if> <c:if test="${not empty medicine.image }">
                  <img src="${medicine.image}" alt="product image">
                </c:if>
                <p class="work-txt">${medicine.medicine_name}</p>
                <p class="work-txt">${medicine.amount}MMK</p> <a
                href=" <c:url value='detailMedicine?id=${medicine.id}'/>"
                class="btn btn-info">View Detail</a> <a
                class="btn btn-info"
                href="${pageContext.request.contextPath}/addToCart?id=${medicine.id}">ADD
                  TO CART</a></li>
            </c:forEach>
          </ul>
        </div>
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
                          class="page-link"> ${i} <span
                            class="sr-only">(current)</span>
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
</body>
</html>