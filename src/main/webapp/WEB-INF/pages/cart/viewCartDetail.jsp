<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Veterinary Pharmacy | Cart</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

</head>
<body>
  <div class="wrapper">
    <div class="sec-list">
      <div class="container">
        <h2 class="cmn-ttl">Medicine List</h2>
        <a href="${pageContext.request.contextPath}/userMedicineList">Continue
          Shopping</a>
        <table class="tbl-student">
          <tr>
            <th>Name</th>
            <th>Unit Price</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Action</th>
          </tr>
          <c:forEach items="${cartDetails}" var="cartDetail"
            varStatus="loop">
            <form:form
              action="${pageContext.request.contextPath}/updateQuantity">

              <tr>
                <td>${cartDetail.medicine.medicine_name}</td>
                <td>${cartDetail.medicine.amount}</td>
                <td><input type="hidden" name="id"
                  value="${ cartDetail.c_id}" /> <input type="number"
                  min="1" max="${cartDetail.medicine.unit_in_stock}"
                  name="quantity" value="${cartDetail.quantity}"
                  onblur="this.form.submit()" /></td>
                <td>${cartDetail.medicine.amount * cartDetail.quantity}</td>
                <td><a
                  href="${pageContext.request.contextPath}/deleteCartItem?id=${cartDetail.c_id }"
                  class="cmn-link"
                  onclick="if (!(confirm('Are you sure you want to remove this item?'))) return false">Delete</a>
              </tr>
            </form:form>
            <c:if test="${loop.index == 0}">
              <b>Total Amount : ${cartDetail.cart.amount} </b>
              <a class="btn btn-success"
                href="${pageContext.request.contextPath}/cart/checkout?id=${cartDetail.cart.id}">Check
                Out Items</a>
            </c:if>
          </c:forEach>
        </table>
      </div>
    </div>
  </div>
  <script src="<c:url value="/resources/js/common.js"/>"></script>
</body>
</html>