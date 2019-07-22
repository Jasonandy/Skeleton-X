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
package cn.ucaner.skeleton.webapp.controller.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.controller.page
 * @Description： <p> PageController - 初始页面路由  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 15:20
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 */
@Controller
@RequestMapping("/page")
public class PageController {

    private final static Logger logger = LoggerFactory.getLogger(PageController.class);

    private final static String DEFAULT_PAGE = "page/security";
    private final static String DEFAULT_PAGE_ATTRIBUTE = "pageName";


    @RequestMapping("/index")
    public String pageIndex(ModelMap map){
        map.addAttribute(DEFAULT_PAGE_ATTRIBUTE,"首页");
        map.addAttribute("name","Skeleton-Spring-Security");
        logger.info("== pageIndex ==");
        return "index/index";
    }

    @RequestMapping("/logout")
    public String pageLogout(ModelMap map){
        logger.info("== pageLogout ==");
        map.addAttribute(DEFAULT_PAGE_ATTRIBUTE,"登出");
        return DEFAULT_PAGE;
    }

    @RequestMapping("/auth/error")
    public String pageAuthError(ModelMap map){
        logger.info("== pageAuthError ==");
        map.addAttribute(DEFAULT_PAGE_ATTRIBUTE,"授权失败");
        return DEFAULT_PAGE;
    }

    @RequestMapping("/invalid/session")
    public String pageInvalidSession(ModelMap map){
        logger.info("== pageInvalidSession ==");
        map.addAttribute(DEFAULT_PAGE_ATTRIBUTE,"无效Session");
        return DEFAULT_PAGE;
    }

    @RequestMapping("/expire")
    public String pageExpire(ModelMap map){
        logger.info("== pageExpire ==");
        map.addAttribute(DEFAULT_PAGE_ATTRIBUTE,"登录过期");
        return DEFAULT_PAGE;
    }

    @RequestMapping("/access/denied")
    public String pageAccessDenied(ModelMap map){
        logger.info("== pageAccessDenied ==");
        map.addAttribute(DEFAULT_PAGE_ATTRIBUTE,"无权限访问");
        return DEFAULT_PAGE;
    }

}
