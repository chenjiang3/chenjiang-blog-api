package com.chenjiang.endurance.service;

import com.chenjiang.endurance.entity.LoginBody;
import com.chenjiang.endurance.entity.RegisterBody;
import com.chenjiang.endurance.entity.User;
import com.chenjiang.endurance.entity.Token;

public interface UserService {
    User findUserByMobile(String mobile);
    User authorInfo(String mobile);
    String register(RegisterBody registerUser);
    Token login(LoginBody loginBody);
    boolean logout(Long tokenId);
}
