package com.example.gasip.comment.dto;

import com.example.gasip.comment.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CommentReadResponse implements Serializable {
    private Long postId;
    private Long commentId;
    private Long memberId;
    private String content;
    private Long commentLike;
    private Long parentId;
    private List<CommentChildrenReadResponse> commentChildren = new ArrayList<>();

    public static CommentReadResponse fromEntity(Comment comment) {
        // 부모 댓글인 경우
        if (comment.getParentComment() == null) {
            return buildCommentDtoWithChildrenComment(comment);
        }
        // 자식 댓글인 경우
        else {
            return null;
        }
    }
    private static CommentReadResponse buildCommentDtoWithChildrenComment(Comment comment) {
        return CommentReadResponse.builder()
            .postId(comment.getBoard().getPostId())
            .commentId(comment.getCommentId())
            .memberId(comment.getMember().getMemberId())
            .content(comment.getContent())
            .commentChildren(comment.getCommentChildren()
                .stream()
                .map(CommentChildrenReadResponse::fromEntity)
                .collect(Collectors.toList()))
            .build();
    }
}
