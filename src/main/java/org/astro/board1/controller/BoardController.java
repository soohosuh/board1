package org.astro.board1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.astro.board1.dto.BoardDTO;
import org.astro.board1.dto.PageRequestDTO;
import org.astro.board1.dto.PageResponseDTO;
import org.astro.board1.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board/")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    @GetMapping("/list")
    public void getList(PageRequestDTO pageRequestDTO, Model model){

        PageResponseDTO<BoardDTO> pageResponseDTO
        = boardService.getList(pageRequestDTO);

        log.info("--------------------");

//        List<BoardDTO> list = boardService.getList();
//        model.addAttribute("list", list);
        log.info(pageResponseDTO.getTotal());
        model.addAttribute("pageResponseDTO", pageResponseDTO); 

        log.info("List");
    }
    @GetMapping("read/{bno}")
    public String getRead(@PathVariable("bno")Integer bno, Model model){

        log.info("get Read");

        model.addAttribute("dto", boardService.getOne(bno));

        return "/board/read";
    }

    @GetMapping("regist")
    public void getRegist(BoardDTO boardDTO){
        log.info("get Regist");
    }

    @PostMapping("/regist")
    public String postRegist(BoardDTO boardDTO){
        boardService.insertOne(boardDTO);


        return "redirect:/board/list";
    }

    @PostMapping("delete/{bno}")
    public String delete(@PathVariable("bno")Integer bno){

        log.info("post delete..."); 

        boardService.deleteOne(bno);

        return "redirect:/board/list";
    }

    @GetMapping("modify/{bno}")
    public String modify(@PathVariable("bno")Integer bno, Model model){

        model.addAttribute("dto", boardService.getOne(bno));

        return "board/modify";

    }

    @PostMapping("/modify/{bno}")
    public String postModify(@PathVariable("bno")Integer bno, BoardDTO boardDTO){

        log.info("=====================================================-");
        log.info(boardDTO);
        log.info(bno);
        log.info("=====================================================-");


        boardService.modifyOne(boardDTO);   

        return "redirect:/board/read/" + bno;
    }



}
