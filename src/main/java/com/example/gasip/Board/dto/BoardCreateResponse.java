package com.example.gasip.Board.dto;

import com.example.gasip.Board.model.Board;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardCreateResponse {

    @NotNull
    private Long postId;
    @NotNull
    private String content;
    private Long clickCount;
    private Long likeCount;
    @NotNull
    private LocalDateTime regDate;
    @NotNull
    private LocalDateTime updateDate;
    @NotNull
    private Long profId;

    @Builder
    public BoardCreateResponse(Long postId, String content, Long clickCount, Long likeCount, LocalDateTime regDate, LocalDateTime updateDate, Long profId) {
        this.postId = postId;
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.profId = profId;
    }

    //fromEntity메서드 개발
    public BoardCreateResponse fromEntity() {
        return BoardCreateResponse.builder()
                .postId(postId)
                .content(content)
                .clickCount(clickCount)
                .likeCount(likeCount)
                .regDate(regDate)
                .updateDate(updateDate)
                .profId(profId)
                .build();
    }
//    public BoardCreateResponse fromEntity(Board board) {
//        return BoardCreateResponse.builder()
//                .postId(board.getPostId())
//                .content(board.getContent())
//                .clickCount(board.getClickCount())
//                .likeCount(board.getLikeCount())
//                .regDate(board.getRegDate())
//                .updateDate(board.getUpdateDate())
//                .profId(board.getProfId())
//                .build();
//    }


}
