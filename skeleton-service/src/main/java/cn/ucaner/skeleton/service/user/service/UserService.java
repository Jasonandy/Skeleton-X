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
package cn.ucaner.skeleton.service.user.service;

import cn.ucaner.skeleton.common.base.service.BaseService;
import cn.ucaner.skeleton.service.user.entity.User;

import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.user.service
 * @Description： <p> UserService  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/19 - 11:39
 * @Modify By：
 * @ModifyTime： 2019/3/19
 * @Modify marker：
 */
public interface UserService<User,String> extends BaseService {

    /**
     * 获取用户列表
     * @return 用户列表
     */
    List<User> getAllUserList();
}
