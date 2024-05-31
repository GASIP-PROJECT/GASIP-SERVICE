package com.example.gasip.board.repository;

import com.example.gasip.board.dto.BoardProfessorReadResponse;
import com.example.gasip.board.dto.BoardReadRequest;
import com.example.gasip.board.dto.BoardReadResponse;
import com.example.gasip.board.model.Board;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {

    List<BoardReadResponse> findAllByMemberId(Long memberId,Pageable pageable);
    List<BoardReadResponse> findAllBoard();

    List<BoardReadRequest> findAllByPostId(Long postId);

    void addLikeCount(Board board);

    void subLikeCount(Board board);

    void addViewCount(Board board);

    List<BoardReadResponse> findByProfNameLike(String profName);

    List<BoardReadResponse> findBestBoard(Pageable pageable);

    /**
     * 교수 상세정보 넘기기
     */
    List<BoardProfessorReadResponse> findBoarByProfessor(Long profId);

    /**
     * 자유게시판 게시글 불러오기
     */
    List<BoardProfessorReadResponse> findFreeBoardByProfessor(Pageable pageable);
}
