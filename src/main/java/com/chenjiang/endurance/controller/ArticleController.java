package com.chenjiang.endurance.controller;

import com.chenjiang.endurance.entity.Article;
import com.chenjiang.endurance.interceptor.CheckToken;
import com.chenjiang.endurance.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CheckToken
    public boolean add(@RequestBody Article article) {
        return articleService.add(article);
    }

    @GetMapping(value = "/list")
    public List<Article> articleList(@RequestParam(value = "pageIndex", required = true) int pageIndex,
                                     @RequestParam(value = "pageSize", required = true) int pageSize) {
        List<Article> result = articleService.articleList(pageIndex, pageSize);
        return result;
//        return articleService.articleList(pageIndex, pageSize);
    }

}
