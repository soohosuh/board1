package org.astro.board1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

import org.astro.board1.dto.BoardDTO;
import org.astro.board1.dto.BoardImageDTO;
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
    private final ServletContext servletContext;

    @Value("${org.astro.upload.path}")
    private String path;

    //예외처리
    public static class UploadException extends RuntimeException{
    
        public UploadException(String msg){
            super(msg);
        }
    }

    //list
    @Override
    public PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {

        //list
        List<BoardDTO> list = boardMapper.getList(pageRequestDTO);
        //total
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

    //register
    @Override
    public int insertOne(BoardDTO boardDTO, boolean makeThumbnail) {
        
        List<MultipartFile> files = boardDTO.getFiles();

        //파일을 저장하고 이름만 추출
        if(files == null || files.size() == 0){
            throw new UploadException("No File");
        }
        List<String> uploadFileNames = new ArrayList<>();

        //loop
        for(MultipartFile mFile : files){

            String originalFileName = mFile.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();

            String mimeType = servletContext.getMimeType(originalFileName);
            log.info("mimeType: " + mimeType);

            //save할 파일 이름
            String saveFileName = uuid+"_"+originalFileName;
            File saveFile = new File(path, saveFileName);

            // 예외처리
            try ( InputStream in = mFile.getInputStream();
                  OutputStream out = new FileOutputStream(saveFile);
            ) {

                // 파일 Copy
                FileCopyUtils.copy(in, out);

                // 이미지 파일일 경우
                if(makeThumbnail && mimeType.contains("image")) {
                    // 섬네일 생성
                    File thumOutFile = new File(path, "s_" + saveFileName);
                    Thumbnailator.createThumbnail(saveFile, thumOutFile, 200, 200);
                }
                uploadFileNames.add(saveFileName);
            } catch (Exception e) {
                throw new UploadException("Upload Fail: " + e.getMessage());
            }
        }
        //이름을 디비에 저장
        log.info("여기 잘들어왔어 222222");
        BoardImageDTO boardImageDTO = new BoardImageDTO();
        Integer bno = boardMapper.insertOne(boardDTO); 
        boardImageDTO.setImage_bno(boardDTO.getBno());
        log.info("=======================================");
        
        int ord = 0;
        for(String uploadFileName : uploadFileNames ){
            boardImageDTO.setImage(uploadFileName);
            boardImageDTO.setOrd(ord++);
            boardMapper.setBoardImage(boardImageDTO);
        }
        
        return bno;
    }


    

    @Override
    public int deleteOne(Integer bno) {
        
        return boardMapper.deleteOne(bno);
    }

    @Override
    public int modifyOne(BoardDTO boardDTO) {

        //수정 업데이트
        int count = boardMapper.modifyOne(boardDTO);
        log.info("modify product count: " + count);

        //기존파일 삭제
        fileMapper.deleteImage(boardDTO.getBno());

        //파일이름 List로 가져오기
        List<String> fileNames = boardDTO.getFileNames();
        log.info(fileNames);

        
}

}