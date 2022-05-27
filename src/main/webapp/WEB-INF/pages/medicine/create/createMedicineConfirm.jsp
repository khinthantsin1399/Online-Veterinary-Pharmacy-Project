<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Veterinary Pharmacy | Medicine Create Confirm</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

</head>
<body>
	<div class="wrapper">

		<div class="container">
			<div class="sec-form">
				<h2 class="cmn-ttl">Medicine Create Confirm</h2>
				
				 <c:url var="createMedicineConfirm" value="/insertMedicine"></c:url>
            <form:form class="form" action="insertMedicine"
              method="POST" id="form" modelAttribute="medicineForm">
              	
               <c:if test="${errorMsg != null }">
                    <div class="alert alert-danger">
                      <strong>${errorMsg }</strong>
                    </div>
                  </c:if>
              <input type="hidden" name="id" value="${medicineForm.id }">
              
               <ul class="list-group list-group-unbordered mb-3">
                    <li class="list-group-item"><b>Medicine Code</b>
                      <a class="float-right">${medicineForm.medicine_code }
                        <form:input path="medicine_code" type="hidden"
                          name="medicine_code"
                          value="${medicineForm.medicine_code}"
                          class="form-control" />
                    </a></li>
                    <li class="list-group-item"><b>Medicine Name</b> <a
                      class="float-right">${medicineForm.medicine_name } <form:input
                          path="medicine_name" type="hidden" name="medicine_name"
                          value="${medicineForm.medicine_name}"
                          class="form-control" /></a></li>
                          
                          
                          <li class="list-group-item"><b>Medicine Description</b> <a
                      class="float-right">${medicineForm.medicine_description} <form:input
                          path="medicine_description" type="hidden" name="medicine_description"
                          value="${medicineForm.medicine_description}"
                          class="form-control" /></a></li>
                          
                            <li class="list-group-item"><b>Category Name</b> <a
                      class="float-right">${medicineForm.category.category_name} <form:input
                          path="category.category_id" type="hidden" name="category_id"
                          value="${medicineForm.category.category_id}"
                          class="form-control" /></a></li>
                          
                           
                            <li class="list-group-item"><b>Unit In Stock</b> <a
                      class="float-right">${medicineForm.unit_in_stock} <form:input
                          path="unit_in_stock" type="hidden" name="unit_in_stock"
                          value="${medicineForm.unit_in_stock}"
                          class="form-control" /></a></li>
                          
                           <li class="list-group-item"><b>Price</b> <a
                      class="float-right">${medicineForm.amount} <form:input
                          path="amount" type="hidden" name="amount"
                          value="${medicineForm.amount}"
                          class="form-control" /></a></li>
                          
                          
                             <li class="list-group-item"><b>Image</b> <c:if
                        test="${medicineForm.image == ''}">
                        <form:input path="image" type="hidden"
                          name="image" value="${medicineForm.image}"
                          class="form-control" />
                      </c:if> <c:if test="${medicineForm.image != ''}">
                        <a class="float-right"> <img
                          src="${medicineForm.image}" id="medicine_image"
                          class="profile-user-img img-fluid img-circle" />
                          <form:input path="image" type="hidden"
                            name="image" value="${medicineForm.image}"
                            class="form-control" /></a>
                      </c:if></li>
                      
                          </ul>
                          
                 <button type="submit" class="btn"
                    name="addMedicine">Add</button>
                  <button type="submit" class="btn"
                    name="cancel">Cancel</button>
              </form:form>
</div>
</div>
</div>
	<script src="<c:url value="/resources/js/common.js"/>"></script>
</body>
</html>