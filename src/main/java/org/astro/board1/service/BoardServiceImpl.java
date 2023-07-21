package org.astro.board1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.astro.board1.dto.BoardDTO;
import org.astro.board1.mappers.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;
    @Override
    public List<BoardDTO> getList() {


        return boardMapper.getList();
    }
}
