
function formatterDevResult(value, row, index) {
    // value 当前值
    // row 当前行的值
    // index 当前行的索引
    if (value == 0) {
        return "未开发";
    }
    if (value == 1) {
        return "开发中";
    }
    if (value == 2) {
        return "开发成功";
    }
    if (value == 3) {
        return "开发失败";
    }
}

function formatterOp(value, row, index) {
    var devResult = row.devResult;
/*    if (devResult == 2 || devResult == 3){
        return "查看详情";
    } else {
        return "开发";
    }*/
    if(devResult==0||devResult==1){
        var href="javascript:openSaleChanceInfoDialog("+'"开发机会数据"'+","+row.id+")";
        return "<a href='"+href+"'>开发</a>";
    }
    if(devResult==2||devResult==3){
        var href="javascript:openSaleChanceInfoDialog("+'"详情机会数据"'+","+row.id+")";
        return "<a href='"+href+"'>查看详情</a>";
    }
}

function openSaleChanceInfoDialog(title,id) {
    /**
     * 打开新的选项卡
     */
    window.parent.openTab(title+"_"+id,ctx+"/cusDevPlan/index?sid="+id);
}


function querySaleChancesByParams () {
    $('#dg').datagrid('load', {
        customerName: $('#customerName').val(),
        devResult: $('#devResult').combobox('getValue'),
        createDate: $('#time').datebox('getValue')
    });
}