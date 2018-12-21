
var sid = $('#saleChanceId').val();

$("#dg").edatagrid({
    url: ctx + '/cusDevPlan/queryCusDevPlansByParams?sid='+sid,
    saveUrl:ctx + '/cusDevPlan/saveOrUpdateCusDevPlan?saleChanceId='+sid,
    updateUrl:ctx + '/cusDevPlan/saveOrUpdateCusDevPlan?saleChanceId='+sid,
    destroyUrl:''
});

//增加一行
function addRow () {
    $("#dg").edatagrid("addRow");
}
//保存和更新
function saveOrUpdateCusDevPlan() {
    $("#dg").edatagrid("saveRow");
}
//删除
function delCusDevPlan () {
    deleteData('dg', ctx+'/cusDevPlan/delectCusDevPlanBatch', function () {
        $("#dg").edatagrid("reload");
    });
}

//开发成功
function updateSaleChanceDevResult (devResult) {
    $.ajax({
        url:ctx+'/cusDevPlan/updateSaleChanceDevResult',
        data:{
            devResult:devResult,
            sid:sid
        },
        success:function (data) {
            if(data.code == 200) {
                $.messager.alert('来自crm', data.msg, 'info', function () {
                    // 隐藏工具栏
                    $('#toolbar').hide();
                    // 把表格置为不可编辑
                    $('#dg').edatagrid("disableEditing")
                });
            }
        }
    });
}

//根据状态进行工具栏的隐藏
$(function () {
    var devResult = $('#devResult').val();
    if(devResult==2 || devResult==3){
        // 隐藏工具栏
        $('#toolbar').hide();
        // 把表格置为不可编辑
        $('#dg').edatagrid("disableEditing")
    }
})