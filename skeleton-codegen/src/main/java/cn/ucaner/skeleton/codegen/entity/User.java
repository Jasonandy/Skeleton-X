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

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.codegen.entity
 * @Description： <p> User example 示列 </p>
 * @Author： - Jason
 * @CreatTime：2019/6/25 - 9:53
 * @Modify By：
 * @ModifyTime： 2019/6/25
 * @Modify marker：
 */
@TableName("user")
@Data
public class User implements Serializable {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 描述
     */
    private String desc;

    /**
     * 状态
     */
    @TableField(exist = false)
    private String state;

}
