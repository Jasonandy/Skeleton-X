package cn.ucaner.skeleton.service.user.dao;

import cn.ucaner.skeleton.common.base.dao.BaseDao;
import cn.ucaner.skeleton.service.user.entity.User;

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
public interface UserMapper extends BaseDao<User, String> {

    /**
     * 获取用户列表
     * @return
     */
    public List<User> getAllUserList();

}