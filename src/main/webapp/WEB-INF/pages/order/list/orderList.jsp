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

<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

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
                <div class="margin-right mb-5">
                  <div class="col-lg-3" style="float: right;">
                    <div class="input-group">
                      <input type="text" class="form-control"
                        id="orderTableSearch"
                        placeholder="Search order date..."> <span
                        class="input-group-btn">
                        <button class="btn btn-info btn-flat"
                          type="button">Search</button>
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
                      <th>Customer Name</th>
                      <th>Phone</th>
                      <th>Address</th>
                      <th class="date">Order Date</th>
                      <th>Status</th>
                      <th>Total Price</th>
                      <th>Order Details</th>
                      <th>Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${orderList}" var="order"
                      varStatus="loop">
                      <tr>
                        <td>${loop.index + 1 }</td>
                        <td>${order.u_name }</td>
                        <td>${order.u_phone}</td>
                        <td>${order.u_address }</td>
                        <td><fmt:formatDate pattern="dd/MM/YYYY"
                            value="${order.date}" /></td>
                        <td><c:if test="${order.status}">
                            <span class="badge badge-success">Success</span>
                          </c:if> <c:if test="${!order.status}">
                            <a href="#"> <span
                              class="badge badge-warning">Not yet</span>
                            </a>
                          </c:if></td>
                        <td>${order.amount }</td>
                        <td><a class=""
                          href="${pageContext.request.contextPath}/order/detail?id=${order.id}">See
                            Details</a></td>
                        <td><a
                          class="btn btn-sm btn-primary ${order.status ? 'disabled' : ''}"
                          href="${pageContext.request.contextPath}/order/accept?id=${order.id}">Accept</a>
                          <a
                          class="btn btn-sm btn-danger ${order.status ? 'disabled' : ''}"
                          href="${pageContext.request.contextPath}/order/cancel?id=${order.id}">Cancel</a>
                        </td>
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
		$('#orderTableSearch').keyup(function() {
			table.columns('.date').search($(this).val()).draw();
		});
	});
</script>