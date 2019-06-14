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
package cn.ucaner.skeleton.codegen.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * @ClassName：TableEntity
 * @Description： <p> TableEntity - 表属性 </p>
 * @Author： - Jason
 * @CreatTime：2019/6/14 - 14:14
 * @Modify By：
 * @ModifyTime： 2019/6/14
 * @Modify marker：
 * @version V1.0
*/
@Data
public class TableEntity implements Serializable {

    /**
     * 名称
     */
    private String tableName;

    /**
     * 备注
     */
    private String comments;

    /**
     * 主键
     */
    private ColumnEntity pk;

    /**
     * 列名
     */
    private List<ColumnEntity> columns;

    /**
     * 驼峰类型
     */
    private String caseClassName;

    /**
     * 普通类型
     */
    private String lowerClassName;
}

