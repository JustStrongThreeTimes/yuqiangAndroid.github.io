package com.qiang.litepal.bean;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/6.
 */
public class Comment extends DataSupport {

    private int id;

    private String content;

    private Date publishDate;

    private int news_id;


    public int getId() {
        return id;
    }

    private News news;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }
}
