package com.board.Service;

import com.board.dto.PostRequest;
import com.board.repository.PostRepository;
import com.board.vo.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    final private PostRepository postRepository;

    @Transactional
    public List<Post> getPostList(int startIndex, int pageSize) throws SQLException, ClassNotFoundException {
        return postRepository.findByPage(startIndex, pageSize);
    }

    @Transactional
    public Post getPostDetail (int postId) {
        return postRepository.findById(postId);
    }

    public void update(PostRequest postInfo, int postId) {
        postRepository.update(postInfo, postId);
    }

    public void save(PostRequest board){
        postRepository.save(board);
    }

    public void deleteById(int postId){ postRepository.deleteById(postId);}
}
