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
package cn.ucaner.skeleton.webapp.controller.security;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.controller.security
 * @Description： <p> AccountController </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 15:22
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    private final static Logger logger = LoggerFactory.getLogger(AccountController.class);


    @ApiOperation(value = "登录页")
    @RequestMapping("/login.do")
    public String loginAuth(Authentication authentication){
        logger.info("== account:login.do:{} ==",authentication);
        if(authentication == null){
            return "login";
        }
        return "index";
    }

}
