<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<h2 class="cmn-ttl">Medicine List</h2>
				 <c:url var="addAction" value="/searchMedicine"></c:url>
				
				
					<c:forEach items="${MedicineList}" var="medicine" varStatus="loop">
					
					
					 <div class="col-md-4">
             <div class="product-single">
                <div class=" text-center productColumn">
                 <div class=" img-responsive productImage">
                     <img  style="width:50%;height: 50%   "  alt="image" src="<c:url value="/resources/images/${product.productId}.png"/>" />
                    
                 </div>
                 
             </div>
             
             
             <div class="product-desc">
                 <h2>${product.productModel}</h2>
                 <h3>${product.productPrice} USD</h3>
               
                 <a  href=" <c:url  value="/product/viewProduct/${product.productId}"/>" class="btn btn-info btn-lg">View Detail</a>
                
             </div>
             </div>
             
          </div> 
          
          
          
					
					
						<tr>
							<td>${medicine.id}</td>
							<td>${medicine.medicine_code}</td>
							<td> <a
                              href="<c:url value='detailMedicine?id=${medicine.id}'/>"
                              class="btn btn-info">${medicine.medicine_name}</a></td>
							<td>${medicine.medicine_description}</td>
							<td>${medicine.category.category_name}</td>
							<td>${medicine.amount}</td>
							<td>${medicine.unit_in_stock}</td>
							<td><a
								href="${pageContext.request.contextPath}/updateMedicine?id=${medicine.id}"
								class="cmn-link">Update</a> <a
								href="${pageContext.request.contextPath}/deleteMedicine?id=${medicine.id }"
								class="cmn-link"
								onclick="if (!(confirm('Are you sure you want to delete this item?'))) return false">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>