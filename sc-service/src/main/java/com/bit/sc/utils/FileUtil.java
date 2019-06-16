package com.bit.sc.utils;

import com.bit.sc.common.Const;
import com.bit.utils.UUIDUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: liyujun
 * @Date: 2018-11-05
 **/
public class FileUtil {

    public static final  String rootPath=YmlUtil.getValue("file.localtion");

   /**
    * @description:
    * @author liyujun
    * @date 2018-11-06
    * @param file :
    * @param fileName :
    * @return : java.lang.String
    */
    public static String uploadFile(byte[] file, String fileName) throws IOException {

        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        //String filenameStr = fileName.substring(0, fileName.lastIndexOf("."));// 去掉后缀的文件名
        String fileNewName= UUIDUtil.getUUID()+"."+suffix;
        String filePaths=rootPath+fileNewName;
        File filePath = new File(filePaths);
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            out.write(file);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
       return filePaths;
    }
    
    /**
     * @description:
     * @author liyujun
     * @date 2018-11-06
     * @param file : 
     * @return : java.lang.String
     */
    public static String uploadFile(MultipartFile file) throws IOException {
        String name=file.getOriginalFilename();
        String suffix = name.substring(name.lastIndexOf(".") + 1);//得到文件后缀名
        String fileNewName= UUIDUtil.getUUID()+"."+suffix;
        String filePaths=rootPath+fileNewName;
        File parentFile = new File(filePaths);
        if(!parentFile.getParentFile().exists()){
            parentFile.getParentFile().mkdirs();
        }
        if(!parentFile.exists()){
            file.transferTo(parentFile);
        }

        return Const.UPLOAD_PATH+fileNewName;
    }

    /**
     * 获得WEB-INF/classes根目录
     * @return
     */
    public static String getClassesPath(){
        URL url = Thread.currentThread().getContextClassLoader().getResource("/");
        if(url==null) {
            url = Thread.currentThread().getContextClassLoader().getResource("");
        }
        String rootPath = url.getPath().substring(1);
        try {
            rootPath = java.net.URLDecoder.decode(rootPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rootPath;
    }

    public static String getRootPath() {
        String classPath = FileUtil.class.getClassLoader().getResource("/").getPath();
        String rootPath  = "";
        //windows下
        if("\\".equals(File.separator)){
            rootPath  = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"));
            rootPath = rootPath.replace("/", "\\");
        }
        //linux下
        if("/".equals(File.separator)){
            rootPath  = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));
            rootPath = rootPath.replace("\\", "/");
        }
        rootPath =rootPath +File.separator;

        try {
            rootPath = java.net.URLDecoder.decode(rootPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return rootPath;
    }

    public static String getWebRealPath(String contextPath) {
        String classPath = FileUtil.class.getClassLoader().getResource("/").getPath();
        String rootPath  = "";
        //windows下
        if("\\".equals(File.separator)){
            rootPath  = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"))+contextPath;
            rootPath = rootPath.replace("/", "\\");
        }
        //linux下
        if("/".equals(File.separator)){
            rootPath  = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"))+contextPath;
            rootPath = rootPath.replace("\\", "/");
        }
        try {
            rootPath = java.net.URLDecoder.decode(rootPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rootPath;
    }

    public static String getClassPath() {
        String classPath = FileUtil.class.getClassLoader().getResource("/").getPath();
        String rootPath  = "";
        //windows下
        if("\\".equals(File.separator)){
            rootPath  = classPath.substring(1);
            rootPath = rootPath.replace("/", "\\");
        }
        //linux下
        if("/".equals(File.separator)){
            rootPath  = classPath;
            rootPath = rootPath.replace("\\", "/");
        }

        try {
            rootPath = java.net.URLDecoder.decode(rootPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return rootPath;
    }


    /**
     * 生成下载文件
     * @param file_name
     * @param is
     * @return
     */
    public static File createFileFromInputStream(String file_name, InputStream is) {
        File file = new File(file_name);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            byte buffer[] = new byte[4 * 1024];
            while ((is.read(buffer)) != -1) {
                os.write(buffer);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * Java文件操作 获取文件扩展名
     * @author liuyancheng
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * Java文件操作 获取不带扩展名的文件名
     * @author liuyancheng
     * @param filename
     * @return
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 根据文件扩展名判断文件类型（0-图片   1-文件）
     * @author liuyancheng
     * @param extensionName
     * @return
     */
    public static int equalsExtensionName(String extensionName){
        Integer i = null;
        //读取配置文件中的类型
        ConcurrentHashMap<String,String> yml = YmlUtil.getYmlByFileName("bootstrap.yml");
        String pic = yml.get("file.pic");
        String file = yml.get("file.file");
        //按照，拆分
        String[] pics = pic.split(",");
        String[] files = file.split(",");
        //循环判断当前传入的文件扩展名（0-图片   1-文件）
        for (String s : pics) {
            if (s.equals(extensionName)){
                i = 0;
                break;
            }
        }
        if (i == null){
            for (String s : files) {
                if (s.equals(extensionName)){
                    i = 1;
                    break;
                }
            }
        }
        return i;
    }
}
