package com.chenjiang.endurance.service;

import com.chenjiang.endurance.entity.ArticleSource;

import java.util.List;

public interface ArticleSourceService {
    int add(ArticleSource articleSource);
    List<ArticleSource> list();
    int deleteById(Integer id);
}
