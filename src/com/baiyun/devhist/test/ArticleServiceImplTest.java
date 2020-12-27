package com.baiyun.devhist.test;

import com.baiyun.devhist.dao.ArticleDao;
import com.baiyun.devhist.dao.impl.ArticleDaoImpl;
import com.baiyun.devhist.domain.Article;
import com.baiyun.devhist.domain.PageBean;
import com.baiyun.devhist.service.ArticleService;
import com.baiyun.devhist.service.impl.ArticleServiceImpl;
import com.baiyun.devhist.util.CurdTemplate;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ArticleServiceImplTest {

    private ArticleService articleService = new ArticleServiceImpl();
    private ArticleDao articleDao = new ArticleDaoImpl();

    @Test
    public void getAllArticle() {
        List<Article> allArticle = articleService.getAllArticle();
        System.out.println("allArticle = " + allArticle.size());
    }

    @Test
    public void getOneArticle() {
        Article oneArticle = articleService.getOneArticle("2019", 3);
        System.out.println("oneArticle = " + oneArticle);
    }

    @Test
    public void getByYear() {
        List<Article> byYear = articleService.getByYear("1989-2003");
        System.out.println("byYear = " + byYear.size());
    }

    @Test
    public void getByLikeKeyWord() {
        List<Article> byLikeKeyWord = articleService.getByLikeKeyWord("巫海辉");
        System.out.println("byLikeKeyWord = " + byLikeKeyWord.size());
    }

    @Test
    public void getTotalCount() {
        PageBean<Article> pageData = articleService.getPageData(1, 10, null, null);
        System.out.println("pageData = " + pageData);
    }

    @Test
    public void testInteger() {
        Integer num1 = Integer.parseInt("0");
        Integer num2 = Integer.valueOf("0");
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
    }

    @Test
    public void testTotalCount() {
        // String sqlStr = "select count(article_id) from article where article_content like ?";
        // CurdTemplate.executeQuery(sqlStr, "%郭勤亮%");
    }

    @Test
    public void getOneArticleById() {
        Article oneArticle = articleService.getOneArticle(2);
        System.out.println("oneArticle = " + oneArticle);
    }
}