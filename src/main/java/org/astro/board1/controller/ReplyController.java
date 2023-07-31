package org.astro.board1.controller;

import java.util.Map;

import javax.print.attribute.standard.Media;

import org.astro.board1.dto.PageRequestDTO;
import org.astro.board1.dto.PageResponseDTO;
import org.astro.board1.dto.ReplyDTO;
import org.astro.board1.service.ReplyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/replies/")
public class ReplyController {

    // service 의존성 주입
    private final ReplyService service;


    //list
    //produces: 만들어내는 데이터
    @GetMapping(value = "{bno}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponseDTO<ReplyDTO> getReplyList(
        @PathVariable("bno")Long bno, PageRequestDTO pageRequestDTO){


        return service.getList(bno, pageRequestDTO);
    }

    //register
    //@RequestBody: 보내는 데이터 JSON형식
    //rno 데이터 전달 위해 return타입 Map
    @PostMapping("{bno}/register")
    public Map<String, Long> register(@PathVariable("bno")Long bno, @RequestBody ReplyDTO replyDTO){

        log.info("bno"  + bno);
        
        //안전장치
        replyDTO.setBno(bno);
        log.info(replyDTO);

        Long rno = service.register(replyDTO);

        log.info(rno);

        return Map.of("result", rno);
    }

    // modify
    @PutMapping("{rno}")
    public int modifyReply(@PathVariable("rno") int rno, ReplyDTO replyDTO){

        return service.modifyReply(replyDTO);
    }

    //입력
    // replies/517/register
    
}
