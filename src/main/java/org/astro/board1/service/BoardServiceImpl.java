package org.astro.board1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

import org.astro.board1.dto.BoardDTO;
import org.astro.board1.dto.BoardListDTO;
import org.astro.board1.dto.FileUploadDTO;
import org.astro.board1.dto.PageRequestDTO;
import org.astro.board1.dto.PageResponseDTO;
import org.astro.board1.mappers.BoardMapper;
import org.astro.board1.mappers.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    //mapper 의존성 주입
    private final BoardMapper boardMapper;
    private final FileMapper fileMapper;

    //list
    @Override
    public PageResponseDTO<BoardListDTO> getList(PageRequestDTO pageRequestDTO) {

        //list
        List<BoardListDTO> list = boardMapper.getList(pageRequestDTO);
        //total
        long total = boardMapper.listCount(pageRequestDTO);


        return PageResponseDTO.<BoardListDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }
    //read
    @Override
    public BoardDTO getOne(Integer bno) {

        return boardMapper.getOne(bno);
    }

    //register
    @Override
    public void insertOne(BoardDTO boardDTO) {
        
        //게시판 등록
        int count = boardMapper.insertOne(boardDTO);
        log.info("insert product count: " + count);

        //파일 이름 List로 가져오기
        List<String> fileNames = boardDTO.getFileNames();
        
        //게시판 등록 성공과 파일이 등록되었다면 실행
        if(count > 0 && boardDTO.getFileNames() != null && !boardDTO.getFileNames().isEmpty()){
            //bno 가져오기
            Integer bno = boardDTO.getBno();
            log.info("-----------------bno: "+ bno);

            AtomicInteger index = new AtomicInteger();

            //등록된 파일 fileNames에서 추출
            List<Map<String, String>> list = fileNames.stream().map(str -> {
                //uuid 가져오기
                String uuid = str.substring(0, 36);
                //실제 파일명 가져오기
                String fileName = str.substring(37);

                //return map에 담기
                return Map.of("uuid", uuid, "file_name", fileName, "bno", "" + bno, "ord", "" + index.getAndIncrement());
                }).collect(Collectors.toList());

            log.info("----------------------------------------");
            log.info("----------------------------------------");
            log.info(list);

            //파일 등록 실행
            fileMapper.registerImage(list);
        }
    }



    //delete
    @Override
    public void deleteOne(Integer bno) {
        //삭제 업데이트
        boardMapper.deleteOne(bno);
        //파일도 함께 삭제
        fileMapper.deleteImage(bno);
    }
    //modify
    @Override
    public void modifyOne(BoardDTO boardDTO) {
        //수정 업데이트
        int count = boardMapper.modifyOne(boardDTO);
        log.info("modify product count: " + count);

        //기존파일 삭제
        fileMapper.deleteImage(boardDTO.getBno());

        //파일이름 List로 가져오기
        List<String> fileNames = boardDTO.getFileNames();
        log.info(fileNames);

        //게시판 등록 성공과 파일이 등록되었다면 실행
        if (count > 0) {
        //bno 가져오기
        Integer bno = boardDTO.getBno();
        log.info("--------------------------------- bno: " + bno);

        AtomicInteger index = new AtomicInteger();

        //등록된 파일 fileNames에서 추출
        List<Map<String, String>> list = fileNames.stream().map(str -> {
            //uuid 가져오기
            String uuid = str.substring(0, 36);
            //실제 파일명 가져오기
            String fileName = str.substring(37);

            //return map에 담기
            return Map.of("uuid", uuid, "file_name", fileName, "bno", "" + bno, "ord", "" + index.getAndIncrement());
        }).collect(Collectors.toList());

        log.info("=====================================================================");
        log.info("=====================================================================");
        log.info(list);

        //파일 등록 실행
        fileMapper.registerImage(list);
        }

        
    }

    @Override
    public void viewCount(Integer bno) {
        boardMapper.viewCount(bno);
    }

        
}

