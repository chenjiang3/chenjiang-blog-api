package com.chenjiang.endurance.controller;

import com.chenjiang.endurance.entity.ArticleSource;
import com.chenjiang.endurance.interceptor.CheckToken;
import com.chenjiang.endurance.service.ArticleSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article-source")
public class ArticleSourceController {

    @Autowired
    private ArticleSourceService service;

    @GetMapping(value = "/list")
    @CheckToken
    public List<ArticleSource> list() {
        return service.list();
    }

    @PutMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CheckToken
    public int add(@RequestBody ArticleSource source) {
        return service.add(source);
    }

    @DeleteMapping(value = "/{id}")
    @CheckToken
    public boolean deleteById(@PathVariable(value = "id") Integer id) {
        return service.deleteById(id) == 1;
    }

}
