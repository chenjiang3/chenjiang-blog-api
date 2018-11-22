package com.chenjiang.endurance.service;

import com.chenjiang.endurance.common.BasicErrorCode;
import com.chenjiang.endurance.entity.Article;
import com.chenjiang.endurance.exception.ArticleException;
import com.chenjiang.endurance.mapper.ArticleMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Component
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public boolean add(Article article) {
        if (article.getAbstractContent().length() >= 512) {
            throw new ArticleException(BasicErrorCode.ABSTRACT_TOO_LONG);
        }
        int result = articleMapper.add(article);
        return result == 1;
    }

    @Override
    public List<Article> articleList(int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        return articleMapper.articleList(new HashMap<>());
    }

}
