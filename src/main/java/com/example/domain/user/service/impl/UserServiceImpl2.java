package com.example.domain.user.service.impl;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceImpl2 implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    @Override
    public void signup(MUser mUser) {
        // 存在チェック
        final boolean exists = repository.existsById(mUser.getUserId());
        if (exists) {
            throw new DataAccessException("ユーザーが既に存在"){};
        }
        mUser.setDepartmentId(1);
        mUser.setRole("ROLE_GENERAL");
        // パスワード暗号化
        final String rawPassword = mUser.getPassword();
        mUser.setPassword(encoder.encode(rawPassword));
        // 挿入
        repository.save(mUser);
    }

    /**
     * ユーザー取得
     */
    @Override
    public List<MUser> getUsers(MUser mUser) {
        // 検索条件
        final ExampleMatcher matcher
                = ExampleMatcher
                .matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        return repository.findAll(Example.of(mUser, matcher));
    }

    /**
     * ユーザー取得
     */
    @Override
    public MUser getUserOne(String userId) {
        final Optional<MUser> user = repository.findById(userId);
        return user.orElse(null);
    }

    /**
     * ユーザー更新
     */
    @Transactional
    @Override
    public void updateUserOne(String userId, String password, String userName) {
        // パスワード暗号化
        final String encryptPassword = encoder.encode(password);
        repository.updateUser(userId, encryptPassword, userName);
    }

    /**
     * ユーザー削除（1件）
     */
    @Transactional
    @Override
    public void deleteUserOne(String userId) {

        repository.deleteById(userId);
    }

    /**
     * ログインユーザー取得
     */
    @Override
    public MUser getLoginUser(String userId) {

        return repository.findLoginUser(userId);
    }
}
