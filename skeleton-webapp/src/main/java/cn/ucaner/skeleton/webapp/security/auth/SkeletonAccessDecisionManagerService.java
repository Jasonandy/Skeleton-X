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
package cn.ucaner.skeleton.webapp.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.security.auth
 * @Description： <p> 自定义权限控制 </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 11:12
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 */
@Service("skeletonAccessDecisionManagerService")
public class SkeletonAccessDecisionManagerService implements AccessDecisionManager {

    private final static Logger logger = LoggerFactory.getLogger(SkeletonAccessDecisionManagerService.class);

    /**
     * @param authentication UserService中循环添加到GrantedAuthority中的权限信息集合
     * @param o         包含客户端发起的请求的request信息，可以转换为HTTPRequest
     * @param collection     url所需的权限集合
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection)
            throws AccessDeniedException, InsufficientAuthenticationException {
        //判断URL所需的权限集合是否为空,为空则放行
        if (null == collection || collection.size() <= 0) {
            return;
        }
        String needPermission;
        for (ConfigAttribute c : collection) {
            //获得所需的权限
            needPermission = c.getAttribute();
            //遍历用户拥有的权限与URL所需的权限进行对比
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needPermission.trim().equals(ga.getAuthority())){
                    return;
                }
            }
        }
        logger.info("== 没有权限策略无权限 ==");
        throw new AccessDeniedException("Sorry you no permission thanks.");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
