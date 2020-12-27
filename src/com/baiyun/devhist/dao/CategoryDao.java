package com.baiyun.devhist.dao;

import com.baiyun.devhist.domain.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 分类(年份)dao
 * @author: HuaiAnGG
 * @create: 2020-12-23 12:46
 **/
public interface CategoryDao {
    /**
     * 获取所有分类信息
     *
     * @return 所有分类信息
     * @throws SQLException {@link SQLException} 数据库异常
     */
    List<Category> getAll() throws SQLException;

    /**
     * 获取分类(年份)信息
     *
     * @param id 年份 id
     * @return 年份信息
     */
    Category get(Integer id) throws SQLException;
}
