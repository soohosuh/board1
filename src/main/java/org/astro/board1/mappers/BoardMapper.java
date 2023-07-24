package org.astro.board1.mappers;

import org.astro.board1.dto.BoardDTO;

import java.util.List;

public interface BoardMapper {

    int insert(BoardDTO boardDTO);

    List<BoardDTO> getList();

    BoardDTO getOne(Integer bno);

    int insertOne(BoardDTO boardDTO);

    int deleteOne(Integer bno);

    int modifyOne(BoardDTO boardDTO);


}
