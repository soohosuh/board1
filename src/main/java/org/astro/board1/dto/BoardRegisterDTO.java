package org.astro.board1.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardRegisterDTO {
  //변수
  private Integer bno;    //pk
  private String title;   //제목
  private String content; //내용
  private String writer;  //작성자

  private List<String> fileNames; //파일업로드 파일명저장
}