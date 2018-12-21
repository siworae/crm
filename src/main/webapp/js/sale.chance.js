//分页查询
function querySaleChancesByParams() {
    $('#dg').datagrid('load', {
        customerName: $('#customerName').val(),
        state: $('#state').combobox('getValue'),
        devResult: $('#devResult').combobox('getValue'),
        createDate: $('#time').datebox('getValue')
    });
}

/**************增加******************/
//打开对话框
function openAddSaleChacneDialog() {
    /*$("#dlg").dialog("open");*/
    openAddOrUpdateDlg("dlg");
}

/**************更新******************/
//打开对话框
function openModifySaleChanceDialog() {

    /*    var rows = $('#dg').datagrid("getSelections");
        //console.log(rows);
        if (rows.length == 0) {
            $.messager.alert('来自Crm', "请选择一条数据进行更新");
            return;
        }
        if (rows.length > 1) {
            $.messager.alert('来自Crm', "只能选择一条数据进行更新");
            return;
        }
        //回显表单数据
        $('#fm').form('load', rows[0]);
        $("#dlg").dialog("open").dialog("setTitle", "更新营销机会");*/

    openModifyDialog('dg', 'fm','dlg','更新营销机会');

}

//保存
function saveOrUpdateSaleChance() {
/*    $('#fm').form('submit', {
        url: ctx + '/saleChance/saveOrUpdateSaleChance',
        onSubmit: function () {
            return $(this).form('validate');	// 返回false终止表单提交
        },
        success: function (data) {
            data = JSON.parse(data);
            //console.log(data);
            if (data.code == 200) {
                $.messager.alert('来自crm', data.msg, 'info', function () {
                    // 关闭弹窗
                    $('#dlg').dialog('close');
                    // 刷新页面
                    $('#dg').datagrid('reload');
                });
            } else {
                $.messager.alert('来自crm', data.msg, 'error');
            }
        }
    })*/

    saveOrUpdateData('fm',ctx + '/saleChance/saveOrUpdateSaleChance', 'dlg', function () {
        $('#dg').datagrid('reload');
    });
}

//关闭
function closeDlg() {
    /*$("#dlg").dialog("close");*/
    closeDlgData("dlg");
}

//格式化显示数据
function formatState(value, row, index) {
    // value 当前值
    // row 当前行的值
    // index 当前行的索引
    if (value == 0) {
        return "未分配";
    }
    if (value == 1) {
        return "已分配";
    }
}

function formatDevResult(value, row, index) {
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



/**************删除******************/

function deleteSaleChance() {

/*    var rows = $('#dg').datagrid("getSelections");
    //console.log(rows);
    if (rows.length == 0) {
        $.messager.alert('来自Crm', "请选择一条数据进行删除");
        return;
    } else {
        $.messager.confirm('来自Crm', "确定删除" + rows.length + "条记录？", function (r) {
            //删除记录
            if (r) {
                var ids = [];
                for (var i = 0; i < rows.length; i++) {
                    ids.push(rows[i].id);
                }
                console.log(ids);
                $.ajax({
                    url: ctx + '/saleChance/deleteSaleChanceBatch?',
                    type: 'post',
                    data: {
                        ids: ids,
                    },
                    success: function (data) {
                        if (data.code == 200) {
                            $.messager.alert('来自Crm', data.msg, 'info', function () {
                                // 刷新页面
                                $('#dg').datagrid('reload');
                            });
                        } else {
                            $.messager.alert('来自crm', data.msg, 'error');
                        }
                    }
                })
            }
        });
    }*/

    deleteData('dg',ctx + '/saleChance/deleteSaleChanceBatch?', function () {
        $('#dg').datagrid('reload');
    });

}

/****************监听对话框是否关闭********************/
$(function () {
    $('#dlg').dialog({
        "onClose": function () {
            // 触发表单清空
            $('#fm').form('clear');
        }
    })
})