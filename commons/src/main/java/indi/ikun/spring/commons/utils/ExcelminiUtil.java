package indi.ikun.spring.commons.utils;


//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;


/**
 * @author renqiankun
 * @Description 直接读取文件到内存中操作，excel数据量<=10000行x10列个
 * @Date  2018/4/19
 * @Modified By
 */
public class ExcelminiUtil {

    /**
     * @Author：renqiankun
     * @Description: 传入路径获得excel内的数据
     * @Date: 17:33 2018/4/19
     */
//    public static List<Map<String,Object>> getMapData(String path){
//        List<Map<String,Object>> excelData=new ArrayList<>();
//        Map<String,Object> mapData;
//        //        获取excel文件数据
//        File file=new File(path);
//        //   输入流，将文件内容输入cpu
//        InputStream is = null;
//        try {
//            is = new FileInputStream(file);
//            XSSFWorkbook xssfWorkbook = null;
//            //下面这个操作如果数据太大会 Out of memeory   POI事件模式可以解决
//            xssfWorkbook = new XSSFWorkbook(is);
//            //关闭文件流
//            is.close();
//            // 获取excel表的每一个工作薄sheet
//            for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
//                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
//                if (xssfSheet == null) {
//                    continue;
//                }
//                // 获取当前工作薄的每一行 第0行为表头
//                for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
//                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
//                    if (xssfRow != null) {
//                        mapData = new HashMap<>();
//                        //长度标题行
//                        for (int cellNum = 0; cellNum < xssfSheet.getRow(0).getLastCellNum(); cellNum++) {
//                            XSSFCell xssfCell = xssfRow.getCell(cellNum);
//                            //表头作为map的key
//                            String key = getValue(xssfSheet.getRow(0).getCell(cellNum));
//                            if (xssfCell != null) {
//                                xssfCell.setCellType(Cell.CELL_TYPE_STRING);
//                                mapData.put(key, getValue(xssfCell));
//                            } else {
//                                mapData.put(key, "");
//                            }
//                        }
//                        //去除重复的行
//                        if (!excelData.contains(mapData)) {
//                            excelData.add(mapData);
//                        }
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if(is!=null){
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return excelData;
//        }
//    }
//    //转换数据格式，将单元格的数据转为String
//    private static String getValue(XSSFCell xssfRow) {
//
//        if (xssfRow.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
//            return String.valueOf(xssfRow.getBooleanCellValue());
//        } else if (xssfRow.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//            return String.valueOf(xssfRow.getNumericCellValue());
//        } else {
//            return String.valueOf(xssfRow.getStringCellValue());
//        }
//    }
}
