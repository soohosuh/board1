package org.astro.board1.service;

import java.util.List;

import org.astro.board1.dto.PageRequestDTO;
import org.astro.board1.dto.PageResponseDTO;
import org.astro.board1.dto.ReplyDTO;
import org.astro.board1.mappers.ReplyMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyMapper replyMapper;

    //list
    @Override
    public PageResponseDTO<ReplyDTO> getList(Integer bno, PageRequestDTO pageRequestDTO) {

        int total = replyMapper.getBnoCount();

        //pageRequestDTO.setSize(100);
        List<ReplyDTO> list = replyMapper.getList(bno, pageRequestDTO);
        //int total = replyMapper.getBnoCount(bno);

        return PageResponseDTO.<ReplyDTO>withAll()
        .list(list)
        .total(total)
        .pageRequestDTO(pageRequestDTO)
        .build();
        
    }

    //read
    @Override
    public ReplyDTO selectOne(Integer rno) {
        
        return replyMapper.selectOne(rno);
    }

    //regist
    @Override
    public Integer register(ReplyDTO replyDTO) {
        
        return replyMapper.register(replyDTO);

    }
    
    //modify
    @Override
    public void modifyReply(ReplyDTO replyDTO) {
        
        replyMapper.modifyReply(replyDTO);
    }

    //delete
    @Override
    public void delete(Integer rno) {
        replyMapper.delete(rno);
    }

    
    
    
}
