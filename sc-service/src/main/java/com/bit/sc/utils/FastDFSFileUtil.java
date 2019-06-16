package com.bit.sc.utils;

import com.bit.sc.common.fastdfs.FastDFSClient;
import com.bit.sc.common.fastdfs.FastDFSFile;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * fastdfs 工具类
 * @author mifei
 */
public class FastDFSFileUtil {

    private static Logger logger = LoggerFactory.getLogger(FastDFSFileUtil.class);

    /**
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public static String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath = {};
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        if (inputStream != null) {
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);  //upload to fastdfs
        } catch (Exception e) {
            logger.error("upload file Exception!", e);
            throw new RuntimeException("upload file Exception!");
        }
        if (fileAbsolutePath == null) {
            logger.error("upload file failed,please upload again!");
            throw new RuntimeException("upload file failed,please upload again!");
        }
        String path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
        return path;
    }

    /**
     * fastdfs删除
     * @param path
     * @return
     */
    public static void deleteFile(String path){
        //组名
        String[] groupName = StringUtils.splitByWholeSeparator(path,"/");
        //剩余path地址
        String[] surplusPath = StringUtils.splitByWholeSeparator(path,groupName[2] + "/");
        try {
            FastDFSClient.deleteFile(groupName[2],surplusPath[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
