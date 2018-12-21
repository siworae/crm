package com.siworae.crm.exceptions;

import com.alibaba.fastjson.JSON;
import com.siworae.crm.model.ResultInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @program: crm
 * @ClassName: GlobalEXceptionHandler
 * @Date: 2018/12/18 19:21
 * @Author: siworae
 */
@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {

        ModelAndView mv = createDefModelAndView(request);


        /**
         * 区分页面请求还是json请求
         */
        if (handler instanceof HandlerMethod){
            //将对象强制转换为HandlerMethod类型
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //得到方法对象
            Method method = handlerMethod.getMethod();
            //得到方法的注解
            ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
            //判断是否存在ResponseBody注解
            if (null == responseBody){
                //为空，说明为页面请求
                //判断异常类型
                if (ex instanceof ParamsException){
                    ParamsException paramsException = (ParamsException) ex;
                    //重新赋值异常提示信息
                    mv.addObject("errorMsg",paramsException.getMsg());
                }
                /**
                 * 拦截未登录
                 */
                if (ex instanceof LoginException){
                    LoginException loginException = (LoginException) ex;
                    //重新赋值异常提示信息
                    mv.addObject("errorMsg",loginException.getMsg());
                }

                return mv;
            }else{
                //不为空，说明为json请求
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(300);
                resultInfo.setMsg("系统繁忙");
                if (ex instanceof ParamsException){
                    ParamsException paramsException = (ParamsException) ex;
                    resultInfo.setCode(paramsException.getCode());
                    resultInfo.setMsg(paramsException.getMsg());
                }
                /**
                 * 拦截未登录
                 */
                if (ex instanceof LoginException){
                    LoginException loginException = (LoginException) ex;
                    resultInfo.setCode(loginException.getCode());
                    resultInfo.setMsg(loginException.getMsg());
                }
                //将对象转换为json格式发送至前台
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json;charset=utf-8");
                PrintWriter pw = null;
                try {
                    pw = response.getWriter();
                    pw.write(JSON.toJSONString(resultInfo));
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (null != pw){
                        pw.close();
                    }
                }
                return null;
            }
        }
        return null;
    }

    private ModelAndView createDefModelAndView(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("errorMsg","系统繁忙哟。。。");
        mv.addObject("ctx",request.getContextPath());
        return mv;
    }
}
