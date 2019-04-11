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
package cn.ucaner.skeleton.service.user.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import cn.ucaner.skeleton.common.base.entity.BaseEntity;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @ClassName：User
 * @Description： <p> User  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/19 - 12:08
 * @Modify By：
 * @ModifyTime： 2019/3/19
 * @Modify marker：
 * @version V1.0
*/
public class User extends BaseEntity implements IExcelModel,Serializable  {

    /**
     * 自定义errorMsg接收IExcelModel.setErrorMsg传过来的errorMsg
     */
    private String errorMsg;

    @Excel(name = "id",needMerge = true)
    private String id;

    @Excel(name = "姓名" ,orderNum = "1")
    @Pattern(regexp = "[\u4E00-\u9FA5]*", message = "不是中文")
    private String name;

    @Excel(name = "年龄" ,orderNum = "2")
    private Integer age;

    @Excel(name = "描述" ,orderNum = "3")
    private String desc;

    public User() {}

    public User(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User(String id, String name, Integer age, String desc) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.desc = desc;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }


    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public void setErrorMsg(String s) {
        this.errorMsg = s;
    }
}