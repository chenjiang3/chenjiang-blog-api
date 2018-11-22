package com.chenjiang.endurance.entity;

public class Article {
    private Integer id;
    private String title;
    private String tags;
    private String type;
    private String abstractContent;
    private String content;
    private String rawFileLink;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRawFileLink() {
        return rawFileLink;
    }

    public void setRawFileLink(String rawFileLink) {
        this.rawFileLink = rawFileLink;
    }
}