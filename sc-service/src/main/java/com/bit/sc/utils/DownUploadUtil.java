package com.bit.sc.utils;

import com.bit.base.exception.BusinessException;
import com.bit.sc.common.fastdfs.FastDFSClient;
import com.bit.sc.common.fastdfs.FastDFSFile;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 下载和上传图片util
 * @author liuyancheng
 * @create 2018-12-24 15:29
 */
public class DownUploadUtil {

    /**
     * 下载图片并上传到fast返回新的路径
     * @param imgPath
     * @return
     */
    public static String downUploadPic(String imgPath){
        //截取文件名
        int idx = imgPath.lastIndexOf("/");
        String imgName = imgPath.substring(idx + 1, imgPath.length());
        InputStream inputStream = null;
        File file = DownUploadUtil.downloadFromUrl(imgPath);
        try {
            inputStream = new FileInputStream(file);
            String path = DownUploadUtil.uploadFile(inputStream, imgName);
            return path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new BusinessException("文件流关闭失败");
                }
            }
            if (file != null) {
                file.delete();
            }
        }
        return null;
    }

    /**
     * 传文件到fast
     * @param is
     * @param fileName
     * @return
     */
    public static String uploadFile(InputStream is, String fileName){
        String[] fileAbsolutePath = {};
        try {
            //读取流
            //文件扩展名
            String ext = FilenameUtils.getExtension(fileName);
            byte[] file_buff = null;
            if (is != null) {
                int len1 = is.available();
                file_buff = new byte[len1];
                is.read(file_buff);
            }
            FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
            // 上传
            fileAbsolutePath = FastDFSClient.upload(file);
            String path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下载文件并生成临时文件
     * @param urlStr
     * @return
     */
    public static File downloadFromUrl(String urlStr){
        //获取URL对象
        URL url = null;
        HttpURLConnection conn = null;
        File file = null;
        try {
            url = new URL(urlStr);
            //获取连接
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(6000);
            //设置超时时间是3秒
            conn.setReadTimeout(6000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.19 Safari/537.36");
            //得到临时文件
            InputStream is = conn.getInputStream();
            if (is == null || is.available() <= 0) {
                throw new BusinessException("图片为空");
            }
            file = getTemplateFile(is);
        } catch (MalformedURLException e) {
            throw new BusinessException("图片下载失败");
        } catch (Exception e) {
            throw new BusinessException("图片下载失败");
        } finally {
            //关闭连接
            if (conn != null) {
                conn.disconnect();
            }
        }
        return file;
    }

    /**
     * 获取模板文件--获取到的文件为临时文件，用完后需要手动删除
     *
     * @param inputStream
     * @return 模板文件
     * @throws Exception 异常抛出
     */
    public static File getTemplateFile(InputStream inputStream) throws Exception {
        File file = File.createTempFile("temp_image", null);
        inputStreamToFile(inputStream, file);
        if (file.exists() && file.length() <= 0) {
            throw new BusinessException("临时文件为空");
        }
        return file;
    }

    /**
     * InputStream 转file
     *
     * @param ins  输入流
     * @param file 目标文件
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            try {
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.flush();
            } finally {
                if (os != null) {
                    os.close();
                }
                if (ins != null) {
                    ins.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
