package com.chenjiang.endurance.service;

import com.chenjiang.endurance.entity.Tag;

import java.util.List;

public interface TagService {
    int add(Tag tag);
    List<Tag> list();
}
