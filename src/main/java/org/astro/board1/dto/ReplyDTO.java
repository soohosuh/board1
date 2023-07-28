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
@ToString
public class ReplyDTO {

    private long rno;
    private long bno;
    private String reply;
    private String replyer;
    private LocalDate replyDate;

    private long gno = 0L;
    private int step;                   //대댓글 여부 확인
    private boolean status;             //댓글 삭제여부
}
