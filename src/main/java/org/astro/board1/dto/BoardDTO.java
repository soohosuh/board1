package org.astro.board1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor 
@NoArgsConstructor
@ToString
public class BoardDTO {

    private Integer bno;            //pk
    private String title;           //제목
    private String content;         //내용
    private String writer;          //작성자
    private String duedate;         //등록일
    private LocalDate modifyDate;   //수정일

    private boolean status;     //상태여부
  
    //DB 처리 용도 - 대표이미지 하나만 가지고올거다.
    private String images;

    // 썸네일 작업
    private String picture;

    //등록,수정 업로드된 파일 데이터를 수집하는 용도
    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>(); //업로드 파일

    public String getPicture() {
        if(images!=null){
        picture = images.split("\\.")[0] + "." + "png"; 
            return picture;
        }
        return "";
        

    }
    private List<String> fileNames; //파일업로드 파일명저장


}
