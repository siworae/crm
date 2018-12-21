package com.siworae.crm.base;

import com.siworae.crm.model.ResultInfo;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: crm
 * @ClassName: BaseController
 * @Date: 2018/12/18 17:59
 * @Author: siworae
 */
public class BaseController {

    @ModelAttribute
    public void preMetod(HttpServletRequest request){
        // 获取当前项目路径
        request.setAttribute("ctx", request.getContextPath());
    }

    public ResultInfo success(Integer code, String msg, Object result){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }

    public ResultInfo success(String msg, Object result){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }
    public ResultInfo success(String msg){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        return resultInfo;
    }
    public ResultInfo success(Integer code){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        return resultInfo;
    }
    public ResultInfo success(Integer code, String msg){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMsg(msg);
        return resultInfo;
    }
}
