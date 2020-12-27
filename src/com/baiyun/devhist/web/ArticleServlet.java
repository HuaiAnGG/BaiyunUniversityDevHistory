package com.baiyun.devhist.web;

import com.baiyun.devhist.domain.Article;
import com.baiyun.devhist.domain.PageBean;
import com.baiyun.devhist.service.ArticleService;
import com.baiyun.devhist.service.impl.ArticleServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 文章数据servlet
 * @author: HuaiAnGG
 * @create: 2020-12-24 00:07
 **/
@WebServlet(name = "ArticleServlet", urlPatterns = "/ArticleServlet")
public class ArticleServlet extends BaseServlet {

    private ArticleService articleService = new ArticleServiceImpl();

    /**
     * 遍历所有文章数据
     *
     * @param request  {@linkplain HttpServletRequest} req 请求
     * @param response {@linkplain HttpServletResponse} resp 请求
     * @throws IOException {@linkplain IOException} io 异常
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Article> allArticle = articleService.getAllArticle();
        // 转 json
        JSONArray jsonArray = JSONArray.fromObject(allArticle, new JsonConfig());
        // 响应给浏览器
        response.getWriter().println(jsonArray.toString());
    }

    /**
     * 获取分页数据
     *
     * @param request  {@linkplain HttpServletRequest} req 请求
     * @param response {@linkplain HttpServletResponse} resp 请求
     * @throws IOException {@linkplain IOException} io 异常
     */
    public void getPageBean(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Integer currPage, int pageSize
        Integer currPage = Integer.valueOf(request.getParameter("currPage"));
        String cid = request.getParameter("cid");
        String keyWord = request.getParameter("keyWord");
        System.out.println("cid = " + cid);
        System.out.println("keyWord = " + keyWord);
        PageBean<Article> pageData = null;
        if (!StringUtils.isBlank(cid) && !StringUtils.isBlank(keyWord)) {
            pageData = articleService.getPageData(currPage, 8, Integer.valueOf(cid.substring(0, 4)), keyWord);
        } else if (StringUtils.isBlank(cid) && StringUtils.isBlank(keyWord)) {
            pageData = articleService.getPageData(currPage, 8, null, null);
        } else if (!StringUtils.isBlank(cid)) {
            pageData = articleService.getPageData(currPage, 8, Integer.valueOf(cid.substring(0, 4)), null);
        } else {
            pageData = articleService.getPageData(currPage, 8, null, keyWord);
        }
        // 转 json
        JSONArray jsonArray = JSONArray.fromObject(pageData, new JsonConfig());
        // 响应给浏览器
        response.getWriter().println(jsonArray.toString());
    }

    /**
     * 通过文章 id 获取文章
     *
     * @param request  {@linkplain HttpServletRequest} req 请求
     * @param response {@linkplain HttpServletResponse} resp 请求
     * @throws IOException {@linkplain IOException} io 异常
     */
    public void getArticleById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String aid = request.getParameter("aid");
        Article article = articleService.getOneArticle(Integer.valueOf(aid));
        JSONArray jsonArray = JSONArray.fromObject(article, new JsonConfig());
        response.getWriter().println(jsonArray.toString());
    }
}
