package com.chenjiang.endurance.service;

import com.chenjiang.endurance.common.BasicErrorCode;
import com.chenjiang.endurance.entity.Article;
import com.chenjiang.endurance.entity.PageResult;
import com.chenjiang.endurance.entity.User;
import com.chenjiang.endurance.entity.UserArticle;
import com.chenjiang.endurance.exception.ArticleException;
import com.chenjiang.endurance.mapper.ArticleMapper;
import com.chenjiang.endurance.mapper.UserArticleMapper;
import com.sun.rowset.internal.Row;
import io.swagger.models.auth.In;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Component
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserArticleMapper userArticleMapper;

    @Override
    @Transactional
    public boolean add(Article article, User user) {
        if (article.getAbstractContent().length() >= 512) {
            throw new ArticleException(BasicErrorCode.ABSTRACT_TOO_LONG);
        }
        article.setAccess(0);
        int ret = articleMapper.add(article);

        UserArticle userArticle = new UserArticle();
        userArticle.setArticleId(article.getId());
        userArticle.setUserId(user.getUserId());
        int result = userArticleMapper.add(userArticle);

        return result == 1;
    }

    @Override
    public PageResult<Article> articleList(RowBounds rowBounds) {
        int total = articleMapper.total();
        List<Article> list = articleMapper.articleList(rowBounds);
        PageResult<Article> result = new PageResult<>(list, rowBounds.getOffset(), rowBounds.getLimit(), total);
        return result;
    }

    @Override
    public Article getById(Integer id) {
        return articleMapper.getById(id);
    }

    @Override
    public int increaseAccess(Integer id) {
        return articleMapper.increaseAccess(id);
    }
}
