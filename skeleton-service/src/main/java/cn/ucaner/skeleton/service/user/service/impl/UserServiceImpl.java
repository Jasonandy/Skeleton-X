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
package cn.ucaner.skeleton.service.user.service.impl;

import cn.ucaner.skeleton.common.base.dao.BaseDao;
import cn.ucaner.skeleton.common.base.service.impl.BaseServiceImpl;
import cn.ucaner.skeleton.service.user.dao.UserMapper;
import cn.ucaner.skeleton.service.user.entity.User;
import cn.ucaner.skeleton.service.user.repository.UserEsRepository;
import cn.ucaner.skeleton.service.user.service.UserService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.user.service.impl
 * @Description： <p> TODO </p>
 * @Author： - Jason
 * @CreatTime：2019/3/19 - 11:49
 * @Modify By：
 * @ModifyTime： 2019/3/19
 * @Modify marker：
 */
@Service(value = "UserService")
public class UserServiceImpl extends BaseServiceImpl implements UserService<User,String>{

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    UserMapper userMapper;

    @Resource
    UserEsRepository userEsRepository;

    @Override
    protected BaseDao getDao() {
        return userMapper;
    }

    @Override
    public List<User> getAllUserList() {
        return userMapper.getAllUserList();
    }

    @Override
    public User findEsById(String id) {
        if (null == id) {
            return null;
        }
        return userEsRepository.findById(id).get();
    }

    @Override
    public User saveByEs(User user) {
        if (null == user) {
            return null;
        }
        logger.info("--- user:save2es :{} ---", JSON.toJSONString(user));
        return userEsRepository.save(user);
    }


    @Override
    public List<User> findAllByEs() {
        List<User> users = new ArrayList<User>();
        userEsRepository.findAll().forEach(item -> {
            users.add(item);
        });
        return users;
    }

    @Override
    public void deleteByIds(List ids) {
    }
}
