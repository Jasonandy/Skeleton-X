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
package cn.ucaner.skeleton.webapp.security.handler.logout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.security.handler.logout
 * @Description： <p> SkeletonLogoutSuccessHandler - 退出后的处理逻辑 </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 10:55
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 */
@Service("skeletonLogoutSuccessHandler")
public class SkeletonLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private final static Logger logger = LoggerFactory.getLogger(SkeletonLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("=== 恭喜退出成功 ===");
        super.onLogoutSuccess(request, response, authentication);
    }
}
