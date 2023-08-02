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

    //read
    ReplyDTO selectOne(Integer rno);
    
    //등록
    int register(ReplyDTO replyDTO);

    //수정
    int modifyReply(ReplyDTO replyDTO);

    //delete
    int delete(Integer rno);

}
