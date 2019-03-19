package cn.ucaner.skeleton.service.user.service.impl;

import cn.ucaner.skeleton.common.base.dao.BaseDao;
import cn.ucaner.skeleton.common.base.service.impl.BaseServiceImpl;
import cn.ucaner.skeleton.service.user.dao.UserMapper;
import cn.ucaner.skeleton.service.user.entity.User;
import cn.ucaner.skeleton.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl extends BaseServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    protected BaseDao getDao() {
        return userMapper;
    }

    @Override
    public List<User> getAllUserList() {
        return userMapper.getAllUserList();
    }

    @Override
    public void deleteByIds(List ids) {

    }
}
