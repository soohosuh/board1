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
    //댓글, 대댓글
    @Override
    public Integer register(ReplyDTO replyDTO) {
        Integer result = null;
        int gno = replyDTO.getGno();

        //댓글일 때
        if(gno == 0){
            //정상 등록 확인을 위한 count
            //댓글이므로 registerReply
            int count = replyMapper.registerReply(replyDTO);

            //정상 등록 아닐 시 예외처리
            if(count != 1){
                throw new RuntimeException("Reply Insert Exception");
            }

            //rno값을 가져온 뒤 update
            Integer rno = replyDTO.getRno();
            replyMapper.updateReplyGno(rno);
            result = rno;
        }else{
            //대댓글 일 때
            int count = replyMapper.registerReplyChild(replyDTO);

            //정상 등록 아닐 시 예외처리
            if(count != 1){
                throw new RuntimeException("Reply Insert Exception");
            }
            return result;
        }

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
