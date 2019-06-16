package com.bit.sc.module.restTemplate.dto;

import com.bit.sc.module.restTemplate.vo.ResultDTO;
import lombok.Data;

import java.io.File;

/**
 * @author liuyancheng
 * @create 2018-12-14 9:05
 */
@Data
public class FileDTO extends ResultDTO {

    private File updateFile;

    private String fileId;

    private String filePath;
}
