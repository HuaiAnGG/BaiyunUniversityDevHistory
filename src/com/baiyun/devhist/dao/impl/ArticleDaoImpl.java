package com.baiyun.devhist.dao.impl;

import com.baiyun.devhist.dao.ArticleDao;
import com.baiyun.devhist.domain.Article;
import com.baiyun.devhist.handler.BeanHandler;
import com.baiyun.devhist.handler.BeanListHandler;
import com.baiyun.devhist.util.CurdTemplate;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 文章dao实现类
 * @author: HuaiAnGG
 * @create: 2020-12-23 12:44
 **/
public class ArticleDaoImpl implements ArticleDao {
    /**
     * 关键字查询文章数据
     *
     * @param keyWord 关键字
     * @return 文章列表
     * @throws SQLException {@link SQLException} 数据库异常
     */
    @Override
    public List<Article> getByLike(String keyWord) throws SQLException {
        String sqlStr = "select * from article where article_content like ?";
        return CurdTemplate.executeQuery(sqlStr, new BeanListHandler<>(Article.class), "%" + keyWord + "%");
    }

    /**
     * 查询所有文章数据
     *
     * @return 文章列表
     * @throws SQLException {@link SQLException} 数据库异常
     */
    @Override
    public List<Article> getAll() throws SQLException {
        String sqlStr = "select * from article";
        return CurdTemplate.executeQuery(sqlStr, new BeanListHandler<>(Article.class));
    }

    /**
     * 根据文章id 获取文章数据
     *
     * @param articleId 月份
     * @return 文章数据
     * @throws SQLException {@link SQLException} 数据库异常
     */
    @Override
    public Article get(Integer articleId) throws SQLException {
        String sqlStr = "select * from article where article_id = ?";
        return CurdTemplate.executeQuery(sqlStr, new BeanHandler<>(Article.class), articleId);
    }

    /**
     * 下拉列表获取文章数据
     *
     * @param cid       年份
     * @param articleId 月份
     * @return 文章数据
     * @throws SQLException {@link SQLException} 数据库异常
     */
    @Override
    public Article get(String cid, Integer articleId) throws SQLException {
        String sqlStr = "select * from article where article_cid like ? and article_id = ?";
        return CurdTemplate.executeQuery(sqlStr, new BeanHandler<>(Article.class), "%" + cid + "%", articleId);
    }

    /**
     * 按照年份获取文章数据
     *
     * @param cid 年份 id
     * @return 文章(月份)数据
     * @throws SQLException {@link SQLException} 数据库异常
     */
    @Override
    public List<Article> get(String cid) throws SQLException {
        String sqlStr = "select * from article where article_cid like ?";
        return CurdTemplate.executeQuery(sqlStr, new BeanListHandler<>(Article.class), "%" + cid + "%");
    }

    /**
     * 获取数据库共有多少条数据
     *
     * @param cid
     * @param keyWord
     * @return 一页的数据列表
     * @throws SQLException {@link SQLException} 数据库异常
     */
    @Override
    public Integer getTotalCount(Integer cid, String keyWord) throws SQLException {
        String sqlStr = "select count(*) from article where article_content like ? and article_cid = ?";
        return CurdTemplate.execute(sqlStr, "%" + keyWord + "%", "%" + cid + "%");
    }

    /**
     * 获取数据库共有多少条数据
     *
     * @param cid
     * @return 一页的数据列表
     * @throws SQLException {@link SQLException} 数据库异常
     */
    @Override
    public Integer getTotalCount(Integer cid) throws SQLException {
        String sqlStr = "select count(*) from article where article_cid like ?";
        return CurdTemplate.execute(sqlStr, "%" + cid + "%");
    }

    /**
     * 获取数据库共有多少条数据
     *
     * @param keyWord
     * @return 一页的数据列表
     * @throws SQLException {@link SQLException} 数据库异常
     */
    @Override
    public Integer getTotalCount(String keyWord) throws SQLException {
        String sqlStr = "select count(*) from article where article_content like ?";
        return CurdTemplate.execute(sqlStr, "%" + keyWord + "%");
    }

    /**
     * 获取数据库共有多少条数据
     *
     * @return 一页的数据列表
     * @throws SQLException {@link SQLException} 数据库异常
     */
    @Override
    public Integer getTotalCount() throws SQLException {
        String sqlStr = "select count(*) from article";
        return CurdTemplate.execute(sqlStr);
    }

    /**
     * 获取分页数据
     *
     * @param index    计算当前页从数据库当中查询的位置（角标）
     * @param pageSize 当前页的记录数
     * @return 当前页的所有文章数据
     */
    public List<Article> getPageData(Integer index, Integer pageSize) {
        String sqlStr = "select * from article limit ?, ?";
        return CurdTemplate.executeQuery(sqlStr, new BeanListHandler<>(Article.class), index, pageSize);
    }

    /**
     * 获取分页数据
     *
     * @param index    计算当前页从数据库当中查询的位置（角标）
     * @param pageSize 当前页的记录数
     * @param cid      文章分类(年份)id
     * @return 当前页的所有文章数据
     */
    @Override
    public List<Article> getPageData(Integer index, Integer pageSize, Integer cid) {
        String sqlStr = "select * from article where article_cid like ? limit ?, ?";
        return CurdTemplate.executeQuery(sqlStr, new BeanListHandler<>(Article.class), "%" + cid + "%", index,
                pageSize);
    }

    /**
     * 获取分页数据
     *
     * @param index    计算当前页从数据库当中查询的位置（角标）
     * @param pageSize 当前页的记录数
     * @param keyWord  模糊查询关键字
     * @return 当前页的所有文章数据
     */
    @Override
    public List<Article> getPageData(Integer index, Integer pageSize, String keyWord) {
        String sqlStr = "select * from article where article_content like ? limit ?, ?";
        return CurdTemplate.executeQuery(sqlStr, new BeanListHandler<>(Article.class), "%" + keyWord + "%",
                index, pageSize);
    }

    /**
     * 获取分页数据
     *
     * @param index    计算当前页从数据库当中查询的位置（角标）
     * @param pageSize 当前页的记录数
     * @param cid      文章分类(年份)id
     * @param keyWord  模糊查询关键字
     * @return 当前页的所有文章数据
     */
    @Override
    public List<Article> getPageData(Integer index, Integer pageSize, Integer cid, String keyWord) {
        String sqlStr = "select * from article where article_cid like ? and article_content like ? limit ?, ?";
        return CurdTemplate.executeQuery(sqlStr, new BeanListHandler<>(Article.class), "%" + cid + "%",
                "%" + keyWord + "%", index, pageSize);
    }
}
