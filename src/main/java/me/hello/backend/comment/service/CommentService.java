package me.hello.backend.comment.service;

import java.util.List;

import me.hello.backend.comment.dao.CommentDao;
import me.hello.backend.bbs.dto.request.CreateCommentRequest;
import me.hello.backend.bbs.dto.response.CreateCommentResponse;
import me.hello.backend.comment.domain.Comment;
import me.hello.backend.comment.dto.param.CommentListParam;
import me.hello.backend.comment.dto.param.CreateCommentParam;
import me.hello.backend.comment.dto.param.UpdateCommentParam;
import me.hello.backend.comment.dto.request.CommentRequest;
import me.hello.backend.comment.dto.request.UpdateCommentRequest;
import me.hello.backend.comment.dto.response.CommentResponse;
import me.hello.backend.comment.dto.response.DeleteCommentResponse;
import me.hello.backend.comment.dto.response.UpdateCommentResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final CommentDao dao;

    public CommentService(CommentDao dao) {
        this.dao = dao;
    }

    /* 댓글 조회 */
    public CommentResponse getBbsCommentList(CommentRequest req) {
        CommentListParam param = new CommentListParam(req.getBbsSeq());
        param.setPageParam(req.getPage(), 5);

        List<Comment> commentList = dao.getCommentPageList(param);
        Integer pageCnt = dao.getCommentCount(req.getBbsSeq());

        return new CommentResponse(commentList, pageCnt);
    }

    /* 댓글 작성 */
    public CreateCommentResponse createComment(Integer seq, CreateCommentRequest req) {
        CreateCommentParam param = new CreateCommentParam(seq, req);
        dao.createComment(param);
        return new CreateCommentResponse(param.getBbsSeq());
    }

    /* 댓글 삭제 */
    public DeleteCommentResponse deleteComment(Integer seq) {
        Integer deletedRecordCount = dao.deleteComment(seq);
        return new DeleteCommentResponse(deletedRecordCount);
    }

    /* 댓글 수정 */
    @Transactional
    public UpdateCommentResponse updateComment(String id, Integer seq, UpdateCommentRequest req) {
        Comment comment = dao.getCommentBySeq(seq);
        if (!comment.getId().equals(id)) {
            System.out.println("작성자만 댓글을 수정할 수 있습니다.");
            return null;
        }

        Integer updatedRecordCount = dao.updateComment(new UpdateCommentParam(seq, req.getContent()));
        if (updatedRecordCount != 1) {
            System.out.println("댓글 수정에 실패했습니다.");
            return null;
        }

        return new UpdateCommentResponse(updatedRecordCount);
    }


}
