package com.baiyun.devhist.domain;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 月份文章分类
 * @author: HuaiAnGG
 * @create: 2020-12-23 11:35
 **/
public class Article {
    private Integer article_id;
    private String article_title;
    private String article_desc;
    private String article_content;
    private Long article_time;
    private String article_cid;

    @Override
    public String toString() {
        return "Article{" +
                "article_id=" + article_id +
                ", article_title='" + article_title + '\'' +
                ", article_desc='" + article_desc + '\'' +
                // ", article_content='" + article_content + '\'' +
                ", article_time=" + article_time +
                ", article_cid='" + article_cid + '\'' +
                '}';
    }

    public String getArticle_desc() {
        return article_desc;
    }

    public void setArticle_desc(String article_desc) {
        this.article_desc = article_desc;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public Long getArticle_time() {
        return article_time;
    }

    public void setArticle_time(Long article_time) {
        this.article_time = article_time;
    }

    public String getArticle_cid() {
        return article_cid;
    }

    public void setArticle_cid(String article_cid) {
        this.article_cid = article_cid;
    }
}
