package com.chenjiang.endurance.controller;

import com.chenjiang.endurance.common.AuthorizationUtil;
import com.chenjiang.endurance.entity.LoginBody;
import com.chenjiang.endurance.entity.RegisterBody;
import com.chenjiang.endurance.entity.User;
import com.chenjiang.endurance.interceptor.CheckToken;
import com.chenjiang.endurance.entity.Token;
import com.chenjiang.endurance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/find-by-mobile/{mobile}")
    public User findUserByMobile(@PathVariable("mobile") String mobile) {
        User user = userService.findUserByMobile(mobile);
        user.setPassword(null);
        return user;
    }

    @GetMapping(value = "/author-info/{mobile}")
    public User authorInfo(@PathVariable(value = "mobile") String mobile) {
        User user = userService.authorInfo(mobile);
        return user;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String register(@RequestBody RegisterBody registerUser) {
        return this.userService.register(registerUser);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Token login(@RequestBody LoginBody loginBody) {
        return this.userService.login(loginBody);
    }

    @DeleteMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CheckToken
    public boolean logout(@RequestHeader("Authorization") String authHeader) {
        Map<String, String> auth = AuthorizationUtil.parseAuthInfo(authHeader);
        Long tid = Long.valueOf(auth.get("tid"));
        return this.userService.logout(tid);
    }

}
