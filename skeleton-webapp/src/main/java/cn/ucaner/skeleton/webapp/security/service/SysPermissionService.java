package cn.ucaner.skeleton.webapp.security.service;

import cn.ucaner.skeleton.webapp.security.entity.SysPermission;

import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.security.service
 * @Description： <p> SysPermissionService  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 14:02
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 */
public interface SysPermissionService {

    /**
     * 查询权限列表
     * @return
     */
    List<SysPermission> selectPermissionList();


}
