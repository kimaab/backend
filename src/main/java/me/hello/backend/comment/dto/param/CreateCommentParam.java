package me.hello.backend.comment.dto.param;

import lombok.Getter;
import lombok.Setter;
import me.hello.backend.bbs.dto.request.CreateCommentRequest;

@Getter
@Setter
public class CreateCommentParam {

    private Integer bbsSeq;
    private int seq;
    private String id;
    private String content;

    public CreateCommentParam(Integer bbsSeq, CreateCommentRequest req) {
        this.bbsSeq = bbsSeq;
        this.id = req.getId();
        this.content = req.getContent();
    }

}
