package org.astro.board1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;
import java.util.List;


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
    private String modifyDate;   //수정일

    private boolean status;     //상태여부
  
    
    private List<String> fileNames; //파일업로드 파일명저장


}
