package com.example.gasip.comment.repository;

import com.example.gasip.comment.model.Comment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.example.gasip.comment.model.QComment.comment;

@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public void addCommentLikeCount(Comment selectedComment) {
        queryFactory.update(comment)
                .set(comment.commentLike, comment.commentLike.add(1))
                .where(comment.eq(selectedComment))
                .execute();
    }

    @Override
    public void subCommentLikeCount(Comment selectedComment) {
        queryFactory.update(comment)
                .set(comment.commentLike, comment.commentLike.subtract(1))
                .where(comment.eq(selectedComment))
                .execute();
    }
}
