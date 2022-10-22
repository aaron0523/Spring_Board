package com.board.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {

    /** 1. 페이지 당 보여지는 게시글의 최대 개수 **/
    private int pageSize = 5;

    /** 2. 페이징된 버튼의 블럭당 최대 개수 **/
    private int blockSize = 10;

    /** 3. 현재 페이지 **/
    private int currPage = 1;

    /** 4. 현재 블럭 **/
    private int currBlock = 1;

    /** 5. 총 게시글 수 **/
    private int totalPostCnt;

    /** 6. 총 페이지 수 **/
    private int totalPageCnt;

    /** 7. 총 블럭 수 **/
    private int totalBlockCnt;

    /** 8. 블럭 시작 페이지 **/
    private int startPage = 1;

    /** 9. 블럭 마지막 페이지 **/
    private int endPage = 1;

    /** 10. DB 접근 시작 index **/
    private int startIndex = 0;

    /** 11. 이전 블럭의 마지막 페이지 **/
    private int preBlock;

    /** 12. 다음 블럭의 시작 페이지 **/
    private int nextBlock;


    //생성자
    public Pagination(int totalPostCnt, int page) {
        this.currPage = page; //현제페이지
        this.totalPostCnt = totalPostCnt; //총 post 겟수
        this.totalPageCnt = (int) Math.ceil(totalPostCnt * 1.0 / pageSize); //총 page 갯수
        this.totalBlockCnt = (int) Math.ceil(totalPageCnt * 1.0 / blockSize); // 총 블럭수 (
        this.currBlock = (int) Math.ceil((page * 1.0)/blockSize); // 현재 블럭 위치
        this.startPage = (currBlock - 1) * blockSize + 1; // 블록 시작 페이지

        this.endPage = startPage + blockSize - 1; // 블록 끝 페이지
        /* === 블럭 마지막 페이지에 대한 validation ===*/
        if(endPage > totalPageCnt){this.endPage = totalPageCnt;}

        this.preBlock = (currBlock * blockSize) - blockSize; // 이전 블록(이전블록의 마지막 페이지로 이동)
        /* === 이전 블럭에 대한 validation === */
        if(preBlock < 1) {this.preBlock = 1;}

        this.nextBlock = (currBlock * blockSize) + 1; // 다음 블록의 첫 페이지 이동
        /* === 다음 블럭에 대한 validation ===*/
        if(nextBlock > totalPageCnt) {nextBlock = totalPageCnt;}

        /** 10. DB 접근 시작 index **/
        this.startIndex = (page-1) * pageSize;
    }
}
