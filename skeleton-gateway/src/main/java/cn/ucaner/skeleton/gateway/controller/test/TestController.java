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
package cn.ucaner.skeleton.gateway.controller.test;

import cn.ucaner.skeleton.common.vo.RespBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.gateway.controller.test
 * @Description： <p> TestController  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 11:18
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 */
@RestController
@RequestMapping(value = "/v1/test")
public class TestController {

    @GetMapping("/token")
    public RespBody justForTest() {
        RespBody respBody = new RespBody();
        respBody.addOK(new Random().nextInt(24),"yes ok .");
        return respBody;
    }

}
