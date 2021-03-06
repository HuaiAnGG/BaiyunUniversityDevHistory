package com.baiyun.devhist.domain;

import java.util.List;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 分页数据实体
 * @author: HuaiAnGG
 * @create: 2020-12-23 19:43
 **/
public class PageBean<T> {
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 一页多少条数据
     */
    private Integer pageSize;
    /**
     * 当前查询的角标
     */
    private Integer index;
    /**
     * 总记录数
     */
    private Integer totalCount;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 当前页的数据
     */
    private List<T> list;

    /**
     * 如果当前页没有设置,默认设置为第1页
     *
     * @param currentPage 当前页
     */
    public void setCurrentPage(Integer currentPage) {
        if (currentPage == null) {
            currentPage = 1;
        }
        this.currentPage = currentPage;
    }

    /**
     * 如果没有设置当前页总记录数据,设置默认记录数为一页5条
     *
     * @param pageSize 一页有多少条数据
     */
    public void setPageSize(Integer pageSize) {
        if (pageSize == null) {
            pageSize = 5;
        }
        this.pageSize = pageSize;
    }

    /**
     * 计算当前页从数据库当中查询的位置
     *
     * @return 当前页索引
     */
    public Integer getIndex() {
        return (currentPage - 1) * pageSize;
    }

    /**
     * 计算总页数
     *
     * @return 总页数
     */
    public Integer getTotalPage() {
        double ceil = Math.ceil(totalCount * 1.0 / pageSize);
        return (int) ceil;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", index=" + index +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}
