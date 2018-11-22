package com.chenjiang.endurance.service;

import com.chenjiang.endurance.entity.Article;

import java.util.List;

public interface ArticleService {
    List<Article> articleList(int pageIndex, int pageSize);
    public boolean add(Article article);
}
