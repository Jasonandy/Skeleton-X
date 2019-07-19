/*******************************************************************************
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
package cn.ucaner.skeleton.webapp.security.detail;

import cn.ucaner.skeleton.webapp.security.entity.SysPermission;
import cn.ucaner.skeleton.webapp.security.entity.SysRole;
import cn.ucaner.skeleton.webapp.security.entity.SysUser;
import cn.ucaner.skeleton.webapp.security.service.SysRoleService;
import cn.ucaner.skeleton.webapp.security.service.SysUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.security.detail
 * @Description： <p> SkeletonUserDetailService  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 11:05
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 */
@Service("skeletonUserDetailService")
public class SkeletonUserDetailService implements UserDetailsService {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.findByUserName(username);
        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            //获取用户的角色集合
            List<SysRole> roles = user.getRoles();
            //遍历角色集合，并获取每个角色拥有的权限
            for (SysRole role : roles) {
                List<SysPermission> permissions = sysRoleService.findByRoleId(role.getRoleId()).getPermissions();
                for (SysPermission permission : permissions) {
                    //为每个授权中心对象写入权限名
                    grantedAuthorities.add(new SimpleGrantedAuthority(permission.getResourceName()));
                }
            }
            /**
             * 此处的user是spring-security中的一个实现了UserDetails接口的user类
             * 因为我们没有将entity中的user去实现UserDetails接口,所以只能在此处调用实现好的构造方法
             */
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        }
        return null;
    }
}
