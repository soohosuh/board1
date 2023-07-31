package org.astro.board1.mappers;

import java.util.List;
import java.util.Map;

public interface FileMapper {

    //file register
    int registerImage(List<Map<String, String>> imageList);

    int deleteImage(Integer bno);

    int updateImage(List<Map<String, String>> imageList);
    
}
