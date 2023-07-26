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

    @Override
    public PageResponseDTO<ReplyDTO> getList(Long bno, PageRequestDTO pageRequestDTO) {

        //pageRequestDTO.setSize(100);
        List<ReplyDTO> list = replyMapper.selectList(bno, pageRequestDTO);
        //int total = replyMapper.getBnoCount(bno);

        return PageResponseDTO.<ReplyDTO>withAll()
        .list(list)
        .build();
        
    }

    @Override
    public ReplyDTO selectOne(Long rno) {
        
        return replyMapper.selectOne(rno);
    }

    @Override
    public Long register(ReplyDTO replyDTO) {
        
        return replyMapper.insert(replyDTO);

    }
    
    
}
