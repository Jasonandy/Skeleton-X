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
package cn.ucaner.skeleton.service.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.ucaner.skeleton.service.user.entity.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @projectName：Skeleton-X
 * @Description： <p> EasyPoiController </p>
 * @Author： - Jason
 * @CreatTime：2019/3/26 - 9:43
 * @Modify By：
 * @ModifyTime： 2019/3/26
 * @Modify marker：
 */
@Controller
@RequestMapping("/excel")
public class EasyPoiController {


    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        List<User> usersList = new ArrayList<>();
        User user1  = new User("123","Jason",12);
        User user2  = new User("123","Jason",12);
        User user3  = new User("123","Jason",12);

        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);

        for (User user : usersList) {
            System.out.println(user);
        }

        /**
         * TemplateExportParams
         */
        TemplateExportParams params = new TemplateExportParams("/templates/商品详情表.xls", true);

        Map<String, Object> data = new HashMap<>();

        //导出一般都要日期
        data.put("date", new Date());

        //导出一个对象
        data.put("one", user1);

        //导出list集合
        data.put("list", usersList);

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("Skeleton项目学生","101班"), User.class, usersList);

        try {
            //Workbook book = ExcelExportUtil.exportExcel(params, data);
            export(response, workbook, "Skeleton下载Excel测试");
            //export(response, book, "Skeleton下载Excel测试");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * export导出请求头设置
     * @param response
     * @param workbook
     * @param fileName
     * @throws Exception
     */
    private static void export(HttpServletResponse response, Workbook workbook, String fileName) throws Exception {
        response.reset();
        response.setContentType("application/x-msdownload");
        fileName = fileName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO-8859-1") + ".xls");
        ServletOutputStream outStream = null;
        try {
            outStream = response.getOutputStream();
            workbook.write(outStream);
        } finally {
            outStream.close();
        }
    }


}
