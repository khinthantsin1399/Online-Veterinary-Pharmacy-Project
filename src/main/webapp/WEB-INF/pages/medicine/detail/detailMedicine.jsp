<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content-wrapper">
  <section class="content">
    <div class="row">
      <div class="col-12">
        <div class="forms-mr">
          <div class="col-sm-6 col-md-6 form-detail">
            <div class="card card-primary card-outline">
              <div class="card-body box-profile">
                <div class="text-center">
                  <c:if test="${empty detailMedicine.image }">
                    <img class="profile-user-img img-fluid img-circle"
                      src="<c:url value='/resources/img/noimage.png'/>"
                      alt="User profile picture">
                  </c:if>
                  <c:if test="${not empty detailMedicine.image }">
                    <img class="profile-user-img img-fluid img-circle"
                      src="${detailMedicine.image}" />
                  </c:if>
                </div>
                <h3 class="profile-username text-center">${detailMedicine.medicine_name}</h3>
                <ul class="list-group list-group-unbordered mb-3">
                  <li class="list-group-item"><b>Price</b> <a
                    class="float-right">${detailMedicine.amount}</a></li>
                  
                  <li class="list-group-item"><b>Category</b> <a
                    class="float-right">${detailMedicine.category.category_name}</a></li>
                  <li class="list-group-item"><b>Description</b>
                    <a class="float-right">${detailMedicine.medicine_description}</a></li>
                  
                </ul>
                
                <a href="${pageContext.request.contextPath}/medicineList"
                  class="btn btn-block btn-secondary">Back</a>
              
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</div>