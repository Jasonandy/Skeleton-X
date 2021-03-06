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
package cn.ucaner.skeleton.webapp.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.controller.base
 * @Description： <p> BaseController  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/12 - 15:14
 * @Modify By：
 * @ModifyTime： 2019/7/12
 * @Modify marker：
 */
@Controller
@RequestMapping(value="/base")
public class BaseController {


    /**
     * login page
     * @return
     */
    @GetMapping("/")
    public String login() {
        return "login";
    }

    /**
     * index page
     * @return
     */
    @GetMapping("/login")
    public String index() {
        return "index";
    }
}
