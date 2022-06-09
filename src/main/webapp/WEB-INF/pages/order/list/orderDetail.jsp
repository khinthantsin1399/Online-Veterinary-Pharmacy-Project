<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<h2 class="cmn-ttl">Order Detail</h2>
			<div class="order-detail-sec clearfix">
				<input type=hidden name="id" value="" />
				<table class="order-detail-tbl ">
					<tr>
						<th><label for="name" class="order-label">User Name</label></th>
						<td>${order.u_name}</td>
					</tr>
					<tr>
						<th><label for="phone" class="order-label">Phone No</label></th>
						<td>${order.u_phone}</td>
					</tr>
					<tr>
						<th><label for="address" class="order-label">Address</label></th>
						<td>${order.u_address}</td>
					</tr>
					<tr>
						<th><label for="date" class="order-label">Order Date</label></th>
						<td><fmt:formatDate value="${order.date}" /></td>
					</tr>
					<tr>
						<th><label for="status" class="order-label">Status</label></th>

						<c:if test="${order.status}">
							<td><span class="order-status">Ordered</span>
							</td>
						</c:if>
						<c:if test="${!order.status && order.deleted_at == null}">
							<td><span class="order-status not-yet-status">Not yet</span>
							</td>
						</c:if>
                        <c:if test="${order.deleted_at != null}">
                          <td><span class="order-status reject-status">Reject</span>
                          </td>
                        </c:if>
					<tr>
						<th><label for="status" class="order-label">Total
								Price</label></th>
						<td>${order.amount} MMK</td>
					</tr>
				</table>
			</div>

			<div class="card mt-5">
				<table class="table table-hover">
					<thead class="thead-dark">
						<tr>
							<th scope="col">No.</th>
							<th scope="col">Medicine Name</th>
                            <th scope="col">Unit Price</th>
							<th scope="col">Quantity</th>
							<th scope="col">Total Price</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orderDetail}" var="orderDetail"
							varStatus="index">
							<tr>
								<th scope="row">${index.index+1}</th>
								<td>${orderDetail.medicine.medicine_name}</td>
                                <td>${orderDetail.amount / orderDetail.m_quantity}</td>
								<td>${orderDetail.m_quantity}</td>
								<td>${orderDetail.amount}</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5" align="center"><a
								class="btn btn-sm btn-secondary mr-1 text-white btn-margin-right"
								onclick="history.back(2);">Back</a>
                                <c:if test="${LOGIN_USER.type == 0 }">
                                    <a
    								href="${pageContext.request.contextPath}/order/accept?id=${order.id}"
    								class="btn btn-sm btn-info text-white ${(order.status || order.deleted_at != null) ? 'disabled' : ''}">Accept</a>
                                </c:if>
                            </td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</body>
</html>