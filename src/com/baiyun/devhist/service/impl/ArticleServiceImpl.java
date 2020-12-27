package com.baiyun.devhist.service.impl;

import com.baiyun.devhist.dao.ArticleDao;
import com.baiyun.devhist.dao.CategoryDao;
import com.baiyun.devhist.dao.impl.ArticleDaoImpl;
import com.baiyun.devhist.dao.impl.CategoryDaoImpl;
import com.baiyun.devhist.domain.Article;
import com.baiyun.devhist.domain.Category;
import com.baiyun.devhist.domain.PageBean;
import com.baiyun.devhist.service.ArticleService;
import org.apache.commons.lang.StringUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 文章服务实现类
 * @author: HuaiAnGG
 * @create: 2020-12-23 13:10
 **/
public class ArticleServiceImpl implements ArticleService {

    private ArticleDao articleDao = new ArticleDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();

    /**
     * 获取所有文章数据
     *
     * @return 文章列表
     */
    @Override
    public List<Article> getAllArticle() {
        List<Article> articles = null;
        try {
            articles = articleDao.getAll();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return articles;
    }

    /**
     * 根据年份-月份获取文章数据
     *
     * @param cid       年份
     * @param articleId 月份
     * @return 文章
     */
    @Override
    public Article getOneArticle(String cid, Integer articleId) {
        Article article = null;
        try {
            article = articleDao.get(cid, articleId);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return article;
    }

    /**
     * 获取文章数据
     *
     * @param articleId 文章id
     * @return 文章
     */
    @Override
    public Article getOneArticle(Integer articleId) {
        try {
            return articleDao.get(articleId);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    /**
     * 按照年份获取所有文章(月份)数据
     *
     * @param cid 年份
     * @return 文章列表
     */
    @Override
    public List<Article> getByYear(String cid) {
        List<Article> articles = null;
        try {
            articles = articleDao.get(cid);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return articles;
    }

    /**
     * 模糊查询所有文章数据
     *
     * @param keyWord 关键字
     * @return 文章（月份）列表
     */
    @Override
    public List<Article> getByLikeKeyWord(String keyWord) {
        List<Article> articles = null;
        try {
            articles = articleDao.getByLike(keyWord);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return articles;
    }

    /**
     * 获取分页数据
     *
     * @param currPage 当前页
     * @param pageSize 一页有多少条数据
     * @param cid      分类(年份) id
     * @param keyWord  模糊查询关键字
     * @return 分页数据实体
     */
    @Override
    public PageBean<Article> getPageData(Integer currPage, int pageSize, Integer cid, String keyWord) {
        PageBean<Article> pageBean = new PageBean<>();
        // 设置当前页
        pageBean.setCurrentPage(currPage);
        // 设置一页有多少条数据
        pageBean.setPageSize(pageSize);
        Integer totalCount = null;
        try {
            List<Article> pageArticle = null;
            // 查询数据库，设置当前页数据
            if (cid == null && StringUtils.isBlank(keyWord)) {
                totalCount = articleDao.getTotalCount();
                pageArticle = articleDao.getPageData(pageBean.getIndex(), pageBean.getPageSize());
            } else if (cid != null && !StringUtils.isBlank(keyWord)) {
                totalCount = articleDao.getTotalCount(cid, keyWord);
                pageArticle = articleDao.getPageData(pageBean.getIndex(), pageBean.getPageSize(), cid, keyWord);
            } else if (!StringUtils.isBlank(keyWord)) {
                totalCount = articleDao.getTotalCount(keyWord);
                pageArticle = articleDao.getPageData(pageBean.getIndex(), pageBean.getPageSize(), keyWord);
            } else {
                totalCount = articleDao.getTotalCount(cid);
                pageArticle = articleDao.getPageData(pageBean.getIndex(), pageBean.getPageSize(), cid);
            }
            pageBean.setList(pageArticle);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        // 从数据库获取总记录数
        pageBean.setTotalCount(totalCount);
        // 设置总页数
        pageBean.setTotalPage(pageBean.getTotalPage());

        return pageBean;
    }

}
