
var sid = $('#saleChanceId').val();

$("#dg").edatagrid({
    url: ctx + '/cusDevPlan/queryCusDevPlansByParams?sid='+sid,
    saveUrl:ctx + '/cusDevPlan/saveOrUpdateCusDevPlan?saleChanceId='+sid,
    updateUrl:ctx + '/cusDevPlan/saveOrUpdateCusDevPlan?saleChanceId='+sid,
    destroyUrl:''
});

function addRow () {
    $("#dg").edatagrid("addRow");
}
function saveOrUpdateCusDevPlan() {
    $("#dg").edatagrid("saveRow");
}