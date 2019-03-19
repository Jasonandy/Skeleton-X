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
public interface UserService extends BaseService {

    /**
     * 获取用户列表
     * @return 用户列表
     */
    List<User> getAllUserList();
}
