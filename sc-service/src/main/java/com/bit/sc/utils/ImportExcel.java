package com.bit.sc.utils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit.base.vo.BaseVo;

/**
 * 导出工具类
 * liqi
 * @description 读取并解析excel
 */
public class ImportExcel {

    public static List<Map<String, Object>> importExcel(InputStream input, String[] keys) throws Exception {
        Workbook wb = WorkbookFactory.create(input);
        Sheet sheet = wb.getSheetAt(0);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for(int rownum = 1; rownum <= sheet.getLastRowNum(); rownum++) {
            Row row = sheet.getRow(rownum);
            if(row == null) {
                continue;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            for(int cellnum = 0; cellnum<row.getLastCellNum(); cellnum++){
                Cell cell = row.getCell(cellnum);
                if(cell==null)
                    continue;
                int valType = cell.getCellType();
                if(valType == Cell.CELL_TYPE_STRING) {
                    map.put(keys[cellnum], cell.getStringCellValue());
                } else if(valType == Cell.CELL_TYPE_BOOLEAN) {
                    map.put(keys[cellnum], cell.getBooleanCellValue());
                }else if(valType == Cell.CELL_TYPE_BLANK) {
                    map.put(keys[cellnum], cell.getStringCellValue());
                } else if(valType == Cell.CELL_TYPE_NUMERIC) {
                    if(HSSFDateUtil.isCellDateFormatted(cell)){
                        //用于转化为日期格式
                        Date d = cell.getDateCellValue();
                        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                        map.put(keys[cellnum], formater.format(d));
                    } else {
                        map.put(keys[cellnum], numToStringFormat(String.valueOf(cell.getNumericCellValue())));
                    }
                } 
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 判断这个数字是否是科学计数法 如果是 则转换成普通模式
     * @param num
     * @return
     */
    private static String numToStringFormat(String num){
        if(num.contains("E10"))
        {
            BigDecimal bd = new BigDecimal(num);
            String str = bd.toPlainString();
            return str;
        }else if(num.contains(".0")){
            return num.substring(0,num.length()-2);
        }else
            return num;
    }
    //测试导入  postman
    public BaseVo upload(@RequestParam(value = "file") MultipartFile multipartFile) throws Exception {
        String[] keys={"groupId", "relId", "type"};
        //map  是自己上面定义的key    value是在excel 里面的值  对应的
        //只导入sheet  1页
        List<Map<String, Object>> maps = importExcel(multipartFile.getInputStream(), keys);
        BaseVo objectBaseVo = new BaseVo();
        objectBaseVo.setData(maps);
        return objectBaseVo;
    }
}