package org.astro.board1.service;

import org.astro.board1.dto.PageRequestDTO;
import org.astro.board1.dto.PageResponseDTO;
import org.astro.board1.dto.ReplyDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReplyService {

    //list
    PageResponseDTO<ReplyDTO> getList(Integer bno, PageRequestDTO pageRequestDTO);

    //read
    ReplyDTO selectOne(Integer rno);

    //register
    Integer register(ReplyDTO replyDTO);

    //modify
    void modifyReply(ReplyDTO replyDTO);

    //delete
    void delete(Integer rno);
    
}
