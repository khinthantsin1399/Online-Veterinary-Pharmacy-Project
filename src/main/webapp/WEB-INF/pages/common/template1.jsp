<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
response.addHeader("Pragma", "no-cache");
response.addDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Veterinary Pharmacy</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS only -->
<link rel="stylesheet" href='<c:url value="/resources/css/menu.css"/>'>
<link rel="stylesheet" href='<c:url value="/resources/css/reset.css"/>'>
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css">
<link rel="stylesheet"
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" />
<script
  src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/js/all.min.js"></script>

<link rel="stylesheet"
  href='<c:url value="/resources/plugins/fontawesome-free/css/all.min.css"/>'>
<link rel="stylesheet"
  href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<%-- <link rel="stylesheet"
  href='<c:url value="/resources/css/adminlte.css"/>'> --%>
<link rel="stylesheet" href='<c:url value="/resources/css/common.css"/>'>
<link
  href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
  rel="stylesheet">
<script src="<c:url value="/resources/plugins/jquery/jquery.js"/>"></script>
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="<c:url value="/resources/plugins/jquery/jquery.min.js"/>"></script>
<script
  src="<c:url value="/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<script src="<c:url value="/resources/js/adminlte.min.js"/>"></script>
<script src="<c:url value="/resources/js/common.js"/>"></script>
<script src="<c:url value="/resources/js/demo.js"/>"></script>
<script type="text/javascript"
  src="<c:url value="/resources/js/lib/bootstrap-datepicker.min.js"/>"></script>
<link rel="stylesheet"
  href='<c:url value="/resources/css/alt/bootstrap-datepicker3.css"/>' />

<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<script src="./js/font-awesome-6.1.1-all.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>

<body class="hold-transition sidebar-mini">
  <div class="wrapper">
    <tiles:insertAttribute name="header" />
    <tiles:insertAttribute name="body" />
    <tiles:insertAttribute name="footer" />
  </div>
</body>
<tiles:insertAttribute name="javascript" ignore="true" />
</html>