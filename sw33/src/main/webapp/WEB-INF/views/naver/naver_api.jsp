<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />     
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>naver검색</title>
<script>
   document.addEventListener("DOMContentLoaded",function(){
	  console.log("DOMContentLoaded");
	  //doRetrieve
	  const doRetrieveBTN =document.querySelector("#doRetrieve");
	  const searchDivSelect =document.querySelector("#searchDiv");

	  
	  
	  //blog,news,... table view제어
	  $("#blogTable").css("display", "");
	  $("#newsTable").css("display", "none");
	  $("#bookTable").css("display", "none");
	  
      searchDivSelect.addEventListener("change",function(e){
             console.log("searchDivSelect change:");
             
             $("#blogTable").css("display", "none");
             $("#newsTable").css("display", "none");             
             $("#bookTable").css("display", "none");

             let selectValue = searchDivSelect.value;
             console.log("selectValue change:"+selectValue);
             
             if(10 == selectValue){//blog
            	 $("#blogTable").css("display", "");
             }else if(20 == selectValue){
            	 $("#newsTable").css("display", "");          
             }else if(30 == selectValue){
                 $("#bookTable").css("display", "");          
             }        
      });


 
	   
	  doRetrieveBTN.addEventListener("click",function(e){
		  console.log("doRetrieveBTN click:");
		  
		  //검색어 필수 처리
		  const searchWordValue = document.querySelector("#searchWord").value;
		  if(eUtil.isEmpty(searchWordValue) == true){
			  alert("검색어를 입력 하세요.");
			  document.querySelector("#searchWord").focus();
			  return;
		  }
		  
		  console.log("searchDiv:"+document.querySelector("#searchDiv").value);
		  
	        $.ajax({
	            type: "GET",
	            url:"/ehr/naver/doRetrieve.do",
	            asyn:"true",
	            dataType:"json",
	            data:{
	                "pageNo": "1",
	                "pageSize": document.querySelector("#pageSize").value,
	                "searchDiv": document.querySelector("#searchDiv").value,
	                "searchWord": document.querySelector("#searchWord").value  
	            },
	            success:function(data){//통신 성공
	                console.log("data.items:"+data.items);
	                console.log("data.items.length:"+data.items.length);
	                
	                let htmlData = "";
                    let selectValue = searchDivSelect.value;
                    console.log("selectValue change:"+selectValue);
                    
	                if(null != data &&  data.items.length > 0 ){
		                
		                //기존 데이터 삭제
		                //$("#blogTable>tbody").empty();
		                document.querySelector("#blogTable>tbody").innerHTML = "";
		                document.querySelector("#newsTable>tbody").innerHTML = "";
		                document.querySelector("#bookTable>tbody").innerHTML = "";
		                
		                if(10 == selectValue){//blog
	                        data.items.forEach(function(item,idx){
	                            console.log(idx,item.title, item.link);
	                            let i = idx+1;
	                            htmlData +="<tr>";
	                            htmlData +="<td class='text-center'>"+i+"</td>"; 
	                            htmlData +="<td class='text-left'>"+item.title+"</td>"; 
	                            htmlData +="<td class='text-left'>"+item.link+"</td>"; 
	                            htmlData +="<td class='text-left'>"+item.description+"</td>";
	                            htmlData +="<td class='text-center'>"+item.bloggername+"</td>";
	                            htmlData +="<td class='text-center'>"+item.postdate+"</td>";
	                            htmlData +="</tr>\n";
	                        });	                	
		                
	                        $("#blogTable>tbody").append(htmlData);
		                }else if(20 == selectValue){
                            data.items.forEach(function(item,idx){
                                console.log(idx,item.title, item.link);
                                let i = idx+1;
                                htmlData +="<tr>";
                                htmlData +="<td class='text-center'>"+i+"</td>"; 
                                htmlData +="<td class='text-left'><a href='"+item.link+"'>" +item.title+"</a></td>";
                                htmlData +="<td class='text-left'>"+item.description+"</td>";
                                htmlData +="<td class='text-center'>"+item.pubDate+"</td>";
                                htmlData +="</tr>\n";
                            });
                            //$("#newsTable>tbody").append(htmlData);
                            document.querySelector("#newsTable>tbody").innerHTML = htmlData;
		                }else if(30 == selectValue){
		                	 data.items.forEach(function(item,idx){
	                                console.log(idx,item.title, item.link);
	                                let i = idx+1;
	                                htmlData +="<tr>";
	                                htmlData +="<td class='text-center'>"+i+"</td>"; 
	                                htmlData +="<td class='text-left'><a href='"+item.link+"'>" +item.title+"</a></td>";
	                                htmlData +="<td class='text-left'>"+item.description+"</td>";
	                                htmlData +="<td class='text-left'>"+item.author+"</td>";
	                                htmlData +="<td class='text-center'>"+item.pubdate+"</td>";
	                                htmlData +="</tr>\n";
		                	 });       
		                	 document.querySelector("#bookTable>tbody").innerHTML = htmlData;
		                	
		                }
		                
		                

		                //table에 추가
		               
		                
	                }else{
	                	if(10 == selectValue){//blog
	                        htmlData +="<tr>";
	                        htmlData +=' <td colspan="99" class="text-center">조회된 데이터가 없습니다.</td>';
	                        htmlData +="</tr>\n";
	                        $("#blogTable>tbody").append(htmlData);	                		
	                	}else if(20 == selectValue){//blog
                            htmlData +="<tr>";
                            htmlData +=' <td colspan="99" class="text-center">조회된 데이터가 없습니다.</td>';
                            htmlData +="</tr>\n";
                            $("#newsTable>tbody").append(htmlData);    	                		
	                	}

	                }
	                
	                
	            },
	            error:function(data){//실패시 처리
	                console.log("error:"+data);
	            },
	            complete:function(data){//성공/실패와 관계없이 수행!
	                console.log("complete:"+data);
	            }
	        });		  
	  });
	   
   });//--DOMContentLoaded end
   
</script>
</head>
<body>
    <div class="container">
    <!-- 제목 -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">naver 검색</h1>
        </div>
    </div>    
    <!--// 제목 ----------------------------------------------------------------->    
    <form action="#" method="get" id="boardFrm" name="boardFrm">
        <input type="hidden" name="pageNo" id="pageNo" />
        <div class="row g-1 justify-content-end ">
          <label for="searchDiv" class="col-auto col-form-label">검색조건</label>
          <div class="col-auto">
              <select class="form-select pcwk_select" id="searchDiv" name="searchDiv">
                     <c:forEach var="vo" items="${naverSearch }">
                        <option value="<c:out value='${vo.detCode}'/>"  ><c:out value="${vo.detName}"/></option>
                     </c:forEach>
              </select>
          </div>  
          <div class="col-auto">
               <input type="text" class="form-control" id="searchWord" name="searchWord" maxlength="100" placeholder="검색어를 입력 하세요" ">
          </div> 
          <div class="col-auto"> 
               <select class="form-select" id="pageSize" name="pageSize">
                  <c:forEach var="vo" items="${pageSize }">
                    <option value="<c:out value='${vo.detCode }' />"   ><c:out value='${vo.detName}' /></option>
                  </c:forEach>
               </select>  
          </div>          
          <div class="col-auto "> <!-- 열의 너비를 내용에 따라 자동으로 설정 -->
               <input type="button" value="목록" class="btn btn-primary"  id="doRetrieve">  
          </div>        
        </div>
    </form>
    <!-- blog -->
    <table class="table table-bordered border-primary table-hover table-striped" id="blogTable">
      <thead>
        <tr >
          <th scope="col" class="text-center col-lg-1">NO</th>
          <th scope="col" class="text-center col-lg-4">제목</th>
          <th scope="col" class="text-center col-lg-2">포스트URL</th>
          <th scope="col" class="text-center col-lg-3">내용</th>
          <th scope="col" class="text-center col-lg-1">이름</th>
          <th scope="col" class="text-center col-lg-1">작성일</th>
        </tr>
      </thead>     
      <tbody>
      </tbody>
    </table>
    <!--// blog --------------------------------------------------------------->
    
    <!-- news -->
    <table class="table table-bordered border-primary table-hover table-striped" id="newsTable">
      <thead>
        <tr >
          <th scope="col" class="text-center col-lg-1">NO</th>
          <th scope="col" class="text-center col-lg-4">제목</th>
          <th scope="col" class="text-center col-lg-6">내용</th>
          <th scope="col" class="text-center col-lg-1">제공일시</th>
        </tr>
      </thead>     
      <tbody>
      </tbody>
    </table>
    <!--// news --------------------------------------------------------------->
        
    <!-- book -->
    <table class="table table-bordered border-primary table-hover table-striped" id="bookTable">
      <thead>
        <tr >
          <th scope="col" class="text-center col-lg-1">NO</th>
          <th scope="col" class="text-center col-lg-4">책 제목</th>
          <th scope="col" class="text-center col-lg-4">책 소개</th>
          <th scope="col" class="text-center col-lg-2">저자</th>
          <th scope="col" class="text-center col-lg-1">출간일</th>
        </tr>
      </thead>     
      <tbody>
      </tbody>
    </table>
    <!--// news --------------------------------------------------------------->
            
    <jsp:include page="/WEB-INF/cmn/footer.jsp"></jsp:include>
    </div>
</body>
</html>