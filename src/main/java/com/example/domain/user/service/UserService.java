package com.example.domain.user.service;

import com.example.domain.user.model.MUser;

import java.util.List;

public interface UserService {

    /**
     * ユーザ登録
     * @param mUser
     */
    public void signup(MUser mUser);

    /**
     * ユーザー取得
     * @return
     */
    public List<MUser> getUsers(MUser mUser);

    /**
     * ユーザー取得
     */
    public MUser getUserOne(String userId);

    /**
     * ユーザー更新（1件）
     */
    public void updateUserOne(String userId, String password, String userName);

    /**
     * ユーザー削除（1件）
     */
    public void deleteUserOne(String userId);

}
