package com.chenjiang.endurance.controller;

import com.chenjiang.endurance.entity.Article;
import com.chenjiang.endurance.interceptor.CheckToken;
import com.chenjiang.endurance.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @CheckToken
    public int add(@RequestBody Article article) {
        return articleService.add(article);
    }

}
