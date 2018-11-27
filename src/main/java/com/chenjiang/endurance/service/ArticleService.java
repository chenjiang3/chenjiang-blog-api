package com.chenjiang.endurance.service;

import com.chenjiang.endurance.entity.Article;
import com.chenjiang.endurance.entity.User;

import java.util.List;

public interface ArticleService {
    List<Article> articleList(int pageIndex, int pageSize);
    boolean add(Article article, User user);
    Article getById(Integer id);
    int increaseAccess(Integer id);
}
