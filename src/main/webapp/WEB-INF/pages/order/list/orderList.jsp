<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<style type="text/css">
/* order detail jsp */
.dataTables_wrapper .dataTables_paginate {
  float: none !important;
  text-align: center !important;
}

.dataTables_wrapper .dataTables_filter {
  float: right;
  text-align: right;
  visibility: hidden;
}

.buttons-html5 {
  margin: 30px;
}

.badge-success {
  background-color: #66FF66;
}

.badge-warning {
  background-color: #FFB266;
}

.badge-reject {
  background-color: #FF6666;
}

.input, button, select {
  width: 140px !important;
  margin-left: 0px !important;
}

.btn-excel-down {
  background-color: #32b3b8;
  color: #fff;
}
</style>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
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
    <div class="sec-list order">
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
            <li>
          </ul>

        </div>
        <div class="scroll-bar">
          <table id="userTable" class="tbl tbl-font">
            <thead>
              <tr>
                <th>No</th>
                <th>Customer Name</th>
                <th>Phone</th>
                <th>Address</th>
                <th class="date">Order Date</th>
                <th>Status</th>
                <th>Total Price</th>
                <c:if test="${LOGIN_USER.type != 1 }">
                  <th>Order Details</th>
                  <c:if test="${LOGIN_USER.type == 0 }">
                  <th>Action</th>
                  </c:if>
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
                    </c:if> <c:if test="${!order.status && order.deleted_at == null}">
                      <a href="#"> <span class="badge badge-warning">Not
                          yet</span>
                      </a>
                    </c:if><c:if test="${order.deleted_at != null}">
                    <a href="#"> <span class="badge badge-reject">Reject</span>
                      </a>
                    </c:if> </td>
                  <td>${order.amount }</td>
                  <c:if test="${LOGIN_USER.type != 1 }">
                    <td><a class="cmn-link"
                      href="${pageContext.request.contextPath}/order/detail?id=${order.id}">See
                        Details</a></td>
                    <c:if test="${LOGIN_USER.type == 0 }">
                      <td><a
                        class="btn cmn-link ${(order.status || order.deleted_at != null) ? 'disabled' : ''}"
                        href="${pageContext.request.contextPath}/order/accept?id=${order.id}">Accept</a>
                        <a
                        class="btn cmn-cancel ${(order.status || order.deleted_at != null) ? 'disabled' : ''}"
                        href="${pageContext.request.contextPath}/order/cancel?id=${order.id}">Cancel</a>
                      </td>
                    </c:if>
                  </c:if>
                </tr>
              </c:forEach>
            </tbody>
          </table>
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
			"bFilter": true,
		    "bInfo": false,
			"bAutoWidth" : false,
			dom : 'Bfrtip',
			buttons : [ {
				extend : 'excel',
				text : 'Download',
				className : 'btn-excel-down',
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