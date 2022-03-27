package com.example.domain.user.service;

import com.example.domain.user.model.MUser;

import java.util.List;

public interface UserService {

    /**
     * ユーザ登録
     */
    void signup(MUser mUser);

    /**
     * ユーザー取得
     */
    List<MUser> getUsers(MUser mUser);

    /**
     * ユーザー取得
     */
    MUser getUserOne(String userId);

    /**
     * ユーザー更新（1件）
     */
    void updateUserOne(String userId, String password, String userName);

    /**
     * ユーザー削除（1件）
     */
    void deleteUserOne(String userId);

    /**
     * ログインユーザー情報取得
     */
    MUser getLoginUser(String userId);
}
