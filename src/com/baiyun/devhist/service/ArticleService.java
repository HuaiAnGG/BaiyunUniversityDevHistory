package com.baiyun.devhist.service;

import com.baiyun.devhist.domain.Article;
import com.baiyun.devhist.domain.PageBean;
import com.baiyun.devhist.util.CurdTemplate;

import java.util.List;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 文章(年份)service
 * @author: HuaiAnGG
 * @create: 2020-12-23 12:50
 **/
public interface ArticleService {

    /**
     * 获取所有文章数据
     *
     * @return 文章列表
     */
    List<Article> getAllArticle();

    /**
     * 根据年份-月份获取文章数据
     *
     * @param cid       年份
     * @param articleId 月份
     * @return 文章
     */
    Article getOneArticle(String cid, Integer articleId);

    /**
     * 获取文章数据
     *
     * @param articleId 文章id
     * @return 文章
     */
    Article getOneArticle(Integer articleId);

    /**
     * 按照年份获取所有文章(月份)数据
     *
     * @param cid 年份
     * @return 文章列表
     */
    List<Article> getByYear(String cid);

    /**
     * 模糊查询所有文章数据
     *
     * @param keyWord 关键字
     * @return 文章（月份）列表
     */
    List<Article> getByLikeKeyWord(String keyWord);

    /**
     * 获取分页数据
     *
     * @param currPage 当前页
     * @param pageSize 一页有多少条数据
     * @return 分页数据实体
     */
    PageBean<Article> getPageData(Integer currPage, int pageSize, Integer cid, String keyWord);
}
