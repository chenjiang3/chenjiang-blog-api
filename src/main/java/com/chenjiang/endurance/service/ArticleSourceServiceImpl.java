package com.chenjiang.endurance.service;

import com.chenjiang.endurance.entity.ArticleSource;
import com.chenjiang.endurance.mapper.ArticleSourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }
}
