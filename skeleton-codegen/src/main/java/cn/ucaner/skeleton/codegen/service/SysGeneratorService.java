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
package cn.ucaner.skeleton.codegen.service;

import cn.ucaner.skeleton.codegen.entity.GenConfig;
import cn.ucaner.skeleton.codegen.util.Query;

import java.util.List;
import java.util.Map;

/**
 * @ClassName：SysGeneratorService
 * @Description： <p> 代码生成service </p>
 * @Author： - Jason
 * @CreatTime：2019/6/14 - 14:15
 * @Modify By：
 * @ModifyTime： 2019/6/14
 * @Modify marker：
 * @version V1.0
*/
public interface SysGeneratorService {


    /**
     * 生成代码
     * @param tableNames 表名称
     * @return
     */
    byte[] generatorCode(GenConfig tableNames);

    /**
     * 分页查询表
     * @param query 查询条件
     * @return
     */
    List<Map<String,Object>> queryPage(Query query);
}
