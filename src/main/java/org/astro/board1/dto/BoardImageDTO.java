package org.astro.board1.dto;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@lombok.ToString
public class BoardImageDTO {

    private Long image_bno;

    //DB 처리 용도
    private String image;

    private int ord;
    
}
