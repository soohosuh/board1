package org.astro.board1.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

    private int rno;
    private int bno;
    private String reply;
    private String replyer;
    private String replyDate;
    @Builder.Default
    private int gno = 0;              //대댓글처리 번호 0 default설정
    private int step;                   //대댓글 여부 확인
    private boolean status;             //댓글 삭제여부
    
}
