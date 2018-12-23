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
        $.messager.alert('来自Crm',"请选择一条记录进行授权");
        return;
    }
    if(rows.length>1){
        $.messager.alert('来自Crm',"只能选择一条记录进行授权");
        return;
    }
    loadData();
}

//ztree初始化
function loadData() {

    var setting = {
        check: {
            enable: true,
            chkboxType:  { "Y" : "ps", "N" : "ps" }
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };

    var zNodes =[
        { id:1, pId:0, name:"随意勾选 1", checked:true},
        { id:11, pId:1, name:"随意勾选 1-1"},
        { id:111, pId:11, name:"随意勾选 1-1-1"},
        { id:112, pId:11, name:"随意勾选 1-1-2"},
        { id:12, pId:1, name:"随意勾选 1-2"},
        { id:121, pId:12, name:"随意勾选 1-2-1"},
        { id:122, pId:12, name:"随意勾选 1-2-2"},
        { id:2, pId:0, name:"随意勾选 2", checked:true},
        { id:21, pId:2, name:"随意勾选 2-1"},
        { id:22, pId:2, name:"随意勾选 2-2"},
        { id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
        { id:222, pId:22, name:"随意勾选 2-2-2"},
        { id:23, pId:2, name:"随意勾选 2-3"}
    ];

    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    });

    openAddOrUpdateDlg('permissionDlg', '角色授权');
}

//关闭权限窗口
function closePermissionDlg() {
    closeDlgData('permissionDlg');
}