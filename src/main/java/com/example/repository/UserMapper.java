package com.example.repository;

import com.example.domain.user.model.MUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /** ユーザー登録 */
    public int insertOne(MUser mUser);

    /** ユーザー取得 */
    public List<MUser> findMany();

    /** ユーザー取得（1件） */
    public MUser findOne(String userId);


}
