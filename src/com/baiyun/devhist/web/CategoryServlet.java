package com.baiyun.devhist.web;

import com.baiyun.devhist.domain.Category;
import com.baiyun.devhist.service.CategoryService;
import com.baiyun.devhist.service.impl.CategoryServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 年份(分类) servlet
 * @author: HuaiAnGG
 * @create: 2020-12-24 00:55
 **/
@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet{

    private CategoryService categoryService = new CategoryServiceImpl();

    /**
     * 获取所有年份信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void getAllCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Category> allCategory = categoryService.getAllCategory();
        JSONArray jsonArray = JSONArray.fromObject(allCategory, new JsonConfig());
        response.getWriter().println(jsonArray.toString());
    }
}
