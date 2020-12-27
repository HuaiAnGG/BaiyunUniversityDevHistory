package com.baiyun.devhist.test;

import com.baiyun.devhist.dao.ArticleDao;
import com.baiyun.devhist.dao.impl.ArticleDaoImpl;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class ArticleDaoImplTest {

    private ArticleDao articleDao = new ArticleDaoImpl();

    @Test
    public void getTotalCount() throws SQLException {
        Integer count = articleDao.getTotalCount(2019, "谢可滔");
        System.out.println("count = " + count);
    }
}