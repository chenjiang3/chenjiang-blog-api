package com.chenjiang.endurance.controller;

import com.chenjiang.endurance.entity.Tag;
import com.chenjiang.endurance.interceptor.CheckToken;
import com.chenjiang.endurance.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @PutMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CheckToken
    public boolean add(@RequestBody Tag tag) {
        int ret = tagService.add(tag);
        return ret == 1;
    }
}
