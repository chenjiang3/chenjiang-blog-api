package com.chenjiang.endurance.service;

import com.chenjiang.endurance.entity.Tag;
import com.chenjiang.endurance.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public int add(Tag tag) {
        tag.setDeleted(false);
        tag.setType(0);
        return tagMapper.add(tag);
    }

    @Override
    public List<Tag> list() {
        return tagMapper.list();
    }
}
