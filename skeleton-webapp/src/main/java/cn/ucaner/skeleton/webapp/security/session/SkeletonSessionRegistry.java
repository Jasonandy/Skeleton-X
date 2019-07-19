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
package cn.ucaner.skeleton.webapp.security.session;

import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Service;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.security.session
 * @Description： <p> SkeletonSessionRegistry  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 11:07
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 */
@Service("skeletonSessionRegistry")
public class SkeletonSessionRegistry extends SessionRegistryImpl {

    @Override
    public void registerNewSession(String sessionId, Object principal) {
        super.registerNewSession(sessionId, principal);
    }

    @Override
    public void removeSessionInformation(String sessionId) {
        super.removeSessionInformation(sessionId);
    }

    @Override
    public void refreshLastRequest(String sessionId) {
        super.refreshLastRequest(sessionId);
    }
}
