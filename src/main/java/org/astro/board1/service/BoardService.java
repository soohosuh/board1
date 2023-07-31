package org.astro.board1.service;

import org.astro.board1.dto.BoardDTO;
import org.astro.board1.dto.PageRequestDTO;
import org.astro.board1.dto.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BoardService {

    //list
    PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO);

    //read
    BoardDTO getOne(Integer bno);

    //register
    int insertOne(BoardDTO boardDTO, boolean makeThumbnail);

    //delete
    int deleteOne(Integer bno);

    //modify
    int modifyOne(BoardDTO boardDTO);

    List<String> getImages(Integer pno);
}
