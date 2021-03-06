<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>部门调动</title>

    <link href="resources/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="resources/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
    <link href="resources/plugins/bootstrap-table-1.11.0/bootstrap-table.min.css" rel="stylesheet"/>
    <link href="resources/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
    <link href="resources/plugins/jquery-confirm/jquery-confirm.min.css" rel="stylesheet"/>
    <link href="resources/plugins/select2/css/select2.min.css" rel="stylesheet"/>
    <link href="resources/css/form2.css" rel="stylesheet"/>

    <link href="resources/css/common.css" rel="stylesheet"/>
</head>
<body>
<div id="main" style="margin-top: 10px">
    <!-- 未调职 or 已调职 -->
    <div id="searchCondition">
        <form action="${pageContext.request.contextPath }/changeDepartment" method="post" id="toolbar">
            <div class="btn-group">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="margin-bottom: 5px;">
                    未调度 <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="javascript:notDispatched">未调度查询</a></li>
                    <li><a href="javascript:isDispatched()">已调度查询</a></li>
                </ul>
            </div>
            <input type="text" class="form-control3" name="did" id="did" placeholder="部门编号"  style="width: 120px">
            <input type="text" class="form-control3" name="dname" id="dname" placeholder="部门名称"  style="width: 120px">
            <input type="text" class="form-control3" name="sid" id="sid" placeholder="员工编号"  style="width: 120px">
            <input type="text" class="form-control3" name="sname" id="sname" placeholder="员工姓名"  style="width: 120px">
            <input type="submit" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" value="搜索" style="margin-bottom: 5px;">
        </form>
    </div>
    <table id="table"></table>
</div>
<!-- 新增 -->
<div id="createDialog" class="crudDialog" hidden>
    <form>
        <div class="form-group">
            <select class="form-control" id="inputGroupSelect01">
                <option value="programing">开发部</option>
                <option value="selling">销售部</option>
            </select>
            <select class="form-control">
                <option value="front-end">前端工程师</option>
                <option value="test">测试工程师</option>
            </select>
        </div>
    </form>
</div>
<!--编辑信息-->
<div id="changeDataDialog" class="crudDialog" hidden>
    <form>
        <div class="form-group">
            <a style="font-size: 16px;">调入部门：</a><br>
            <select class="form-control2">
                <option value="programing">开发部</option>
                <option value="selling">销售部</option>
            </select>
            <select class="form-control2" style="margin-bottom: 10px">
                <option value="front-end">前端工程师</option>
                <option value="test">测试工程师</option>
            </select><br>
            <a style="font-size: 16px;">调入部门：</a><br>
            <select class="form-control2" style="margin-bottom: 10px">
                <option value="front-end">主动调动</option>
                <option value="test">被动调动</option>
                <option value="test">数据错误</option>
            </select><br>
            <a style="font-size: 16px;">调动时间：</a>
            <input type="date" class="form-control" placeholder="twitterhandle" style="width: 30%;margin-bottom: 10px;">
            <a style="font-size: 16px;">备注：</a><br>
            <textarea style="width: 100%;height: 100px">  </textarea>

        </div>
    </form>
</div>
<script src="resources/plugins/jquery.1.12.4.min.js"></script>
<script src="resources/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="resources/plugins/bootstrap-table-1.11.0/bootstrap-table.min.js"></script>
<script src="resources/plugins/bootstrap-table-1.11.0/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="resources/plugins/waves-0.7.5/waves.min.js"></script>
<script src="resources/plugins/jquery-confirm/jquery-confirm.min.js"></script>
<script src="resources/plugins/select2/js/select2.min.js"></script>

<script src="resources/js/changeDepartmnt.js"></script>
<script src="resources/js/common.js"></script>
<script>
    var $table = $('#table');
    $(function() {
        $(document).on('focus', 'input[type="text"]', function() {
            $(this).parent().find('label').addClass('active');
        }).on('blur', 'input[type="text"]', function() {
            if ($(this).val() == '') {
                $(this).parent().find('label').removeClass('active');
            }
        });
        // bootstrap table初始化
        // http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/
        $table.bootstrapTable({
            url: 'changeDepartment',
            height: getHeight(),
            striped: false,
            search: false,
            searchOnEnterKey: false,
            showRefresh: false,
            showToggle: false,
            showColumns: false,
            minimumCountColumns: 2,
            showPaginationSwitch: false,
            clickToSelect: true,
            detailView: true,
            detailFormatter: 'detailFormatter',
            pagination: true,
            paginationLoop: false,
            classes: 'table table-hover table-no-bordered',
            //sidePagination: 'server',
            //silentSort: false,
            smartDisplay: false,
            idField: 'id',
            sortName: 'id',
            sortOrder: 'desc',
            escape: true,
            //searchOnEnterKey: true,
            //idField: 'systemId',
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'state', checkbox: true},
                {field: 'id', title: '编号', sortable: true, halign: 'center'},
                {field: 'name', title: '姓名', sortable: true, halign: 'center'},
                {field: 'did', title: '部门编号', sortable: true, halign: 'center'},
                {field: 'dname', title: '部门名称', sortable: true, halign: 'center'},
                {field: 'pid', title: '岗位编号', sortable: true, halign: 'center'},
                {field: 'pname', title: '岗位名称', sortable: true, halign: 'center'},
                {field: 'action', title: '操作', halign: 'center', align: 'center', formatter: 'actionFormatter',  clickToSelect: false}
            ],
            queryParams:function (params) {
                var temp = {
                    limit:params.limit,//页面大小
                    offset:params.offset,//页码
                    did:$.trim($('#did').val()),
                    dname:$.trim($('#dname').val()),
                    dname:$.trim($('#dname').val()),
                    dname:$.trim($('#dname').val()),
                    dname:$.trim($('#dname').val()),
                };
            }
        }).on('all.bs.table', function (e, name, args) {
            $('[data-toggle="tooltip"]').tooltip();
            $('[data-toggle="popover"]').popover();
        });
    });
    function actionFormatter(value, row, index) {
        return [
            '<a class="like" href="javascript:changeData()" data-toggle="tooltip" title="编辑"><i class="glyphicon glyphicon-edit"></i></a>　',
        ].join('');
    }

    function detailFormatter(index, row) {
        var html = [];
        $.each(row, function (key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return html.join('');
    }

    //改变时间
    function changeData() {
        $.confirm({
            type: 'blue',
            animationSpeed: 300,
            title: '编辑部门调动信息',
            content: $('#changeDataDialog').html(),
            buttons: {
                confirm: {
                    text: '确认',
                    btnClass: 'waves-effect waves-button',
                    action: function () {
                        $.alert('确认');
                    }
                },
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
    }

    // 编辑
    function updateAction() {
        var rows = $table.bootstrapTable('getSelections');
        if (rows.length == 0) {
            $.confirm({
                title: false,
                content: '请至少选择一条记录！',
                autoClose: 'cancel|3000',
                backgroundDismiss: true,
                buttons: {
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        } else {
            $.confirm({
                type: 'blue',
                animationSpeed: 300,
                title: '部门岗位调动',
                content: $('#createDialog').html(),
                buttons: {
                    confirm: {
                        text: '确认',
                        btnClass: 'waves-effect waves-button',
                        action: function () {
                            $.alert('确认');
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        }
    }
    // 删除
    function deleteAction() {
        var rows = $table.bootstrapTable('getSelections');
        if (rows.length == 0) {
            $.confirm({
                title: false,
                content: '请至少选择一条记录！',
                autoClose: 'cancel|3000',
                backgroundDismiss: true,
                buttons: {
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        } else {
            $.confirm({
                type: 'red',
                animationSpeed: 300,
                title: false,
                content: '确认删除该员工记录吗？',
                buttons: {
                    confirm: {
                        text: '确认',
                        btnClass: 'waves-effect waves-button',
                        action: function () {
                            var ids = new Array();
                            for (var i in rows) {
                                ids.push(rows[i].systemId);
                            }
                            $.alert('删除：id=' + ids.join("-"));
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        }
    }
</script>
</body>
</html>