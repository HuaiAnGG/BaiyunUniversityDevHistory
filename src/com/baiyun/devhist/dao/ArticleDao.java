package com.baiyun.devhist.dao;

import com.baiyun.devhist.domain.Article;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 月份文章dao接口
 * @author: HuaiAnGG
 * @create: 2020-12-23 12:29
 **/
public interface ArticleDao {
    /**
     * 关键字查询文章数据
     *
     * @param keyWord 关键字
     * @return 文章列表
     * @throws SQLException {@link SQLException} 数据库异常
     */
    List<Article> getByLike(String keyWord) throws SQLException;

    /**
     * 查询所有文章数据
     *
     * @return 文章列表
     * @throws SQLException {@link SQLException} 数据库异常
     */
    List<Article> getAll() throws SQLException;

    /**
     * 根据文章id 获取文章数据
     *
     * @param articleId 月份
     * @return 文章数据
     * @throws SQLException {@link SQLException} 数据库异常
     */
    Article get(Integer articleId) throws SQLException;

    /**
     * 下拉列表获取文章数据
     *
     * @param cid       年份
     * @param articleId 月份
     * @return 文章数据
     * @throws SQLException {@link SQLException} 数据库异常
     */
    Article get(String cid, Integer articleId) throws SQLException;

    /**
     * 按照年份获取文章数据
     *
     * @param cid 年份 id
     * @return 文章(月份)数据
     * @throws SQLException {@link SQLException} 数据库异常
     */
    List<Article> get(String cid) throws SQLException;

    /**
     * 获取数据库共有多少条数据
     *
     * @return 一页的数据列表
     * @throws SQLException {@link SQLException} 数据库异常
     */
    Integer getTotalCount(Integer cid, String keyWord) throws SQLException;

    /**
     * 获取数据库共有多少条数据
     *
     * @return 一页的数据列表
     * @throws SQLException {@link SQLException} 数据库异常
     */
    Integer getTotalCount(Integer cid) throws SQLException;

    /**
     * 获取数据库共有多少条数据
     *
     * @return 一页的数据列表
     * @throws SQLException {@link SQLException} 数据库异常
     */
    Integer getTotalCount(String keyWord) throws SQLException;

    /**
     * 获取数据库共有多少条数据
     *
     * @return 一页的数据列表
     * @throws SQLException {@link SQLException} 数据库异常
     */
    Integer getTotalCount() throws SQLException;

    /**
     * 获取分页数据
     *
     * @param index    计算当前页从数据库当中查询的位置（角标）
     * @param pageSize 当前页的记录数
     * @return 当前页的所有文章数据
     */
    List<Article> getPageData(Integer index, Integer pageSize);


    /**
     * 获取分页数据
     *
     * @param index    计算当前页从数据库当中查询的位置（角标）
     * @param pageSize 当前页的记录数
     * @param cid      文章分类(年份)id
     * @return 当前页的所有文章数据
     */
    List<Article> getPageData(Integer index, Integer pageSize, Integer cid);

    /**
     * 获取分页数据
     *
     * @param index    计算当前页从数据库当中查询的位置（角标）
     * @param pageSize 当前页的记录数
     * @param keyWord  模糊查询关键字
     * @return 当前页的所有文章数据
     */
    List<Article> getPageData(Integer index, Integer pageSize, String keyWord);

    /**
     * 获取分页数据
     *
     * @param index    计算当前页从数据库当中查询的位置（角标）
     * @param pageSize 当前页的记录数
     * @param cid      文章分类(年份)id
     * @param keyWord  模糊查询关键字
     * @return 当前页的所有文章数据
     */
    List<Article> getPageData(Integer index, Integer pageSize, Integer cid, String keyWord);

}
