package com.chenjiang.endurance.service;

import com.chenjiang.endurance.entity.Article;

import java.util.List;

public interface ArticleService {
    List<Article> articleList(int pageIndex, int pageSize);
    boolean add(Article article);
    Article getById(Integer id);
    int increaseAccess(Integer id);
}
