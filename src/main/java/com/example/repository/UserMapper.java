package com.example.repository;

import com.example.domain.user.model.MUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    /** ユーザー登録 */
    int insertOne(MUser mUser);

    /** ユーザー取得 */
    List<MUser> findMany(MUser mUser);

    /** ユーザー取得（1件） */
    MUser findOne(String userId);

    /** ユーザー更新（1件） */
    void updateOne(@Param("userId") String userId,
                          @Param("password") String password,
                          @Param("userName") String userName);

    /** ユーザー削除（1件） */
    int deleteOne(@Param("userId") String userId);

    /** ログインユーザー取得 */
    MUser findLoginUser(String userId);

}
