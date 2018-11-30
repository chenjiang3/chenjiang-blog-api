package com.chenjiang.endurance.service;

import com.chenjiang.endurance.entity.Article;
import com.chenjiang.endurance.entity.PageResult;
import com.chenjiang.endurance.entity.User;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ArticleService {
    PageResult<Article> articleList(RowBounds rowBounds);
    boolean add(Article article, User user);
    Article getById(Integer id);
    int increaseAccess(Integer id);
}
