package org.astro.board1.mappers;

import org.astro.board1.dto.BoardDTO;
import org.astro.board1.dto.PageRequestDTO;

import java.util.List;

public interface BoardMapper {

    

    List<BoardDTO> getList(PageRequestDTO pageRequestDTO);

    BoardDTO getOne(Integer bno);

    int insertOne(BoardDTO boardDTO);

    int deleteOne(Integer bno);

    int modifyOne(BoardDTO boardDTO);

    long listCount(PageRequestDTO pageRequestDTO);
    
    


}
