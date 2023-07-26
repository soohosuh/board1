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

    private final ReplyService service;


    @GetMapping(value = "{bno}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponseDTO<ReplyDTO> getReplyList(
        @PathVariable("bno")Long bno, PageRequestDTO pageRequestDTO){

        return service.getList(bno, pageRequestDTO);
    }

    @PostMapping("{bno}/new")
    public Map<String, Long> register(@PathVariable("bno")Long bno, @RequestBody ReplyDTO replyDTO){

        //replyDTO.setBno(bno);

        Long rno = service.register(replyDTO);

        return Map.of("result",rno);
    }
    
}
