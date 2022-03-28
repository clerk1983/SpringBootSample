package com.example.domain.user.service.impl;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
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

    @Override
    public List<MUser> getUsers(MUser mUser) {
        return repository.findAll();
    }

    @Override
    public MUser getUserOne(String userId) {
        final Optional<MUser> user = repository.findById(userId);
        return user.orElse(null);
    }

    @Transactional
    @Override
    public void updateUserOne(String userId, String password, String userName) {

    }

    /**
     * ユーザー削除（1件）
     */
    @Transactional
    @Override
    public void deleteUserOne(String userId) {
        repository.deleteById(userId);
    }

    @Override
    public MUser getLoginUser(String userId) {
        final Optional<MUser> user = repository.findById(userId);
        return user.orElse(null);
    }
}
