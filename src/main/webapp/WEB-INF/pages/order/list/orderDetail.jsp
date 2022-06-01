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
</head>
<body>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card mt-5">
          <h2 class="text-primary text-center mb-4">Order Detail
            History Form</h2>
          <input type=hidden name="id" value="" />
          <div class="form-group w-75" style="margin: 0 auto;">
            <label for="name">User Name :</label> ${order.u_name}
          </div>
          <div class="form-group w-75" style="margin: 0 auto;">
            <label for="phone">Phone No:</label> ${order.u_phone}
          </div>
          <div class="form-group w-75" style="margin: 0 auto;">
            <label for="address">Address :</label> ${order.u_address}
          </div>
          <div class="form-group w-75" style="margin: 0 auto;">
            <label for="date">Order Date :</label>
            <fmt:formatDate value="${order.date}" />
          </div>
          <div class="form-group w-75" style="margin: 0 auto;">
            <label for="status">Status :</label>
            <c:if test="${order.status}">
              <span class="badge badge-success">Ordered</span>
            </c:if>
            <c:if test="${!order.status}">
              <span class="badge badge-warning">Not yet</span>
            </c:if>
          </div>
          <div class="form-group w-75" style="margin: 0 auto;">
            <label for="status">Total Price :</label> ${order.amount}
          </div>
        </div>
        <div class="card mt-5">
          <table class="table table-hover">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Medicine Name</th>
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
                  <td>${orderDetail.m_quantity}</td>
                  <td>${orderDetail.amount * orderDetail.m_quantity}</td>
                </tr>
              </c:forEach>
            </tbody>
            <tfoot>
              <tr>
                <td colspan="4" align="center"><a
                  class="btn btn-sm btn-secondary mr-1 text-white"
                  onclick="history.back(2);">Back</a><a
                  href="${pageContext.request.contextPath}/order/accept?id=${order.id}"
                  class="btn btn-sm btn-info text-white ${order.status ? 'disabled' : ''}">Accept</a></td>
              </tr>
            </tfoot>
          </table>
        </div>
      </div>
    </div>
  </div>
</body>
</html>