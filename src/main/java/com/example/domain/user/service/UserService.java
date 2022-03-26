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
    public List<MUser> getUsers();

}
