<%@ page import="com.staffmanage.entity.staffxun" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>离职员工报表</title>

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
			<form class="form-inline " style="padding: 3% 5% 0% 5%;" role="form">
				<div class="input-group">
					<span class="input-group-addon">开始时间</span>
					<input type="date" class="form-control"  name="beginTime" id ="beginTime"placeholder="twitterhandle">
				</div>
				<div class="input-group">
					<span class="input-group-addon">结束时间</span>
					<input type="date" class="form-control" name = "endTime" id="endTime" placeholder="twitterhandle">
				</div>

				<div class="form-group ">
					<input type="text" class="form-control" name="positionName" id="positionName" placeholder="请输入部门名称">
				</div>
				<%--<button type="submit" class="btn btn-default">查找</button>--%>
				<a  href="javascript:;"  onclick="Search()" class="btn btn-default"> 查找</a>
				<button type="button" class="btn btn-primary ">导出为EXCEL</button>
			</form>
			<!-- 	<div id="toolbar">
 		<a class="waves-effect waves-button" href="javascript:;" onclick="createAction()"><i class="zmdi zmdi-plus"></i> 新增用户</a>
		<a class="waves-effect waves-button" href="javascript:;" onclick="updateAction()"><i class="zmdi zmdi-edit"></i> 编辑用户</a>
		<a class="waves-effect waves-button" href="javascript:;" onclick="deleteAction()"><i class="zmdi zmdi-close"></i> 删除用户</a> 
	</div> -->
			<table id="table" style="text-align: center;"></table>
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
			var $table = $('#table');
			$(function() {
				// $(document).on('focus', 'input[type="text"]', function() {
				// 	$(this).parent().find('label').addClass('active');
				// }).on('blur', 'input[type="text"]', function() {
				// 	if ($(this).val() == '') {
				// 		$(this).parent().find('label').removeClass('active');
				// 	}
				// });
				// bootstrap table初始化
				// http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/
				$table.bootstrapTable({
					url: 'getseparatedHiresServlet',
					height: getHeight(),
					striped: true,
					// search: true,
					// searchOnEnterKey: true,
					// showRefresh: true,
					// showToggle: true,
					// showColumns: true,
					minimumCountColumns: 2,
					// showPaginationSwitch: true,
					clickToSelect: true,
					detailView: false,
					detailFormatter: 'detailFormatter',
					pagination: true,
					paginationLoop: false,
					classes: 'table table-hover table-no-bordered',
					// sidePagination: 'server',
					// silentSort: false,
					smartDisplay: false,
					idField: 'id',
					sortName: 'id',
					sortOrder: 'desc',
					escape: true,
					searchOnEnterKey: true,
					idField: 'systemId',
					maintainSelected: true,
					toolbar: '#toolbar',
					exportDataType: 'all', //'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据
					showExport: true, //是否显示导出按钮
					buttonsAlign: "right", //按钮位置
					exportTypes: ['excel'], //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']

					columns: [{
							field: 'id',
							title: '序号',
							sortable: true,
							halign: 'center'
						},
						{
							field: 'depart',
							title: '部门名称',
							sortable: false,
							halign: 'center'
						},
						{
							field: 'post',
							title: '岗位名称',
							sortable: false,
							halign: 'center'
						},
						{
							field: 'name',
							title: '姓名',
							sortable: false,
							halign: 'center'
						},
						{
							field: 'sex',
							title: '性别',
							sortable: false,
							halign: 'center'
						},
						{
							field: 'qdate',
							title: '离职日期',
							sortable: true,
							halign: 'center'
						},
						{
							field: 'dreason',
							title: '离职原因',
							sortable: false,
							halign: 'center'
						},
					]
				}).on('all.bs.table', function(e, name, args) {
					$('[data-toggle="tooltip"]').tooltip();
					$('[data-toggle="popover"]').popover();
				});
			});

			function actionFormatter(value, row, index) {
				return [
					'<a class="like" href="javascript:void(0)" data-toggle="tooltip" title="Like"><i class="glyphicon glyphicon-heart"></i></a>　',
					'<a class="edit ml10" href="javascript:void(0)" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　',
					'<a class="remove ml10" href="javascript:void(0)" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>'
				].join('');
			}
			$(document).ready(function() {
				var time = new Date();
				var day = ("0" + time.getDate()).slice(-2);
				var month = ("0" + (time.getMonth() + 1)).slice(-2);
				var today = time.getFullYear() + "-" + (month) + "-" + (day);
				
				$('#endTime').val(today);//开始日期的id
				var nowMonthFirstDay=time.getFullYear() + "-" + (month) + "-" + ("01");
				$('#beginTime').val(nowMonthFirstDay);//结束日期的id
				
			})
            function Search() {
                var beginTime=$('#beginTime').val();
                var endTime=$('#endTime').val();
				var positionName=$('#positionName').val();

                $('#table').bootstrapTable('refresh',
                    {
                        url:'getseparatedHiresServlet?beginTime='+beginTime+'&endTime='+endTime +'&positionName='+positionName
                    }
                )

            }
		</script>
	</body>
</html>
