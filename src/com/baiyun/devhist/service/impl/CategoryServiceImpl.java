package com.baiyun.devhist.service.impl;

import com.baiyun.devhist.dao.CategoryDao;
import com.baiyun.devhist.dao.impl.CategoryDaoImpl;
import com.baiyun.devhist.domain.Category;
import com.baiyun.devhist.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 分类(年份)数据实现类
 * @author: HuaiAnGG
 * @create: 2020-12-23 13:12
 **/
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    /**
     * 获取所有年份(分类)数据
     *
     * @return 分类(年份)数据
     */
    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = null;
        try {
            categories = categoryDao.getAll();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return categories;
    }

    /**
     * 查询年份(分类)信息
     *
     * @param id 年份id
     * @return 年份信息
     */
    @Override
    public Category getCategory(Integer id) {
        Category category = null;
        try {
            category = categoryDao.get(id);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return category;
    }
}
