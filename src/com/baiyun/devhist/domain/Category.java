package com.baiyun.devhist.domain;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 年份分类
 * @author: HuaiAnGG
 * @create: 2020-12-23 11:38
 **/
public class Category {
    // private Integer id;
    private String cid;
    private String cname;
    private Integer parentid;
    private String url;

    @Override
    public String toString() {
        return "Category{" +
                ", cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", parentid=" + parentid +
                ", url='" + url + '\'' +
                '}';
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
