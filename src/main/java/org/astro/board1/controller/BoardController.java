package org.astro.board1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.astro.board1.dto.BoardDTO;
import org.astro.board1.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board/")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    @GetMapping("list")
    public void getList(Model model){

        log.info("--------------------");

//        List<BoardDTO> list = boardService.getList();
//        model.addAttribute("list", list);
        model.addAttribute("dto", boardService.getList());

        log.info("List");
    }
    @GetMapping("read/{bno}")
    public String getRead(@PathVariable("bno")Integer bno, Model model){

        log.info("get Read");

        model.addAttribute("dto", boardService.getOne(bno));

        return "/board/read";
    }
}
