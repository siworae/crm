
//查询
function queryModulesByParams() {
    $('#dg').datagrid('load', {
        moduleName: $('#moduleName').val(),
        parentId: $('#pid').val(),
        grade: $('#grade').combobox('getValue'),
        optValue: $('#optValue').val()
    });
}

//格式化
function formateGrade(grade) {
    if (grade == 0){
        return"根菜单"
    }
    if (grade == 1){
        return"一级菜单"
    }
    if (grade == 2){
        return"二级菜单"
    }
}

//增加
function openAddModuleDailog () {
    openAddOrUpdateDlg('dlg',"增加模块");
}

$(function () {
    $("#parentMenu").hide();

    $('#grade02').combobox({
        onSelect: function(param){
            var grade = param.value;
            if (grade == 0){
                $("#parentMenu").hide();
            } else {
                $("#parentMenu").show();
                loadModuleByGrade(grade-1);
            }
        }
    });

    //创建下拉框
    function loadModuleByGrade(grade) {
        $('#parentId02').combobox({
            url:ctx+'/module/queryByGrade?grade='+grade,
            valueField:'id',
            textField:'moduleName',
            panelHeight:200
        });
    }
})

//关闭提示框
function closeDlg () {
    closeDlgData('dlg');
}

//保存
function saveOrUpdateModule() {
    saveOrUpdateData('fm',ctx+'/module/saveOrUpdateModule','dlg',function () {
        $('#dg').datagrid('reload');
    })
}

//删除
