package org.astro.board1.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.astro.board1.dto.PageRequestDTO;
import org.astro.board1.dto.ReplyDTO;

public interface ReplyMapper {
    
    List<ReplyDTO> selectList(@Param("bno")long bno, @Param("dto")PageRequestDTO pageRequestDTO);

    ReplyDTO selectOne(Long rno);

    Long insert(ReplyDTO replyDTO);

}
