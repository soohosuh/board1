package org.astro.board1.mappers;

import lombok.extern.log4j.Log4j2;
import org.astro.board1.dto.BoardDTO;
import org.astro.board1.service.BoardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Log4j2
public class BoardMapperTests {

    @Autowired(required = false)
    private BoardMapper boardMapper;

    @Autowired(required = false)
    private BoardService boardService;

    @Transactional
    @Commit
    @Test
    public void testInsert(){

        log.info("0------------------------------------");

        BoardDTO boardDTO = BoardDTO.builder()
                .title("Title Test")
                .content("Content Test")
                .writer("user00")
                .duedate("2023-07-30")
                .build();
            

        log.info("1------------------------------------");

        // boardMapper.insert(boardDTO);

        // log.info("2------------------------------------");
        // int count = boardMapper.insert(boardDTO);

        log.info("0------------------------------------");
        //log.info("count" + count);

    }

    @Test
    public void testList(){

        //log.info(boardService.getList());


    }

}
