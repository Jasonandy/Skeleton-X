/*******************************************************************************
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
package cn.ucaner.skeleton.codegen.controller;

import cn.hutool.core.io.IoUtil;
import cn.ucaner.skeleton.codegen.entity.GenConfig;
import cn.ucaner.skeleton.codegen.service.SysGeneratorService;
import cn.ucaner.skeleton.codegen.util.Query;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName：SysGeneratorController
 * @Description： <p> 代码生成器 </p>
 * @Author： - Jason
 * @CreatTime：2019/6/14 - 14:12
 * @Modify By：
 * @ModifyTime： 2019/6/14
 * @Modify marker：
 * @version V1.0
*/
@RestController
@AllArgsConstructor
@RequestMapping("/generator")
public class SysGeneratorController {

    @Resource
    private SysGeneratorService sysGeneratorService;

    /**
     * 列表
     * @param params 参数集
     * @return  数据库表
     */
    @GetMapping("/page")
    public Page list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        query.setRecords(sysGeneratorService.queryPage(query));
        return query;
    }


    /**
     * 生成代码
     * @param genConfig
     * @param response
     * @throws IOException
     */
    @PostMapping("/code")
    @ApiOperation(value = "代码自动生成")
    public void code(@RequestBody GenConfig genConfig, HttpServletResponse response) throws IOException {
        byte[] data = sysGeneratorService.generatorCode(genConfig);
        response.reset();
        response.setHeader("Content-Disposition", String.format("attachment; filename=%s.zip", genConfig.getTableName()));
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
    }
}
