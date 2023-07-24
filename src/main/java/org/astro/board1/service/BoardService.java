package org.astro.board1.service;

import org.astro.board1.dto.BoardDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BoardService {

    List<BoardDTO> getList();

    BoardDTO getOne(Integer bno);

    int insertOne(BoardDTO boardDTO);

    int deleteOne(Integer bno);

    int modifyOne(BoardDTO boardDTO);
}
