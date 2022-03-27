package com.example.domain.user.service.impl;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    /**
     * ユーザー登録
     */
    @Override
    public void signup(final MUser mUser) {
        mUser.setDepartmentId(1); // 部署
        mUser.setRole("ROLE_GENERAL"); // ロール
        // パスワード暗号化
        final String rawPassword = mUser.getPassword();
        mUser.setPassword(encoder.encode(rawPassword));

        mapper.insertOne(mUser);
    }

    /**
     * ユーザー取得
     */
    @Override
    public List<MUser> getUsers(MUser mUser) {
        return mapper.findMany(mUser);
    }

    /**
     * ユーザー取得（1件）
     */
    @Override
    public MUser getUserOne(String userId) {
        return mapper.findOne(userId);
    }

    /**
     * ユーザー更新
     */
    @Transactional
    @Override
    public void updateUserOne(String userId, String password, String userName) {
        // パスワード暗号化
        final String encryptPassword = encoder.encode(password);
        mapper.updateOne(userId, encryptPassword, userName);
        // 例外を発生させる
//        System.out.println(i/0);
    }

    /**
     * ユーザー削除
     */
    @Override
    public void deleteUserOne(String userId) {
        mapper.deleteOne(userId);
    }

    /**
     * ログインユーザー情報取得
     */
    @Override
    public MUser getLoginUser(String userId) {
        return mapper.findLoginUser(userId);
    }


}
