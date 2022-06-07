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
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

<link
  href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"
  type="text/css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script
  src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
</head>
<body>
  <div class="wrapper">
		<div class="sec-list user">
			<div class="container">
              <h4 class="cmn-ttl">User List Table</h4>
              <c:if test="${errorMsg != null }">
                <p class="card-description text-success text-center">
                  ${errorMsg }</p>
              </c:if>
             
                <c:url value="/logout" var="logoutUrl" />
                <%-- <a href="${logoutUrl}">Log out</a> --%>
               <div class="search-sec">
               <ul class="btn-list clearfix">
                    <li>  <input type="text" class="search-box"
                        id="userTableSearch"
                        placeholder="Search user..."> </li>
                      <li class="user-btn">  <button class="cmn-link"
                          type="button">Search</button> </li><c:if
                          test="${ LOGIN_USER.type == '1' }">
                       <li class="user-btn">   <a
                            href="${pageContext.request.contextPath}/user/create"><button
                              class="cmn-link"
                              type="button">Add</button></a></li>
                        </c:if>
                     </ul>
                    </div>
               
                <table id="userTable"
                  class="tbl">
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
                            class="cmn-link">Edit</a>
                            <a
                            href="${pageContext.request.contextPath}/user/delete/?id=${user.id }"
                          class="cmn-link">Delete</a></td>                  
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
			"dom" : 'rtp'
		});
		$('#userTableSearch').keyup(function() {
			table.search($(this).val()).draw();
		});
	});
</script>