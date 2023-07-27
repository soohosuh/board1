package org.astro.board1.service;

import org.astro.board1.dto.PageRequestDTO;
import org.astro.board1.dto.PageResponseDTO;
import org.astro.board1.dto.ReplyDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReplyService {

    PageResponseDTO<ReplyDTO> getList(Long bno, PageRequestDTO pageRequestDTO);

    ReplyDTO selectOne(Long rno);

    Long register(ReplyDTO replyDTO);

    int modifyReply(ReplyDTO replyDTO);
    
}
