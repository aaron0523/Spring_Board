package com.board.repository;

import com.board.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepository {

    @Select("SELECT * FROM USER WHERE email = #{email} and password =#{pw}")
    User findByUserIdAndPassword(@Param("email") String email, @Param("pw") String pw);
}
