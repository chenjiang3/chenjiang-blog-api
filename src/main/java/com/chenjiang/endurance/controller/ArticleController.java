package com.chenjiang.endurance.controller;

import com.chenjiang.endurance.common.AuthorizationUtil;
import com.chenjiang.endurance.common.BasicErrorCode;
import com.chenjiang.endurance.entity.Article;
import com.chenjiang.endurance.entity.User;
import com.chenjiang.endurance.exception.ArticleException;
import com.chenjiang.endurance.interceptor.CheckToken;
import com.chenjiang.endurance.service.ArticleService;
import com.chenjiang.endurance.service.UserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CheckToken
    public boolean add(@RequestBody Article article,
                       @RequestHeader("Authorization") String authorization) {
        Map<String, String> auth = AuthorizationUtil.parseAuthInfo(authorization);
        String uid = auth.get("uid");
        if (StringUtils.isEmpty(uid)) {
            throw new ArticleException(BasicErrorCode.UNAUTHORIZED);
        }
        User user = userService.findById(uid);
        if (user == null) {
            throw new ArticleException(BasicErrorCode.USER_NOT_FIND);
        }
        article.setAuthor(user.getUserName());
        return articleService.add(article, user);
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
