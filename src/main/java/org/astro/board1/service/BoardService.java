package org.astro.board1.service;

import org.astro.board1.dto.BoardDTO;
import org.astro.board1.dto.BoardListDTO;
import org.astro.board1.dto.PageRequestDTO;
import org.astro.board1.dto.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BoardService {

    //list
    PageResponseDTO<BoardListDTO> getList(PageRequestDTO pageRequestDTO);

    //read
    BoardDTO getOne(Integer bno);

    //register
    void insertOne(BoardDTO boardDTO);

    //delete
    void deleteOne(Integer bno);

    //modify
    void modifyOne(BoardDTO boardDTO);

    //List<String> getImages(Integer pno);

    //viewCount
    void viewCount(Integer bno);
}
