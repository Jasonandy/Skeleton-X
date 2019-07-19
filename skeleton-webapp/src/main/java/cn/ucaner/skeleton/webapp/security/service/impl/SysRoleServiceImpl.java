package cn.ucaner.skeleton.webapp.security.service.impl;

import cn.ucaner.skeleton.webapp.security.dao.SysRoleMapper;
import cn.ucaner.skeleton.webapp.security.entity.SysRole;
import cn.ucaner.skeleton.webapp.security.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.security.service.impl
 * @Description： <p> SysRoleServiceImpl  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 13:58
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysRole findByRoleId(Long roleId) {
        return sysRoleMapper.findByRoleId(roleId);
    }
}
