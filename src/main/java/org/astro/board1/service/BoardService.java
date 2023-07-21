package org.astro.board1.service;

import org.astro.board1.dto.BoardDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BoardService {

    List<BoardDTO> getList();
}
