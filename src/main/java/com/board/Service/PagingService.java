package com.board.Service;

import com.board.paging.Pagination;
import com.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PagingService {
    private final PostRepository postRepository;

    @Transactional
    public Pagination paging(int page){
        // 총 게시물 수
        int totalListCnt = postRepository.countBy();
        return new Pagination(totalListCnt, page);
    }
}
