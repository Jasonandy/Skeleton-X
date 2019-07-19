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

import cn.ucaner.skeleton.webapp.security.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName：SysUserMapper
 * @Description： <p> SysUserMapper  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 12:20
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 * @version V1.0
*/
@Mapper
public interface SysUserMapper {

    /**
     * deleteByPrimaryKey
     * @param userId
     * @return
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * insert
     * @param record
     * @return
     */
    int insert(SysUser record);

    /**
     * insertSelective
     * @param record
     * @return
     */
    int insertSelective(SysUser record);

    /**
     * selectByPrimaryKey
     * @param userId
     * @return
     */
    SysUser selectByPrimaryKey(Long userId);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return
     */
    int updateByPrimaryKey(SysUser record);
}