package com.chenjiang.endurance.service;

import com.chenjiang.endurance.common.BasicErrorCode;
import com.chenjiang.endurance.entity.ArticleSource;
import com.chenjiang.endurance.exception.ArticleSourceException;
import com.chenjiang.endurance.mapper.ArticleSourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Service
public class ArticleSourceServiceImpl implements ArticleSourceService {

    @Autowired
    private ArticleSourceMapper mapper;

    @Override
    public List<ArticleSource> list() {
        return mapper.sourceList();
    }

    @Override
    public int add(ArticleSource articleSource) {
        return mapper.add(articleSource);
    }

    @Override
    @Transactional
    public int deleteById(Integer id) {
        ArticleSource source = mapper.getById(id);
        if (source == null) {
            throw new ArticleSourceException(BasicErrorCode.ARTICLE_SOURCE_NOT_FOUND);
        }
        source.setDeleted(true);
        return mapper.deleteById(source);
    }
}
