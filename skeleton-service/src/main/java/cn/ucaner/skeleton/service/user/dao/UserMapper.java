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
package cn.ucaner.skeleton.service.user.dao;

import cn.ucaner.skeleton.common.base.dao.BaseDao;
import cn.ucaner.skeleton.service.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName：UserMapper
 * @Description： <p> UserMapper  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/19 - 11:40
 * @Modify By：
 * @ModifyTime： 2019/3/19
 * @Modify marker：
 * @version V1.0
*/
@Mapper
public interface UserMapper extends BaseDao<User, String> {

    /**
     * 获取用户列表
     * @return
     */
    List<User> getAllUserList();

}