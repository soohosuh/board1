package org.astro.board1.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.astro.board1.dto.PageRequestDTO;
import org.astro.board1.dto.ReplyDTO;

public interface ReplyMapper {
    
    //목록
    List<ReplyDTO> selectList(@Param("bno")long bno, @Param("dto")PageRequestDTO pageRequestDTO);


    ReplyDTO selectOne(Long rno);
    
    //등록
    Long insert(ReplyDTO replyDTO);

    //수정
    int modifyReply(ReplyDTO replyDTO);

}
