package com.baiyun.devhist.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: servlet 基类
 * @author: HuaiAnGG
 * @create: 2020-12-23 23:22
 **/
public class BaseServlet extends HttpServlet {

    /**
     * 把 book 中的所有请求(即 action) 通过反射获取实现类中相对应的方法，并进行调用
     *
     * @param request  HttpServletRequest 请求
     * @param response HttpServletResponse 相应
     * @throws ServletException {@linkplain ServletException} servlet 异常
     * @throws IOException      {@linkplain IOException} IO 异常
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 设置编码格式
        request.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.setContentType("text/html;charset=utf-8");

        // 获取 action 名
        String action = request.getParameter("action");

        try {
            // 反射获取 action 相应的方法
            Method method = this.getClass().getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // 调用方法获得返回值 url
            method.invoke(this, request, response);
            // 通过 url 进行请求转发
            // request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
