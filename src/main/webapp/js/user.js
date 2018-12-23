//打开保存对话框
function openAddUserDailog () {
    openAddOrUpdateDlg('dlg','添加用户');
}
//打开更新对话框
function openModifyUserDialog () {
    openModifyDialog('dg', 'fm','dlg','更新用户');
}
//关闭对话框
function closeDlg () {
    closeDlgData('dlg');
}

//保存和更新
function saveOrUpdateUser () {
    saveOrUpdateData('fm',ctx+'/user/saveOrUpdateUser','dlg',function () {
        $('#dg').datagrid('reload');
    });
}
//用户删除
function deleteUser () {
    deleteData('dg',ctx+'/user/deleteUserBatch',function () {
        $('#dg').datagrid('reload');
    });
}

//查询
function queryUsersByParams() {
    $('#dg').datagrid('load', {
        userName: $('#userName').val(),
        email: $('#email').val(),
        phone: $('#phone').val()
    });
}