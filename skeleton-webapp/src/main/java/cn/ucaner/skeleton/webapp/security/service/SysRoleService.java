package cn.ucaner.skeleton.webapp.security.service;

import cn.ucaner.skeleton.webapp.security.entity.SysRole;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.security.service
 * @Description： <p> SysRoleService  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 13:57
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 */
public interface SysRoleService {


    /**
     * findByRoleId
     * @param roleId
     * @return00
     */
    SysRole findByRoleId(Long roleId);
}
