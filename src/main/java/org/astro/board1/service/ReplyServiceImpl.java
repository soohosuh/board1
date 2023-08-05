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

        //30개씩 불러오게 사이즈 조정
        pageRequestDTO.setSize(30);

        //total
        int total = replyMapper.getBnoCount(bno);

        //page번호
        int pageNum = pageRequestDTO.getPage();

        //끝페이지 계산
        if(!pageRequestDTO.isReplyLast()){
            //페이지 넘버에 넣어주기
            pageNum = (int) (Math.ceil(total/(double)pageRequestDTO.getSize()));
    
            //페이지번호가 0보다 작거나 같으면 1로 설정
            pageNum = pageNum <= 0 ? 1 : pageNum;
        }
        //끝페이지 번호로 설정
        pageRequestDTO.setPage(pageNum);
    
        // int pageMove = (int) (Math.ceil((pageNum * pageRequestDTO.getSize())/(double)total));
    
        // if(pageMove == 1){
        //   pageRequestDTO.setReplyLast(true);
        // }else{
        //   pageRequestDTO.setReplyLast(false);
        // }
    
        // log.info("=================================================pageMove: " + pageMove);
        // log.info("=================================================pageNum: " + pageNum);
        // log.info("=================================================pageSize: " + pageRequestDTO.getSize());
        // log.info("=================================================total: " + total);
        // log.info("-----------------------------------------------"+pageRequestDTO.isReplyLast());

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
            result = replyDTO.getRno();
        }
            return result;
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
