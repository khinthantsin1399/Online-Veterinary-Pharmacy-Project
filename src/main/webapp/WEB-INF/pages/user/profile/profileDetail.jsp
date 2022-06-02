<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
</head>
<body>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card mt-5">
          <h2 class="text-primary text-center mb-4">Profile Detail
            Form</h2>
          <input type=hidden name="id" value="${profileDetailForm.id}" />
          <div class="form-group w-75" style="margin: 0 auto;">
            <label for="name">User Name :</label> ${ profileDetailForm.username }
          </div>
          <div class="form-group w-75" style="margin: 0 auto;">
            <label for="email">Email :</label> ${ profileDetailForm.email }
          </div>
          <div class="form-group w-75" style="margin: 0 auto;">
            <c:if test="${ profileDetailForm.type == 0}">
              <label for="type">Type :</label>
            PHARMACIST
            </c:if>
            <c:if test="${ profileDetailForm.type == 1}">
              <label for="type">Type :</label>
            ADMIN
            </c:if>
            <c:if test="${ profileDetailForm.type == 2}">
              <label for="type">Type :</label>
            User
            </c:if>
          </div>
          <div class="form-group w-75" style="margin: 0 auto;">
            <label for="phone">Phone No:</label> ${ profileDetailForm.phone }
          </div>
          <div class="form-group w-75" style="margin: 0 auto;">
            <label for="address">Address :</label> ${ profileDetailForm.address }
          </div>
          <div class="col-md-12 text-center mt-3">
            <a
              href="${pageContext.request.contextPath}/profile/edit?id=${profileDetailForm.id}">
              <button type="submit" name="editProfile"
                class="btn btn-primary align-center">Edit
                Confirm</button>
            </a> <a href="${pageContext.request.contextPath}/medicineList">
              <button type="submit" name="cancel"
                class="btn btn-secondary" value="Cancel">Back</button> <!-- onclick="history.back(2);" -->
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>