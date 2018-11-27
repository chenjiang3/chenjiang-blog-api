package com.chenjiang.endurance.service;

import com.chenjiang.endurance.common.BasicErrorCode;
import com.chenjiang.endurance.common.Security;
import com.chenjiang.endurance.entity.LoginBody;
import com.chenjiang.endurance.entity.RegisterBody;
import com.chenjiang.endurance.entity.User;
import com.chenjiang.endurance.exception.UserException;
import com.chenjiang.endurance.entity.Token;
import com.chenjiang.endurance.mapper.TokenMapper;
import com.chenjiang.endurance.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    TokenMapper tokenMapper;

    @Override
    public User findUserByMobile(String mobile) {
        User user = userMapper.findUserByMobile(mobile);
        if (user == null) {
            throw new UserException(BasicErrorCode.USER_NOT_FIND);
        }
        return user;
    }

    @Override
    public User findById(String id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> userList(int pageIndex, int pageSize) {
        return userMapper.userList(pageIndex, pageSize);
    }

    @Override
    public User authorInfo(String mobile) {
        User user = userMapper.findUserByMobile(mobile);
        if (user == null) {
            throw new UserException(BasicErrorCode.USER_NOT_FIND);
        }
        user.setPassword("");
        user.setSalt("");
        return user;
    }

    @Override
    public String register(RegisterBody registerBody) {
        Integer maxUserId = this.userMapper.findMaxUserId();
        User user = new User();
        if (maxUserId != null) {
            user.setUserId(maxUserId + 1);
        } else {
            user.setUserId(1);
        }

        user.setStatus();
        user.setVersion(0);
        user.setClientId(registerBody.getClientId());
        user.setMobile(registerBody.getMobile());
        user.setSalt(registerBody.getSalt());
        user.setPassword(registerBody.getPassword());
        if (StringUtils.isEmpty(registerBody.getClientId())) {
            return "clientId 不能为空";
        }
        if (StringUtils.isEmpty(registerBody.getSalt())) {
            return "salt 不能为空";
        }
        if (StringUtils.isEmpty(registerBody.getMobile())) {
            return "手机号不能为空";
        }
        if (StringUtils.isEmpty(registerBody.getPassword())) {
            return "密码不能为空";
        }

        user.setUserName("User" + Security.randomString(8));
        this.userMapper.register(user);
        return "注册成功";
    }

    @Override
    @Transactional
    public boolean logout(Long tokenId) {
        tokenMapper.deleteToken(tokenId);
        return true;
    }

    @Override
    public Token login(LoginBody loginBody) {
        String mobile = loginBody.getMobile();
        String password = loginBody.getPassword();
        String loginSalt = loginBody.getSalt();

        User user = this.findUserByMobile(mobile);
        String hash = Security.sha256(loginSalt + "-" + user.getPassword());
        if (hash.equals(password) == false) {
            throw new UserException(BasicErrorCode.WRONG_PASSWORD);
        }

        Token token = new Token();
        token.setClientId(loginBody.getClientId());
        token.setExpireTime(new Date()); // MARK: - TODO
        token.setToken(Security.randomString(64));
        token.setUserId(loginBody.getUserId());

        tokenMapper.createToken(token);

        return token;
    }
}






























