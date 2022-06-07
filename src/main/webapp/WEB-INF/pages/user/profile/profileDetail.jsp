<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

</head>
<body>
 <div class="wrapper">
		<div class="container">
      
          <h2 class="cmn-ttl">Profile Detail
            Form</h2>
          
          <input type=hidden name="id" value="${profileDetailForm.id}" />
           <table class="order-detail-tbl profile-detail-tbl">
           <tr>
           <th> <label for="name">User Name</label></th> 
           <td>${ profileDetailForm.username }</td>
          </tr>
          <tr>
         <th>   <label for="email">Email</label> </th>
         <td>${ profileDetailForm.email }</td>
         </tr>
          <tr>
            <c:if test="${ profileDetailForm.type == 0}">
            <th>  <label for="type">Type</label></th>
           <td> PHARMACIST</td>
            </c:if>
            <c:if test="${ profileDetailForm.type == 1}">
             <th> <label for="type">Type</label></th>
           <td> ADMIN</td>
            </c:if>
            <c:if test="${ profileDetailForm.type == 2}">
            <th>  <label for="type">Type</label></th>
           <td> USER</td>
            </c:if>
         </tr>
         <tr>
          <th>  <label for="phone">Phone No</label> </th>
          <td>${ profileDetailForm.phone }</td>
         </tr>
         <tr>
         <th>
            <label for="address">Address</label> </th>
            <td>${ profileDetailForm.address }</td>
            </tr>
          </table>
          <div class="profile-btn clearfix" >
            <a
              href="${pageContext.request.contextPath}/profile/edit?id=${profileDetailForm.id}">
              <button type="submit" name="editProfile"
                class="cmn-btn">Edit
                Confirm</button>
            </a> <a href="${pageContext.request.contextPath}/medicineList">
              <button type="submit" name="cancel"
                class="cmn-btn" value="Cancel">Back</button> <!-- onclick="history.back(2);" -->
            </a>
            </div>
          </div>
        </div>
     
    
</body>
</html>