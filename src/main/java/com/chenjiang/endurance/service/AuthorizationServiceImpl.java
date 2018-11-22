package com.chenjiang.endurance.service;

import com.chenjiang.endurance.common.AuthorizationUtil;
import com.chenjiang.endurance.common.Security;
import com.chenjiang.endurance.entity.Token;
import com.chenjiang.endurance.mapper.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Component
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public boolean checkToken(String authorization) {
        Map<String, String> auth = AuthorizationUtil.parseAuthInfo(authorization);
        String tid = auth.get("tid");
        Token token = tokenMapper.findById(Long.valueOf(tid));
        String message = "cid=" + auth.get("cid") + "&"
                + "uid=" + auth.get("uid") + "&"
                + "tid=" + auth.get("tid") + "&"
                + "nonce=" + auth.get("nonce") + "&"
                + "token=" + token.getToken();
        String hash = Security.sha256(message);
        if (hash.equals(auth.get("hash"))) {
            return true;
        }
        return false;
    }
}
































