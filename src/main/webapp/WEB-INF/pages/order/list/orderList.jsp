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

.dataTables_wrapper .dataTables_filter {
	float: right;
	text-align: right;
	visibility: hidden;
}

.badge-success {
	background-color: green;
}

.badge-warning {
	background-color: burlywood;
}
</style>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

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
<script
	src="https://cdn.datatables.net/buttons/2.2.3/js/dataTables.buttons.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script
	src="https://cdn.datatables.net/buttons/2.2.3/js/buttons.html5.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/2.2.3/js/buttons.print.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<div class="sec-list user">
			<div class="container">
				<h4 class="cmn-ttl">Order List Table</h4>
				<c:if test="${errorMsg != null }">
					<p class="card-description text-success text-center">
						${errorMsg }</p>
				</c:if>
				<div class="search-sec">
					<ul class="btn-list clearfix">
						<li><input type="text" class="search-box"
							id="orderTableSearch" placeholder="Search order date...">
						</li>
						<li class="user-btn">
							<button class="cmn-link" type="button">Search</button>
							<li></ul>
            
					       </div>
                <table id="userTable" class="tbl">
                  <thead>
                    <tr>
                      <th>No</th>
                      <th>Customer Name</th>
                      <th>Phone</th>
                      <th>Address</th>
                      <th class="date">Order Date</th>
                      <th>Status</th>
                      <th>Total Price</th>
                      <c:if test="${LOGIN_USER.type == 0 }">
                        <th>Order Details</th>
                        <th>Action</th>
                      </c:if>
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
                        <c:if test="${LOGIN_USER.type == 0 }">
                          <td><a class="cmn-link"
										href="${pageContext.request.contextPath}/order/detail?id=${order.id}">See
                              Details</a></td>
                          <td><a
										class="cmn-link ${order.status ? 'disabled' : ''}"
										href="${pageContext.request.contextPath}/order/accept?id=${order.id}">Accept</a>
                            <a
										class="cmn-link ${order.status ? 'disabled' : ''}"
										href="${pageContext.request.contextPath}/order/cancel?id=${order.id}">Cancel</a>
                          </td>                        
                        </c:if>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
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
			dom : 'Bfrtip',
			buttons : [ {
				extend : 'excel',
				exportOptions : {
					columns : [ 0, 1, 2, 3, 4, 5, 6 ]
				}
			}, 'colvis' ],
		});
		$('#orderTableSearch').keyup(function() {
			table.columns('.date').search($(this).val()).draw();
		});
	});
</script>