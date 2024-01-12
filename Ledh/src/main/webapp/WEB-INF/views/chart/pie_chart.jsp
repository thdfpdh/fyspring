<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />     
<!DOCTYPE html>
<html> 
<head>  
<jsp:include page="/WEB-INF/cmn/header.jsp"></jsp:include>
<title>파이차트</title>

 <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {
        //ajax call
        $.ajax({
            type: "GET",
            url:"/ehr/chart/viewPieChart.do",
            asyn:"false",
            dataType:"json",
            data:{  
            },
            success:function(chartData){//통신 성공
                console.log("success data:"+chartData);
                let data = new google.visualization.DataTable();
                data.addColumn('string', 'Topping');
                data.addColumn('number', 'Slices');  
                data.addRows(chartData);
                
                // Set chart options     
                let options = {'title':'어제 먹은 피자',
                               'width':800,
                               'height':600};

                // Instantiate and draw our chart, passing in some options.
                let chart = new google.visualization.PieChart(document.getElementById('chart_div'));
                chart.draw(data, options);                
            },
            error:function(data){//실패시 처리
                console.log("error:"+data);
            },
            complete:function(data){//성공/실패와 관계없이 수행!
                console.log("complete:"+data);
            }
        });        
        
        // Create the data table.

        


      }
    </script>
  </head>

  <body>
    <div class="container">
	    <!--Div that will hold the pie chart-->
	    <div id="chart_div"></div>
	    <jsp:include page="/WEB-INF/cmn/footer.jsp"></jsp:include>
    </div>     
  </body>
</html>