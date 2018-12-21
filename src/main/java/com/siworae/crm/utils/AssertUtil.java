package com.siworae.crm.utils;


import com.siworae.crm.exceptions.LoginException;
import com.siworae.crm.exceptions.ParamsException;

public class AssertUtil {

    public static void isTrue(Boolean flag,String msg){
        if(flag){
            throw new ParamsException(msg);
        }
    }

    public static  void isTrue(Boolean flag,String msg,Integer code){
        if(flag){
            throw new ParamsException(code,msg);
        }
    }
    public static  void isLogin(Boolean flag,String msg){
        if(flag){
            throw new LoginException(msg);
        }
    }

}
