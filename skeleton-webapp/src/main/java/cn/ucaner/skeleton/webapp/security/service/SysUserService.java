package cn.ucaner.skeleton.webapp.security.service;

import cn.ucaner.skeleton.webapp.security.entity.SysUser;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.security.service
 * @Description： <p> SysUserService </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 13:56
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 */
public interface SysUserService {

    /**
     *  根据userName查询用户
     * @param userName
     * @return
     */
    SysUser findByUserName(String userName);

}
