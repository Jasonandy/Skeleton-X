/******************************************************************************
 * ~ Copyright (c) 2018 [jasonandy@hotmail.com | https://github.com/Jasonandy] *
 * ~                                                                           *
 * ~ Licensed under the Apache License, Version 2.0 (the "License”);           *
 * ~ you may not use this file except in compliance with the License.          *
 * ~ You may obtain a copy of the License at                                   *
 * ~                                                                           *
 * ~    http://www.apache.org/licenses/LICENSE-2.0                             *
 * ~                                                                           *
 * ~ Unless required by applicable law or agreed to in writing, software       *
 * ~ distributed under the License is distributed on an "AS IS" BASIS,         *
 * ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
 * ~ See the License for the specific language governing permissions and       *
 * ~ limitations under the License.                                            *
 ******************************************************************************/
package cn.ucaner.skeleton.service.framework.common.utils.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.ucaner.skeleton.common.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @projectName：Skeleton-X
 * @Description： <p> EasyPoiExcelUtils 基于easypoi </p>
 * @Author： - Jason
 * @CreatTime：2019/3/26 - 9:43
 * @Modify By：
 * @ModifyTime： 2019/3/26
 * @Modify marker：
 */
public class EasyPoiExcelUtils {


    /**
     * exportExcel 导出excel
     * @param list 需要导出的list数据
     * @param title title 标题
     * @param sheetName sheet名称
     * @param pojoClass 对应的clazz
     * @param fileName 文件名称
     * @param isCreateHeader 是否创建头部
     * @param response 响应
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response){
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);
    }

    /**
     *exportExcel 导出excel
     * @param list 原始数据
     * @param title 标题title
     * @param sheetName sheet名称
     * @param pojoClass 对应的实体类
     * @param fileName 文件名
     * @param response  响应
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName,HttpServletResponse response){
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    /**
     * 导出excel
     * @param list  原始数据
     * @param fileName 文件名称
     * @param response 响应
     */
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response){
        defaultExport(list, fileName, response);
    }

    /**
     * 默认的文件导出
     * @param list 原始的数据
     * @param pojoClass 对象类
     * @param fileName 文件名
     * @param response  响应
     * @param exportParams  导出参数
     */
    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName,HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,pojoClass,list);
        if (workbook != null) {
            return ;
        }
        downLoadExcel(fileName, response, workbook);
    }

    /**
     * downLoadExcel
     * @param fileName
     * @param response
     * @param workbook
     */
    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * defaultExport
     * @param list
     * @param fileName
     * @param response
     */
    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null);
        downLoadExcel(fileName, response, workbook);
    }


    /**
     * importExcel 导入excel
     * @param filePath
     * @param titleRows
     * @param headerRows
     * @param pojoClass
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass){
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list ;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new SystemException("模板不能为空!");
        } catch (Exception e) {
            throw new SystemException("导入出现异常!");
        } return list;
    }


    /**
     * 导入
     * @param file
     * @param titleRows
     * @param headerRows
     * @param pojoClass
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (file == null){ return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new SystemException("excel文件不能为空!");
        } catch (Exception e) {
            throw new SystemException("导入出现异常!",e);
        }
        return list;
    }

}
