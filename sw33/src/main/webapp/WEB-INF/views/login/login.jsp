<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />     
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
    <style>
        .login-form {
            width: 340px;
            margin: 50px auto;
        }
        .login-form form {
            margin-bottom: 15px;
            background: #f7f7f7;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            padding: 30px;
        }
        .login-form h2 {
            margin: 0 0 15px;
        }
        .form-control, .btn {
            min-height: 38px;
            border-radius: 2px;
        }
        .btn {        
            font-size: 15px;
            font-weight: bold;
        }
    </style>
<title><spring:message  code="login.title"/></title>
<script type="text/javascript">
 $(document).ready(function(){
	 
	 
	 
	 console.log( "ready!" );
	 //koBTN
	 $("#koBTN").on("click",function(e){
         console.log( "enBTN click!" );
         changeLocale("ko");
		 
	 });
	 
	 function changeLocale(lang){
         $.ajax({
             type: "GET",
             url:"/ehr/login/localeChange.do",
             asyn:"true",
             dataType:"json",
             data:{
                 "lang":lang
             },
             success:function(data){//통신 성공
                 console.log("success data:"+data.msgId);
                 console.log("success data:"+data.msgContents);
                 if(null != data ){
                     alert(data.msgContents);
                     window.location.reload();
                 }
             },
             error:function(data){//실패시 처리
                 console.log("error:"+data);
             },
             complete:function(data){//성공/실패와 관계없이 수행!
                 console.log("complete:"+data);
             }
         });
	 }
	 
	 
	 $("#enBTN").on("click",function(e){
		 console.log( "enBTN click!" );
		 changeLocale("en");	 
	 });
	 
	 $("#doLogin").on("click",function(e){
		 console.log( "doLogin click!" );
		 
		 let userId = document.querySelector("#userId").value;
		 if(eUtil.isEmpty(userId)==true){
			 alert('아이드를 입력 하세요.');
			 document.querySelector("#userId").focus();
			 return;
		 }
		 console.log( "userId:"+userId );
		 
         let password = document.querySelector("#password").value;
         if(eUtil.isEmpty(password)==true){
             alert('비번을 입력 하세요.');
             document.querySelector("#password").focus();
             return;
         }
         console.log( "password:"+password );
		 
         if(confirm("로그인 하시겠습니까?")===false) return;
         
         $.ajax({
             type: "POST",
             url:"/ehr/login/doLogin.do",
             asyn:"true",
             dataType:"json",
             data:{
            	 "userId": userId,
            	 "password": password
             },
             success:function(data){//통신 성공
                 console.log("data.msgId:"+data.msgId);
                 console.log("data.msgContents:"+data.msgContents);
                 
                 if("10" == data.msgId){
                	 alert(data.msgContents);
                	 document.querySelector("#userId").focus();
                 }else if("20" == data.msgId){
                     alert(data.msgContents);
                     document.querySelector("#password").focus();                 
                 }else if("30" == data.msgId){
                	 alert(data.msgContents);
                	 window.location.href = "/ehr/main/mainView.do";
                 }
             },
             error:function(data){//실패시 처리
                 console.log("error:"+data);
             },
             complete:function(data){//성공/실패와 관계없이 수행!
                 console.log("complete:"+data);
             }
         });         
         
         
	 });//--#doLogin
	 
	 
 });//--document ready
</script>   
</head>
<body>
    <div class="login-form"> 
        <div class="row mt-5 justify-content-center">    
         <div class="col-auto">
            <input class="btn btn-primary" value="<spring:message  code='login.language.ko'/>" id="koBTN">
            <input class="btn btn-primary" value="<spring:message  code='login.language.en'/>" id="enBTN">
         </div>
        </div>
        <form action="/ehr/login/doLogin.do" method="post">
            <h2 class="text-center"><spring:message  code="login.title"/></h2><!-- 로그인 -->
            <div class="form-group">
                <input type="text" class="form-control" id="userId" name="userId" placeholder="<spring:message  code='login.id'/>" required="required" value="p99">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="password" name="password" placeholder="<spring:message  code='login.password'/>" required="required" value="4321">
            </div>
            <div class="form-group">
                <button type="button" class="btn btn-primary btn-block" id="doLogin"><spring:message  code='login.loginButton'/></button>
            </div>
        </form>
        <p class="text-center"><a href="#">계정 만들기</a></p>
    </div> 
</body>
</html>