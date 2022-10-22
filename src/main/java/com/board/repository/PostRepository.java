package com.board.repository;

import com.board.dto.PostRequest;
import com.board.vo.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostRepository {
    @Select("SELECT * FROM POST")
    List<Post> findAll();

    @Select("SELECT * FROM POST ORDER BY id DESC limit #{startIndex}, #{pageSize}")
    List<Post> findByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    @Select("SELECT * FROM POST WHERE id = #{id}")
    Post findById(@Param("id") int id);

    @Select("SELECT COUNT(*) FROM POST")
    int countBy();

    @Insert("INSERT INTO POST (title, author, content, created_date, modified_date) " +
            "VALUES (#{post.title},#{post.author},#{post.content}, now(), now())")
    void save(@Param("post") PostRequest post);

    @Update("UPDATE POST SET title=#{post.title}, content =#{post.content}, modified_date = now() WHERE id =#{postId}")
    void update(@Param("post") PostRequest post, @Param("postId") int id);

    @Delete("DELETE FROM POST WHERE id =#{id}")
    void deleteById(@Param("id") int id);
}
