package org.astro.board1.controller;

/*
 * page물고가야 하는 곳은 PageRequestDTO 파라미터 설정
 * read, modify
 */

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.astro.board1.dto.BoardDTO;
import org.astro.board1.dto.BoardListDTO;
import org.astro.board1.dto.BoardRegisterDTO;
import org.astro.board1.dto.PageRequestDTO;
import org.astro.board1.dto.PageResponseDTO;
import org.astro.board1.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board/")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    // 서비스 의존성 주입
    private final BoardService boardService;

    //list
    @GetMapping("list")
    public void getList(PageRequestDTO pageRequestDTO, Model model){

        log.info("--------------------");

        PageResponseDTO<BoardListDTO> list
        = boardService.getList(pageRequestDTO);

    
       model.addAttribute("boardList", list);

    }
    //read
    @GetMapping("read/{bno}")
    public String getRead(@PathVariable("bno")Integer bno, PageRequestDTO pageRequestDTO, Model model){

        log.info("get Read");

        //DTO 로 선언
        BoardDTO boardDTO = boardService.getOne(bno);
        log.info(boardDTO);

        model.addAttribute("read", boardDTO);

        return "/board/read";
    }

    @GetMapping("regist")
    public void getRegist(BoardDTO boardDTO){


        log.info("get Regist");
    }

    @PostMapping("regist")
    public String postRegist(BoardRegisterDTO boardRegisterDTO, RedirectAttributes rttr){
        boardService.insertOne(boardRegisterDTO);

        boardService.insertOne(boardRegisterDTO);

        //redirect 메세지
        rttr.addFlashAttribute("message", "게시물이 등록 되었습니다.");
        return "redirect:/board/list";
    }

    //delete
    @PostMapping("delete/{bno}")
    public String delete(@PathVariable("bno")Integer bno, RedirectAttributes rttr){

        log.info("post delete..."); 

        boardService.deleteOne(bno);

        //list로 메세지 전달
        rttr.addFlashAttribute("message", bno + " 게시물이 삭제 되었습니다.");

        return "redirect:/board/list";
    }

    //modify
    @GetMapping("modify/{bno}")
    public String modify(PageRequestDTO pageRequestDTO, @PathVariable("bno")Integer bno, Model model){

        //DTO 로 선언
        BoardDTO boardDTO = boardService.getOne(bno);

        model.addAttribute("modify", boardDTO);

        return "board/modify";

    }

    @PostMapping("modify/{bno}")
    public String postModify(RedirectAttributes rttr, BoardDTO boardDTO){

        log.info("=====================================================-");
        
        boardService.modifyOne(boardDTO);   

        rttr.addFlashAttribute("message", boardDTO.getBno() + " 게시물이 수정 되었습니다.");

        return "redirect:/board/read/" + boardDTO.getBno();
    }



}
