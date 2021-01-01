<%@ page import="com.staffmanage.dao.persionalReport" %>
<%@ page import="java.util.List" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<%--designer:zhaowenxun--%>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>人事月报</title>

		<link href="resources/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet" />
		<link href="resources/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet" />
		<link href="resources/plugins/bootstrap-table-1.11.0/bootstrap-table.min.css" rel="stylesheet" />
		<link href="resources/plugins/waves-0.7.5/waves.min.css" rel="stylesheet" />
		<link href="resources/plugins/jquery-confirm/jquery-confirm.min.css" rel="stylesheet" />
		<link href="resources/plugins/select2/css/select2.min.css" rel="stylesheet" />
		<link href="resources/css/common.css" rel="stylesheet" />


	</head>
	<body>
		<div id="main">
			<form method="get" class="form-inline " style="padding: 3% 5% 3% 5%;" role="form">
				<div class="input-group">
					<span class="input-group-addon">开始时间</span>
					<input type="month" class="form-control"  name ="beginTime" id ="beginTime" placeholder="twitterhandle">
				</div>
				<button type="submit" class="btn btn-default">查找</button>
				<button type="button" class="btn btn-primary ">导出为EXCEL</button>
			</form>
			<!-- 	<div id="toolbar">
 		<a class="waves-effect waves-button" href="javascript:;" onclick="createAction()"><i class="zmdi zmdi-plus"></i> 新增用户</a>
		<a class="waves-effect waves-button" href="javascript:;" onclick="updateAction()"><i class="zmdi zmdi-edit"></i> 编辑用户</a>
		<a class="waves-effect waves-button" href="javascript:;" onclick="deleteAction()"><i class="zmdi zmdi-close"></i> 删除用户</a> 
	</div> -->
	<table style="text-align: center;" class="table table-bordered" >
	  <thead>
	    <tr>
	      <th rowspan="2"><h3>部门名称</h3></th>
	      <th colspan="6"><h4>人数统计</h4></th>
	      <th colspan="4"><h4>学历统计</h4></th>
	    </tr>
		<tr>
		  <th>月初人数</th>
		  <th>月末人数</th>
		  <th>本月新入职</th>
		  <th>本月离职</th>
		  <th>本月调入</th>
		  <th>本月调出</th>
		  <th>研究生</th>
		  <th>本科</th>
		  <th>大专</th>
		  <th>高中及以下</th>
		</tr>
	  </thead>
		<%  List<persionalReport>  pRep=(List<persionalReport>)request.getAttribute("pRep");

			if(pRep==null)
			{

		%>
		<tr><td colspan="11"><font color="red">数据加载中请稍等......</font></td></tr>
		<%}else{
			for(persionalReport s:pRep)
			{

		%>
		<tr >
			<td><%=s.getDepartment()%></td>
			<td><%=s.getMonthBeginNum()%></td>
			<td><%=s.getMonthEndnum()%></td>
			<td><%=s.getThisMonthIn()%></td>
			<td><%=s.getThisMonthOut()%></td>
			<td><%=s.getGetThisMonthChangeIn()%></td>
			<td><%=s.getGetThisMonthChangeOut()%></td>
			<td><%=s.getGraduteStudent()%></td>
			<td><%=s.getUngraduteStudent()%></td>
			<td><%=s.getJuniorStudent()%></td>
			<td><%=s.getSeninoHighStudent()%></td>
		</tr>
		<%}} %>
	  </tbody>
	</table>
			<!-- <table id="table" style="text-align: center;"></table> -->
		</div>

		<script src="resources/plugins/jquery.1.12.4.min.js"></script>
		<script src="resources/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
		<script src="resources/plugins/bootstrap-table-1.11.0/bootstrap-table.min.js"></script>
		<script src="resources/plugins/bootstrap-table-1.11.0/locale/bootstrap-table-zh-CN.min.js"></script>
		<script src="resources/plugins/waves-0.7.5/waves.min.js"></script>
		<script src="resources/plugins/jquery-confirm/jquery-confirm.min.js"></script>
		<script src="resources/plugins/select2/js/select2.min.js"></script>
		<script src="resources/js/common.js"></script>
		<script>
			$(document).ready(function() {
				var time = new Date();
				var month = ("0" + (time.getMonth() + 1)).slice(-2);
				var today = time.getFullYear() + "-" + (month) ;
				var nowMonthFirstDay=time.getFullYear() + "-" + (month);		
				// $('#endTime').val(today);
				$('#beginTime').val(nowMonthFirstDay);
				
			})


		</script>
	</body>
</html>
