package com.baiyun.devhist.service;

import com.baiyun.devhist.domain.Category;

import java.util.List;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 分类(年份)service接口
 * @author: HuaiAnGG
 * @create: 2020-12-23 13:11
 **/
public interface CategoryService {
    /**
     * 获取所有年份(分类)数据
     *
     * @return 分类(年份)数据
     */
    List<Category> getAllCategory();

    /**
     * 查询年份(分类)信息
     *
     * @param id 年份id
     * @return 年份信息
     */
    Category getCategory(Integer id);
}
