//查询
function queryRolesByParams() {
    $('#dg').datagrid('load', {
        roleName: $('#roleName').val(),
        createDate: $('#time').datebox('getValue')
    });
}
//打开增加对话框
function openAddRoleDailog() {
    openAddOrUpdateDlg('dlg',"添加角色");
}
//打开更新对话框
function openModifyRoleDialog() {
    openModifyDialog('dg','fm','dlg',"更新角色");
}

//关闭对话框
function closeDlg() {
    closeDlgData('dlg');
}
//保存和更新
function saveOrUpdateRole () {
    saveOrUpdateData('fm',ctx+'/role/saveOrUpdateRole','dlg',function () {
        $('#dg').datagrid('reload');
    });
}
//删除
function deleteRole() {
    deleteData('dg',ctx+'/role/deleteRole',function () {
        $('#dg').datagrid('reload');
    });
}

//打开权限窗口
function openRelationPermissionDialog() {
    var rows = $('#dg').datagrid("getSelections");
    //console.log(rows);
    if(rows.length==0){
        $.messager.alert('来自Crm',"请选择一个角色进行授权");
        return;
    }
    if(rows.length>1){
        $.messager.alert('来自Crm',"只能选择一个角色进行授权");
        return;
    }
    loadData(rows[0].id);
}

var treeObj;
//zTree初始化
function loadData(roleId) {
    //设置roleId到隐藏域
    $("#roleId").val(roleId);

    $.ajax({
        url:ctx+'/role/queryPermissionByRoleId?roleId='+roleId,
        type:'post',
        success:function (data) {
            // console.log(data);
            //zTree 设置
            var setting = {
                check: {
                    enable: true,
                    chkboxType:  { "Y" : "ps", "N" : "ps" }
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onCheck: zTreeOnCheck
                }
            };
            //zTree节点信息
            var zNodes = data;
            //初始化
            treeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            //打开窗口
            openAddOrUpdateDlg('permissionDlg', '角色授权');
        }
    });
}

//zTree勾选的回调函数
function zTreeOnCheck() {
    var nodes = treeObj.getCheckedNodes(true);
    // console.log(nodes);
    var moduleIds = "";
    for (var i = 0; i < nodes.length; i++) {
        moduleIds += "moduleIds="+nodes[i].id+"&";
    }
    $("#moduleIds").val(moduleIds);
}

//关闭权限窗口
function closePermissionDlg() {
    closeDlgData('permissionDlg');
}

//授权
function doGrant() {
    var roleId = $("#roleId").val();
    var moduleIds = $("#moduleIds").val();
    $.ajax({
        url:ctx+'/permission/doGrant?'+moduleIds,
        type:'post',
        data:{
            roleId:roleId
        },
        success:function (data) {
            if (data.code == 200){
                $.messager.alert('来自Crm', data.msg, 'info', function () {
                    closeDlgData('permissionDlg');
                    // 刷新页面
                    $('#dg').datagrid('reload');
                });
            }else{
                $.messager.alert("来自crm",data.msg,"error");
            }
        }
    });
}