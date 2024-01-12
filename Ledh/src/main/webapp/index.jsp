<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/ehr/resources/js/jquery-3.7.1.js"></script>
<script src="/ehr/resources/js/eUtil.js"></script>
</head>
<body>

    여기는 index.jsp
  <!-- http://localhost:8080/ehr/index.jsp -->
  <!-- http://localhost:8080/ehr/ -->
<%--   <c:redirect url="/file/dragAndDropView.do" /> --%>
<%-- <c:redirect url="/file/uploadView.do" /> --%>
<%--    <c:redirect url="/login/loginView.do" /> --%>

<c:redirect url="/board/doRetrieve.do" />
</body>
</html>