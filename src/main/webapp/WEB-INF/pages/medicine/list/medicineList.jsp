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
        <c:if test="${completeMsg != null  && completeMsg != ''}">
          <div class="alert alert-success">
            <strong>${completeMsg }</strong>
          </div>
        </c:if>
        <c:if test="${noCategoryMsg != null}">
          <div class="alert alert-danger">
            <span>${noCategoryMsg}</span>
          </div>
        </c:if>
        <c:remove var="completeMsg" />
        <div class="search-sec">
          <c:url var="addAction" value="/searchMedicine"></c:url>
          <form:form action="${addAction}" modelAtrribute="medicineForm"
            method="post" id="form">
            <ul class="btn-list clearfix">
              <li><label><input type="text"
                  aria-controls="example1" name="search-input"
                  value="${searchData }"
                  class="search-box description-txt"
                  placeholder="Search.."></label></li>
              <li><input name="searchMedicine" type="submit"
                value="Search" class="cmn-link"></li>
              <c:if test="${LOGIN_USER.type == '0' }">
                <li><a
                  href="${pageContext.request.contextPath}/createMedicine"
                  class="cmn-link">Add</a></li>

                <li><a
                  href="${pageContext.request.contextPath}/download"
                  class="cmn-link">Download</a></li>
              </c:if>
            </ul>

          </form:form>
        </div>

        <%-- <c:forEach items="${uploadErrorMsg}" var="err" varStatus="loop">
					<c:if test="${err != null }">
						<div class="alert alert-danger">
							<strong>${err }</strong>
						</div>
					</c:if>
				</c:forEach> --%>
        <br />
        <c:if test="${uploadErrorMsg != null}">
          <span class="text-danger">${uploadErrorMsg}</span>
        </c:if>
        <c:if test="${LOGIN_USER.type == '0' }">
          <form:form
            action="${pageContext.request.contextPath}/uploadExcel?${_csrf.parameterName}=${_csrf.token}"
            method="POST" enctype="multipart/form-data"
            class="upload-sec">
            <br />
            <input type="hidden" name="${_csrf.parameterName}"
              value="${_csrf.token}" />
            <input type="file" name="file" value="Browse File"
              accept=".xlsx" class="browse-file" />
            <input type="submit" class="cmn-link" value="upload" />
            <br />
            <br />
          </form:form>
        </c:if>

        <table class="tbl">
          <tr>
            <th>Code</th>
            <th>Name</th>
            <th>Category</th>
            <th>Price</th>
            <th>Unit In Stock</th>
            <c:if test="${LOGIN_USER.type == '0' }">
              <th>Action</th>
            </c:if>
          </tr>
          <c:if test="${!empty MedicineList}">

            <c:forEach items="${MedicineList}" var="medicine"
              varStatus="loop">
              <tr>
                <td>${medicine.medicine_code}</td>
                <td><a
                  href="<c:url value='detailMedicine?id=${medicine.id}'/>"
                  class="medicine-link">${medicine.medicine_name}</a></td>
                <td>${medicine.category.category_name}</td>
                <td>${medicine.amount}</td>
                <td>${medicine.unit_in_stock}</td>
                <c:if test="${LOGIN_USER.type == '0' }">
                  <td><a
                    href="${pageContext.request.contextPath}/updateMedicine?id=${medicine.id}"
                    class="cmn-link">Update</a> <a
                    href="${pageContext.request.contextPath}/deleteMedicine?id=${medicine.id }"
                    class="cmn-link"
                    onclick="if (!(confirm('Are you sure you want to delete this item?'))) return false">Delete</a></td>
                </c:if>
              </tr>
            </c:forEach>
          </c:if>
          <c:if test="${empty MedicineList}">
            <tr>
              <td colspan="7">No Data Found!</td>
            </tr>

          </c:if>
        </table>


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
</body>
</html>