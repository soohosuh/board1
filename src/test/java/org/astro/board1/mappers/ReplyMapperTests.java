package org.astro.board1.mappers;

import org.astro.board1.dto.PageRequestDTO;
import org.astro.board1.dto.ReplyDTO;
import org.astro.board1.service.ReplyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyMapperTests {
    
    @Autowired
    private ReplyMapper replyMapper;

    @Test
    public void listTest(){

        PageRequestDTO dto = PageRequestDTO.builder().build();
        
        log.info(replyMapper.selectList(1, dto));

    }

    @Test
    public void registTest(){

        ReplyDTO replyDTO = ReplyDTO.builder()
        .bno(100L)
        .reply("REply")
        .replyer("REPLyer")       
        .build();


        replyMapper.insert(replyDTO);
    }

}
