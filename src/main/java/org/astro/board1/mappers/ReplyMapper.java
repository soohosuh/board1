package org.astro.board1.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.astro.board1.dto.PageRequestDTO;
import org.astro.board1.dto.ReplyDTO;

public interface ReplyMapper {
    
    //목록
    List<ReplyDTO> getList(@Param("bno")Integer bno, @Param("pr")PageRequestDTO pageRequestDTO);

    //total
    int getBnoCount();

    /* gno가 0일 때 실행하는 영역 */
    //register 댓글
    int registerReply(ReplyDTO replyDTO);

    // 댓글의 gno 업데이트
    int updateReplyGno(Integer gno);
    /* //gno가 0일 때 실행하는 영역 */

    /* gno가 0이 아닐 때 실행하는 영역 */
    //register 대댓글
    int registerReplyChild(ReplyDTO replyDTO);
    /* //gno가 0이 아닐 때 실행하는 영역 */

    //read
    ReplyDTO selectOne(Integer rno);

    //수정
    int modifyReply(ReplyDTO replyDTO);

    //delete
    int delete(Integer rno);

}
