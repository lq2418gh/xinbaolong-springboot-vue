package com.bit.sc.utils;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.group.pojo.GroupRel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 导入工具类
 * liqi
 */
public class ExportExcel {
    /**
     * 使用poi通过反射导出Excel(通用方法)
     *
     * @param data 需要导出的数据
     * @param saveFilePath 导出文件所在路径
     * @return 成功返回true 失败返回false
     * @throws Exception
     */
    public static <T> boolean exportExcel(List<T> data,
                                            String saveFilePath) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("0"); // 获取到工作表
        HSSFRow row = sheet.createRow(0); // 获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
        // System.out.println(sheet.getLastRowNum() + " " +
        // row.getLastCellNum()); // 分别得到最后一行的行号，和一条记录的最后一个单元格
        FileOutputStream out = new FileOutputStream(saveFilePath); // 向d://test.xls中写数据
        // 遍历集合数据，产生数据行
        Iterator<T> it = data.iterator();
        int index = 0;
        boolean flag = true;
        try {
            while (it.hasNext()) {
                row = sheet.createRow(index++);//若不是在已有Excel表格后面追加数据 则使用该条语句
                // 创建单元格，并设置值
                T t = (T) it.next();
                // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
                Field[] fields = t.getClass().getDeclaredFields();
                for (short i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    if (field.toString().contains("static")) {
                        continue;
                    }
                    HSSFCell cell = row.createCell((short) i);
                    String fieldName = field.getName();
                    String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName,
                            new Class[] {});
                    Object value = getMethod.invoke(t, new Object[] {});
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(
                                "yyyy-mm-dd");
                        textValue = sdf.format(date);
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        if (value == null) {
                            value = "";
                        }
                        textValue = value.toString();
                    }
                    if (textValue != null) {
                        Pattern p = Pattern.compile("^//d+(//.//d+)?//{1}quot;");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            cell.setCellValue(textValue);
                        }
                    }
                }
            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            out.flush();
            wb.write(out);
            out.close();
        }
        System.out.println("导出完毕");
        return flag;
    }

    /**
     *测试 导出
     */
    @GetMapping("/downfile")
    public BaseVo downfile()throws Exception {
        String[] keys={"id","groupId", "relId", "type"};
        //这是查询出来的list
        List<GroupRel> all = new ArrayList<>();
        boolean b = ExportExcel.exportExcel(all, "d://test.xls");
        BaseVo baseVo = new BaseVo();
        if (b){
            baseVo.setCode(0);
            baseVo.setMsg("导出成功");
        }else {
            baseVo.setCode(1);
            baseVo.setMsg("导出失败");
        }
        return baseVo;
    }
}
