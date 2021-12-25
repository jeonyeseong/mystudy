<%@page import="com.kh.admin.vo.Statistics"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<% 	List<Statistics> stat = (List<Statistics>) request.getAttribute("stat");
	String title = (String) request.getAttribute("title");
	
	
 %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>관리자 페이지 통계</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/dashboard/">
    

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    
    <!-- Custom styles for this template -->
  </head>
  <body>
  <div class="container-fluid">
  <div class="row">
    <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
      <div class="position-sticky pt-3">
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" onclick="language()" style="cursor:pointer">
              <span data-feather="bar-chart-2"></span>
              선호 언어 통계
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" onclick="enroll()">
              <span data-feather="bar-chart-2"></span>
              가입일 통계
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" onclick="visitors()">
              <span data-feather="bar-chart-2"></span>
              방문 수 통계
            </a>
          </li>
        </ul>

       
      </div>
    </nav>

    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">관리자 페이지</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
        <h1 class="h2"><%= title == null ? "선호 언어" : title %></h1>
          <div class="btn-group me-2">

          </div>

        </div>
      </div>
      
      

      <canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>
      <script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script><script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous"></script>
		<br /><br /><br /><br /><br /><br />
<form 
action="<%= request.getContextPath() %>/admin/finderStat"
name="languageFrm"
method="GET">
<input type="hidden" name="searchType" value="language" />
</form>
<form 
action="<%= request.getContextPath() %>/admin/finderStat"
name="EnrolldateFrm"
method="GET">
<input type="hidden" name="searchType" value="enrolldate" />
</form>
<form 
action="<%= request.getContextPath() %>/admin/finderStat"
name="visitorsFrm"
method="GET">
<input type="hidden" name="searchType" value="visitors" />
</form>
      <script>
const language = () => {
	$(document.languageFrm).submit();
	$(admintitle).text("선호 언어");
	
}
const enroll = () => {
	$(document.EnrolldateFrm).submit();
	$(admintitle).text("날짜별 회원가입 수");
}
const visitors = () => {
	$("admintitle").empty();
	$(document.visitorsFrm).submit();
}

 (function () {
  'use strict'

  feather.replace({ 'aria-hidden': 'true' })

  // Graphs
  var ctx = document.getElementById('myChart')
  // eslint-disable-next-line no-unused-vars
  var myChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: [
<% for(Statistics s : stat){ %>
        '<%= s.getStat() %>',
<% } %>
      ],
      datasets: [{
        data: [
<% for(Statistics s : stat){ %>
        '<%= s.getCount() %>',
<% } %>
		'0'
        ],
        lineTension: 0,
        backgroundColor: 'transparent',
        borderColor: '#007bff',
        borderWidth: 4,
        pointBackgroundColor: '#007bff'
      }]
    },
    options: {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: false
          }
        }]
      },
      legend: {
        display: false
      }
    }
  })
})()
      </script>

  </body>
  
</html>

