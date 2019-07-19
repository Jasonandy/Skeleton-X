package cn.ucaner.skeleton.webapp.security.service.impl;

import cn.ucaner.skeleton.webapp.security.dao.SysUserMapper;
import cn.ucaner.skeleton.webapp.security.entity.SysUser;
import cn.ucaner.skeleton.webapp.security.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.security.service.impl
 * @Description： <p> SysUserServiceImpl </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 13:56
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findByUserName(String userName) {
        return sysUserMapper.findByUsername(userName);
    }
}
