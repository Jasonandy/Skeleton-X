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
package cn.ucaner.skeleton.webapp.websocket.controller;

import cn.ucaner.skeleton.common.vo.RespBody;
import cn.ucaner.skeleton.webapp.websocket.server.WebSocketServer;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.websocket
 * @Description： <p> WebSocketController  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/1 - 14:56
 * @Modify By：
 * @ModifyTime： 2019/7/1
 * @Modify marker：
 */
@Controller
@RequestMapping(value="/ws")
public class WebSocketController {

    private static Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    /**
     * 页面请求
     * @param cid
     * @return
     */
    @ApiOperation(value = "页面请求")
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mv = new ModelAndView("/ws/ws");
        mv.addObject("cid", cid);
        return mv;
    }


    @ApiOperation(value = "handler请求")
    @GetMapping("/socket/handler")
    public ModelAndView handlerSocket() {
        ModelAndView mv = new ModelAndView("/ws/handler");
        return mv;
    }


    /**
     * 推送数据接口
     * @param cid
     * @param message
     * @return
     */
    @ApiOperation(value = "推送数据接口")
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public RespBody pushToWeb(@PathVariable String cid, String message) {
        RespBody respBody = new RespBody();
        try {
            logger.info(">>> 推送信息:Sid:{},Msg:{} ...",cid,message);
            WebSocketServer.sendInfo(message,cid);
            respBody.addOK(cid,message);
        } catch (IOException e) {
            respBody.addError("==推送异常===");
            logger.error("=== error:{} ===",e.getMessage());
        }
        return respBody;
    }

}
