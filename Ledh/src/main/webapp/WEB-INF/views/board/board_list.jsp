<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- 
변수에 값 할당
c:set var=변수명  value="값"  
--%>
<c:set var="CP" value="${pageContext.request.contextPath}" scope="page" />     

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"  content="width=device-width, initial-scale=1">
<link href="${CP}/resources/css/bootstrap.min.css" rel="stylesheet" >
<link rel="stylesheet" href="${CP}/resources/css/user.css">
<title>Insert title here</title>
<script src="${CP}/resources/js/bootstrap.bundle.min.js"></script>
<script src="${CP}/resources/js/jquery-3.7.1.js"></script>
<script src="${CP}/resources/js/eUtil.js"></script>
</head>
<body>
<div class="container">
    <!-- 제목 -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">게시등록</h1>
        </div>
    </div>    
    <!--// 제목 ----------------------------------------------------------------->

    
    <!-- 검색 -->
    <form action="#">
      <div class="row g-1 justify-content-end ">
          <label for="searchDiv" class="col-auto col-form-label">검색조건</label>
          <div class="col-auto">
              <select class="form-select" id="searchDiv" name="searchDiv">
                 <option value="">전체</option>
                 <option value="10">제목</option>
                 <option value="20">내용</option>
              </select>
          </div>
          <div class="col-auto">
              <input type="text" class="form-control" id="searchWord" name="searchWord" maxlength="100" placeholder="검색어를 입력 하세요">
          </div>   
          <div class="col-auto">
               <select class="form-select" id="pageSize" name="pageSize">
                   <option value="10">10</option>
                   <option value="20">20</option>
                   <option value="50">50</option>
               </select>
          </div>  
          <div class="col-auto"> <!-- 열의 너비를 내용에 따라 자동으로 설정 -->
            <input type="button" value="목록" class="btn btn-primary" >
            <input type="button" value="등록" class="btn btn-primary" >
          </div>              
      </div>
                           
    </form>
    <!--// 검색 ----------------------------------------------------------------->
    
    
    <!-- table -->
    <table class="table table-bordered border-primary table-hover table-striped">
      <thead>
        <tr >
          <th scope="col" class="text-center">NO</th>
          <th scope="col" class="text-center">제목</th>
          <th scope="col" class="text-center">등록일</th>
          <th scope="col" class="text-center">등록자</th>
          <th scope="col" class="text-center">조회수</th>
          <th scope="col" class="text-center" style="display: none;">SEQ</th>
        </tr>
      </thead>         
      <tbody>
        <c:choose>
            <c:when test="${ not empty list }">
              <!-- 반복문 -->
              <c:forEach var="vo" items="${list}" varStatus="status">
                <tr>
                  <td class="text-center"><c:out value="${vo.no}" escapeXml="true"/> </th>
                  <td class="text-left"><c:out value="${vo.title}" escapeXml="true"/></td>
                  <td class="text-center"><c:out value="${vo.modDt}" escapeXml="true"/></td>
                  <td><c:out value="${vo.modId}" /></td>
                  <td class="text-end"><c:out value="${vo.readCnt}" /></td>
                  <td  style="display: none;"><c:out value="${vo.seq}" /></td>
                </tr>              
              </c:forEach>
              <!--// 반복문 -->      
            </c:when>
            <c:otherwise>
               <tr>
                <td colspan="99" class="text-center">조회된 데이터가 없습니다..</td>
               </tr>        
            </c:otherwise>
        </c:choose>


      </tbody>
    </table>
    <!--// table --------------------------------------------------------------> 
    
    <!-- 페이징 -->           
    <div class="d-flex justify-content-center">
        <nav>
            <ul class="pagination">
                <li class="page-item"><a  class="page-link" href="#"><span>&laquo;</span></a></li>
                <li><a  class="page-link" href="#">1</a></li>
                <li><a  class="page-link" href="#">2</a></li>
                <li><a  class="page-link" href="#">3</a></li>
                <li><a  class="page-link" href="#">4</a></li>
                <li><a  class="page-link" href="#">5</a></li>
                <li><a  class="page-link" href="#">6</a></li>
                <li><a  class="page-link" href="#">7</a></li>
                <li><a  class="page-link" href="#">8</a></li>
                <li><a  class="page-link" href="#">9</a></li>
                <li><a  class="page-link" href="#">10</a></li>
                <li><a  class="page-link" href="#"><span>&raquo;</span></a></li>
            </ul>
        </nav>
    </div>
    <!--// 페이징 ---------------------------------------------------------------->
</div>

</body>
</html>