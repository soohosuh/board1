package org.astro.board1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor 
@NoArgsConstructor
public class BoardDTO {

    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private String duedate;
    private LocalDate modifyDate;


}
