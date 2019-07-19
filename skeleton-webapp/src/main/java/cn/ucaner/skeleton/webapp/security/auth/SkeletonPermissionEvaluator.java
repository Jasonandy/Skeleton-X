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

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.security.auth
 * @Description： <p> SkeletonPermissionEvaluator - 定制标签 </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 11:21
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 */
@Service("skeletonPermissionEvaluator")
public class SkeletonPermissionEvaluator implements PermissionEvaluator {

    private final static String SKIP = "skip";

    private final static String PERMISSION = "permission";

    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        if (SKIP.equals(o)) {
            return true;
        }
        if (PERMISSION.equals(o)) {
            return this.hasPermission(authentication, o1);
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return true;
    }

    /**
     * 判断是否拥有权限 - 匹配字符串
     * @param authentication
     * @param permission
     * @return
     */
    private boolean hasPermission(Authentication authentication, Object permission) {
//        Collection<MyGrantedAuthority> authorities = (Collection<MyGrantedAuthority>) authentication.getAuthorities();
//        for (MyGrantedAuthority authority : authorities) {
//            if (authority.equals(permission)) {
//                return true;
//            }
//        }
        return false;
    }

}
