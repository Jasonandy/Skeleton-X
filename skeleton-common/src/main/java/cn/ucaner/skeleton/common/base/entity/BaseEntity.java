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
package cn.ucaner.skeleton.common.base.entity;

import java.io.Serializable;

/**
 * @ClassName：BaseEntity
 * @Description： <p> BaseEntity  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/18 - 16:38
 * @Modify By：
 * @ModifyTime： 2019/3/18
 * @Modify marker：
 * @version V1.0
*/
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 2368417877456900821L;

    /**
     * 主键ID
     */
    protected String id;

    public BaseEntity() {
        super();
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
}
