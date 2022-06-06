<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<style>
.dataTables_wrapper .dataTables_paginate {
  float: none !important;
  text-align: center !important;
}
</style>
<!-- datatable -->
<link
  href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"
  type="text/css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script
  src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
</head>
<body>
  <div class="page-content page-container justify-content-center"
    id="page-content">
    <div class="padding justify-content-center">
      <div class="container d-flex justify-content-center">
        <div class="col-lg-12 grid-margin stretch-card">
          <div class="card">
            <div class="card-body mt-3">
              <h4 class="card-title">User List Table</h4>
              <c:if test="${errorMsg != null }">
                <p class="card-description text-success text-center">
                  ${errorMsg }</p>
              </c:if>
              <div class="table-responsive">
                <c:url value="/logout" var="logoutUrl" />
                <%-- <a href="${logoutUrl}">Log out</a> --%>
                <div class="margin-right mb-5">
                  <div class="col-lg-3" style="float: right;">
                    <div class="input-group">
                      <input type="text" class="form-control"
                        id="userTableSearch"
                        placeholder="Search user..."> <span
                        class="input-group-btn">
                        <button class="btn btn-info btn-flat"
                          type="button">Search</button> <c:if
                          test="${ LOGIN_USER.type == '1' }">
                          <a
                            href="${pageContext.request.contextPath}/user/create"><button
                              class="btn btn-info btn-flat button-leftList"
                              type="button">Add</button></a>
                        </c:if>
                      </span>
                    </div>
                    <!-- /input-group -->
                  </div>
                  <!-- /.col-lg-6 -->
                </div>
                <table id="userTable"
                  class="table table-bordered table-striped">
                  <thead>
                    <tr>
                      <th>No</th>
                      <th>Name</th>
                      <th>Email</th>
                      <th>Phone</th>
                      <th>Address</th>
                      <th>Type</th>
                      <th>Registered Date</th>
                      <c:if test="${ LOGIN_USER.type == '1' }">
                        <th>Action</th>
                      </c:if>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${userList}" var="user"
                      varStatus="loop">
                      <tr>
                        <td>${loop.index + 1 }</td>
                        <td>${user.username }</td>
                        <td>${user.email}</td>
                        <td>${user.phone }</td>
                        <td>${user.address }</td>
                        <td><c:if test="${user.type == 0}"> PHARMACIST </c:if>
                          <c:if test="${user.type == 1}"> ADMIN </c:if>
                          <c:if test="${user.type == 2}"> USER </c:if>
                        </td>
                        <td><fmt:formatDate pattern="dd/MM/YYYY"
                            value="${user.created_at}" /></td>
                        <c:if test="${ LOGIN_USER.type == '1' }">
                          <td><a
                            href="<c:url value='/user/update/?id=${user.id}'/>"
                            class="btn btn-sm btn btn-outline-primary">Edit</a>
                            <a
                            href="${pageContext.request.contextPath}/user/delete/?id=${user.id }"
                            class="btn btn-sm btn btn-outline-danger">Delete</a></td>
                        </c:if>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
<script type="text/javascript">
	$(document).ready(function() {
		var table = $('#userTable').DataTable({
			/* "aoColumns" : [ null, null, null, null, null, null, {
				"sType" : "date-uk"
			}, null ], */
			"paging" : true,
			"pageLength" : 5,
			"bLengthChange" : false,
			"bAutoWidth" : false,
			"dom" : 'rtp'
		});
		$('#userTableSearch').keyup(function() {
			table.search($(this).val()).draw();
		});
	});
</script>