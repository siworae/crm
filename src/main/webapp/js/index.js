function login() {
    //得到参数
    var userName = $("#username").val();
    var userPwd = $("#password").val();

    //判断参数
    if (isEmpty(userName)){
        alert("用户名为空");
        return;
    }
    if (isEmpty(userPwd)){
        alert("用户密码为空");
        return;
    }

    $.ajax({
        url:ctx + "/user/login",
        type:"post",
        data:{
            userName:userName,
            userPwd:userPwd
        },
        success:function (data) {
            console.log(data);
           if (data.code == 200){
                /*
                     存cookie
                 */
                $.cookie("userIdStr",data.result.userIdStr);
                $.cookie("userName",data.result.userName);
                $.cookie("realName",data.result.realName);
               window.location.href = ctx + '/main'
           } else{
                alert(data.msg);
           }

        }

    });
}