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
package cn.ucaner.skeleton.webapp.security.dao;

import cn.ucaner.skeleton.webapp.security.entity.SysModule;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName：SysModuleMapper
 * @Description： <p> SysModuleMapper  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 13:54
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 * @version V1.0
*/
@Mapper
public interface SysModuleMapper {

    /**
     * deleteByPrimaryKey
     * @param moduleId
     * @return
     */
    int deleteByPrimaryKey(Long moduleId);

    /**
     * insert
     * @param record
     * @return
     */
    int insert(SysModule record);

    /**
     * insertSelective
     * @param record
     * @return
     */
    int insertSelective(SysModule record);

    /**
     * selectByPrimaryKey
     * @param moduleId
     * @return
     */
    SysModule selectByPrimaryKey(Long moduleId);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysModule record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return
     */
    int updateByPrimaryKey(SysModule record);
}