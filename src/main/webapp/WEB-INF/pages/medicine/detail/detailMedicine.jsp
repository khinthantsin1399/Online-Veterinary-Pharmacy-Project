<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
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
			<div class="detail-box">
   <div class="content clearfix">
   <div class="img-sec">
                  <c:if test="${empty detailMedicine.image }">
                    <img 
                      src="<c:url value='/resources/img/noimage.png'/>"
                      alt="User profile picture">
                  </c:if>
                  <c:if test="${not empty detailMedicine.image }">
                    <img 
                      src="${detailMedicine.image}" />
                  </c:if>
                </div>
                <div class="detail-sec clearfix">
               
                <ul class="list-group list-group-unbordered mb-3">
                 <li><p><b>Name</b> ${detailMedicine.medicine_name}</p></li>
                  
                  <li><p
                   ><b>Price</b> ${detailMedicine.amount}</p></li>
                  
                  <li><p
                  ><b>Category</b> ${detailMedicine.category.category_name}</p></li>
                    </ul>
                    </div>
            </div>
               <div class="description clearfix">
                  <b>Description</b>
                    <p>${detailMedicine.medicine_description}</p>
                  </div>
               
                
                <a href="${pageContext.request.contextPath}/medicineList"
                  class="cmn-link">Back</a>
              </div>
              </div>
  </div>
  </div>
  </body>
  </html>
