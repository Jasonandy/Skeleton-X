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
package cn.ucaner.skeleton.codegen.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.ucaner.skeleton.codegen.entity.GenConfig;
import cn.ucaner.skeleton.codegen.mapper.SysGeneratorMapper;
import cn.ucaner.skeleton.codegen.service.SysGeneratorService;
import cn.ucaner.skeleton.codegen.util.GenUtils;
import cn.ucaner.skeleton.codegen.util.Query;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;


/**
 * @ClassName：SysGeneratorServiceImpl
 * @Description： <p> 代码生成service </p>
 * @Author： - Jason
 * @CreatTime：2019/6/14 - 14:15
 * @Modify By：
 * @ModifyTime： 2019/6/14
 * @Modify marker：
 * @version V1.0
*/
@Service("sysGeneratorService")
@AllArgsConstructor
public class SysGeneratorServiceImpl implements SysGeneratorService {

    @Resource
    private  SysGeneratorMapper sysGeneratorMapper;

    /**
     * 分页查询表
     * @param query 查询条件
     * @return
     */
    @Override
    public List<Map<String, Object>> queryPage(Query query) {
        Object tableName = query.condition().get("tableName");
        return sysGeneratorMapper.queryList(query, tableName);
    }

    /**
     * 生成代码
     * @param genConfig 生成配置
     * @return
     */
    @Override
    public byte[] generatorCode(GenConfig genConfig) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        String tableName = genConfig.getTableName();
        if(StringUtils.isEmpty(tableName)){
            /**
             * 查询出所有的表名
             */
            List<Map<String,Object>> generatorList = sysGeneratorMapper.queryList(null,null);
            if(generatorList != null && generatorList.size() > 0){
                for(Map<String,Object> map:generatorList){
                    tableName = map.get("tableName").toString();
                    /**
                     * 查询表信息
                     */
                    Map<String, String> table = queryTable(tableName);
                    /**
                     * 查询列信息
                     */
                    List<Map<String, String>> columns = queryColumns(tableName);
                    /**
                     * 生成代码
                     */
                    genConfig.setTableName(tableName);
                    GenUtils.generatorCode(genConfig, table, columns, zip);
                }
                IoUtil.close(zip);
                return outputStream.toByteArray();
            }

        }
        /**
         * 查询表信息
         */
        Map<String, String> table = queryTable(genConfig.getTableName());
        /**
         * 查询列信息
         */
        List<Map<String, String>> columns = queryColumns(genConfig.getTableName());

        /**
         * 生成代码
         */
        GenUtils.generatorCode(genConfig, table, columns, zip);
        IoUtil.close(zip);
        return outputStream.toByteArray();
    }

    /**
     * 查询表信息
     * @param tableName
     * @return
     */
    private Map<String, String> queryTable(String tableName) {
        return sysGeneratorMapper.queryTable(tableName);
    }

    /**
     * 查询列信息
     * @param tableName
     * @return
     */
    private List<Map<String, String>> queryColumns(String tableName) {
        return sysGeneratorMapper.queryColumns(tableName);
    }
}
