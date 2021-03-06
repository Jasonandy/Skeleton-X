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
package cn.ucaner.skeleton.service.user.dao.impl;

import cn.ucaner.skeleton.common.base.dao.impl.BaseDaoImpl;
import cn.ucaner.skeleton.common.exception.SystemException;
import cn.ucaner.skeleton.service.user.dao.UserMapper;
import cn.ucaner.skeleton.service.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.user.dao.impl
 * @Description： <p> TODO </p>
 * @Author： - Jason
 * @CreatTime：2019/3/19 - 11:42
 * @Modify By：
 * @ModifyTime： 2019/3/19
 * @Modify marker：
 */
@Resource
public class UserMapperImpl extends BaseDaoImpl<User,String> implements UserMapper {

    private static Logger logger = LoggerFactory.getLogger(UserMapperImpl.class);

    private final static String GET_ALL_USER_LIST = "getAllUserList";

    @Override
    public List<User> getAllUserList() {
        try {
            return sqlSession.selectList(getSqlName(GET_ALL_USER_LIST));
        } catch (Exception e) {
            logger.error(String.format("查询对象出错！语句：%s", getSqlName(GET_ALL_USER_LIST)), e);
            throw new SystemException(String.format("查询对象出错！语句：%s", getSqlName(GET_ALL_USER_LIST)), e);
        }
    }
}
