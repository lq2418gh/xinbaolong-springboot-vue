package com.bit.sc.utils;

import cn.jiguang.common.utils.StringUtils;
import com.bit.sc.common.Const;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * yml文件工具类
 */
public class YmlUtil {

    private static ConcurrentHashMap<String,String> result = new ConcurrentHashMap<>();

    /**
     * 根据文件名获取yml的文件内容
     * @return
     */
    public static ConcurrentHashMap<String,String> getYmlByFileName(String file){
        if(file == null){
            file = Const.BOOTSTRAP_FILE;
        }
        InputStream in = null;
        try {
            in = Class.forName(YmlUtil.class.getName()).getResourceAsStream("/"+file);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 获取到package下的文件
        Yaml props = new Yaml();
        Object obj = props.loadAs(in,Map.class);
        Map<String,Object> param = (Map<String, Object>) obj;

        for(Map.Entry<String,Object> entry:param.entrySet()){
            String key = entry.getKey();
            Object val = entry.getValue();

            if(val instanceof Map){
                forEachYaml(key,(Map<String, Object>) val);
            }else{
                result.put(key,val.toString());
            }
        }
        return result;
    }

    /**
     * 根据key获取值
     * @param key
     * @return
     */
    public static String getValue(String key){
        ConcurrentHashMap<String,String> map = getYmlByFileName(null);
        if(map==null){
            return null;
        }
        return map.get(key);
    }

    /**
     * 遍历yml文件，获取map集合
     * @param key_str
     * @param obj
     * @return
     */
    public static ConcurrentHashMap<String,String> forEachYaml(String key_str,Map<String, Object> obj){
        for(Map.Entry<String,Object> entry:obj.entrySet()){
            String key = entry.getKey();
            Object val = entry.getValue();

            String str_new = "";
            if(StringUtils.isNotEmpty(key_str)){
                str_new = key_str+ "."+key;
            }else{
                str_new = key;
            }
            if(val instanceof Map){
                forEachYaml(str_new,(Map<String, Object>) val);
            }else{
                result.put(str_new,val.toString());
            }
        }
        return result;
    }

}
