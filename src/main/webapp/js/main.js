function openTab(text, url, iconCls){
    if($("#tabs").tabs("exists",text)){
        $("#tabs").tabs("select",text);
    }else{
        var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs("add",{
            title:text,
            iconCls:iconCls,
            closable:true,
            content:content
        });
    }
}

/** 退出 */
function logout() {
    $.messager.confirm('退出登陆', '确定退出系统吗？', function(r){
        if (r){
            /**  清除cookie */
           $.removeCookie("userIdStr");
           $.removeCookie("userName");
           $.removeCookie("realName");
           /** 跳转到登录*/
           window.location.href = ctx +"/index";
        }
    });
}

/**  修改密码 */
function openPasswordModifyDialog() {
    //打开对话框
    $("#dlg").dialog("open");
}

/** 保存 */
function modifyPassword() {
    $('#fm').form('submit', {
        url: ctx + '/user/updateUserPwd',
        onSubmit: function () {
            return $(this).form('validate');	// 返回false终止表单提交
        },
        success: function (data) {
            data = JSON.parse(data);
            //console.log(data);
            if(data.code == 200){
                $.messager.alert('来自crm', data.msg, 'info',function () {
                    //  清除cookie
                    $.removeCookie("userIdStr");
                    window.location.href = ctx + '/index';
                });
            }else{
                $.messager.alert('来自crm', data.msg, 'error');
            }
        }
    })
}
/** 关闭对话框 */
function closePasswordModifyDialog() {
    $("#fm").form("reset");
    $("#dlg").dialog("close");
}