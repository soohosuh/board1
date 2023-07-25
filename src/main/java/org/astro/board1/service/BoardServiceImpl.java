package org.astro.board1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.astro.board1.dto.BoardDTO;
import org.astro.board1.dto.PageRequestDTO;
import org.astro.board1.dto.PageResponseDTO;
import org.astro.board1.mappers.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;

    @Override
    public PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {

        List<BoardDTO> list = boardMapper.getList(pageRequestDTO);
        long total = boardMapper.listCount(pageRequestDTO);


        return PageResponseDTO.<BoardDTO>withAll()
                .list(list)
                .total(total)
                .build();
    }

    @Override
    public BoardDTO getOne(Integer bno) {

        return boardMapper.getOne(bno);
    }

    @Override
    public int insertOne(BoardDTO boardDTO) {
        
        return boardMapper.insertOne(boardDTO);
    }

    @Override
    public int deleteOne(Integer bno) {
        
        return boardMapper.deleteOne(bno);
    }

    @Override
    public int modifyOne(BoardDTO boardDTO) {
        
        return boardMapper.modifyOne(boardDTO);
    }
}
