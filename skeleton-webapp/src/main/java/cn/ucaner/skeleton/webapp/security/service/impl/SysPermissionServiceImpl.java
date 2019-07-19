package cn.ucaner.skeleton.webapp.security.service.impl;

import cn.ucaner.skeleton.webapp.security.dao.SysPermissionMapper;
import cn.ucaner.skeleton.webapp.security.entity.SysPermission;
import cn.ucaner.skeleton.webapp.security.service.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.security.service.impl
 * @Description： <p> SysPermissionServiceImpl </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 14:03
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    private static Logger logger = LoggerFactory.getLogger(SysPermissionServiceImpl.class);

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> selectPermissionList() {
        try {
            return sysPermissionMapper.selectPermissionList();
        } catch (Exception e) {
            logger.info("== selectPermissionList出现异常:{} ===",e.getMessage());
        }
        return null;
    }
}
