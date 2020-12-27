package com.baiyun.devhist.dao.impl;

import com.baiyun.devhist.dao.CategoryDao;
import com.baiyun.devhist.domain.Category;
import com.baiyun.devhist.handler.BeanHandler;
import com.baiyun.devhist.handler.BeanListHandler;
import com.baiyun.devhist.util.CurdTemplate;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 年份(分类)信息dao实现类
 * @author: HuaiAnGG
 * @create: 2020-12-23 12:47
 **/
public class CategoryDaoImpl implements CategoryDao {

    /**
     * 获取所有分类信息
     *
     * @return 所有分类信息
     * @throws SQLException {@link SQLException} 数据库异常
     */
    @Override
    public List<Category> getAll() throws SQLException {
        String sqlStr = "select * from category";
        return CurdTemplate.executeQuery(sqlStr, new BeanListHandler<>(Category.class));
    }

    /**
     * 获取分类(年份)信息
     *
     * @param id 年份 id
     * @return 年份信息
     */
    @Override
    public Category get(Integer id) throws SQLException {
        String sqlStr = "select * from category where id = ?";
        return CurdTemplate.executeQuery(sqlStr, new BeanHandler<>(Category.class), id);
    }
}
