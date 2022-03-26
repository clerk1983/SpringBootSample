package com.example.domain.user.service.impl;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    /**
     * ユーザー登録
     * @param mUser
     */
    @Override
    public void signup(final MUser mUser) {
        mUser.setDepartmentId(1); // 部署
        mUser.setRole("ROLE_GENERAL"); // ロール
        mapper.insertOne(mUser);
    }

    /**
     * ユーザー取得
     * @return
     */
    @Override
    public List<MUser> getUsers(MUser mUser) {
        return mapper.findMany(mUser);
    }

    /**
     * ユーザー取得（1件）
     * @param userId
     * @return
     */
    @Override
    public MUser getUserOne(String userId) {
        return mapper.findOne(userId);
    }

    /**
     * ユーザー更新
     * @param userId
     * @param password
     * @param userName
     */
    @Transactional
    @Override
    public void updateUserOne(String userId, String password, String userName) {
        mapper.updateOne(userId, password, userName);
        // 例外を発生させる
//        int i = 1 / 0;
//        System.out.println(i);
    }

    /**
     * ユーザー削除
     * @param userId
     */
    @Override
    public void deleteUserOne(String userId) {
        mapper.deleteOne(userId);
    }


}
