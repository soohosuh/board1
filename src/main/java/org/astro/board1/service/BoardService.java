package org.astro.board1.service;

import org.astro.board1.dto.BoardDTO;
import org.astro.board1.dto.PageRequestDTO;
import org.astro.board1.dto.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BoardService {

    PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO);

    BoardDTO getOne(Integer bno);

    int insertOne(BoardDTO boardDTO);

    int deleteOne(Integer bno);

    int modifyOne(BoardDTO boardDTO);
}
