package org.astro.board1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.astro.board1.dto.BoardDTO;
import org.astro.board1.dto.BoardImageDTO;
import org.astro.board1.dto.PageRequestDTO;
import org.astro.board1.dto.PageResponseDTO;
import org.astro.board1.dto.UploadResultDTO;
import org.astro.board1.mappers.BoardMapper;
import org.astro.board1.mappers.FileMapper;
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
        Long bno = boardMapper.insertOne(boardDTO); 
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
