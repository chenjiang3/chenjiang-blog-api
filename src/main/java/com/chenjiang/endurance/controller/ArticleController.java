package com.chenjiang.endurance.controller;

import com.chenjiang.endurance.entity.Article;
import com.chenjiang.endurance.interceptor.CheckToken;
import com.chenjiang.endurance.service.ArticleService;
import io.swagger.models.auth.In;
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
        return articleService.articleList(pageIndex, pageSize);
    }

    @GetMapping(value = "/{id}")
    public Article getById(@PathVariable(value = "id") Integer id) {
        return articleService.getById(id);
    }

    @PutMapping(value = "/access/{id}")
    public int increaseAccess(@PathVariable(value = "id") Integer id) {
        return articleService.increaseAccess(id);
    }

}
