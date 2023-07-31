package org.astro.board1.mappers;

import org.astro.board1.dto.BoardDTO;
import org.astro.board1.dto.BoardImageDTO;
import org.astro.board1.dto.PageRequestDTO;

import java.util.List;

public interface BoardMapper {

    //board list
    List<BoardDTO> getList(PageRequestDTO pageRequestDTO);

    //board count
    long listCount(PageRequestDTO pageRequestDTO);

    //board read
    BoardDTO getOne(Integer bno);

    int insertOne(BoardDTO boardDTO);

    //board delete
    int deleteOne(Integer bno);

    //board modify
    int modifyOne(BoardDTO boardDTO);

    void setBoardImage(BoardImageDTO imageDTO);

    
    
    


}
