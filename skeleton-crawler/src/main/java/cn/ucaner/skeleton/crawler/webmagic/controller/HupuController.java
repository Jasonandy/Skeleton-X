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
package cn.ucaner.skeleton.crawler.webmagic.controller;

import cn.ucaner.skeleton.common.vo.RespBody;
import cn.ucaner.skeleton.crawler.webmagic.downloader.HupuProxyProvider;
import cn.ucaner.skeleton.crawler.webmagic.pipeline.HupuSpiderPipeline;
import cn.ucaner.skeleton.crawler.webmagic.process.HupuPageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.crawler.webmagic.controller
 * @Description： <p> HupuController  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/28 - 10:16
 * @Modify By：
 * @ModifyTime： 2019/4/28
 * @Modify marker：
 */
@RestController
@RequestMapping(value = "/webmagic")
public class HupuController {

    private static Logger logger = LoggerFactory.getLogger(HupuController.class);

    @Autowired
    HupuSpiderPipeline hupuSpiderPipeline;

    @GetMapping("/hupu")
    public RespBody hupuStartUp() {
        RespBody respBody = new RespBody();
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();

        /**
         * 设置动态转发代理,使用定制的ProxyProvider
         */
        httpClientDownloader.setProxyProvider(HupuProxyProvider.from(new Proxy("forward.xdaili.cn", 80)));

        Spider.create(new HupuPageProcessor())
                .addUrl("https://bbs.hupu.com/bxj-1")
                /**
                 * centos ip 访问监控  - iftop - 显示代理有效
                 */
                //.addUrl("http://ucaner.cn/")
                .addPipeline(hupuSpiderPipeline)
                .thread(4)
                .run();
        respBody.addOK("== Hupu Crawer Starting ...");
        return respBody;
    }

    @GetMapping("/testIp")
    public RespBody testIp(HttpServletRequest request) {
        RespBody respBody = new RespBody();
        String remoteAddr = request.getRemoteAddr();
        logger.info("== testIp:{} ==",remoteAddr);
        respBody.addOK(new Random().nextInt(24),"yes ok .");
        return respBody;
    }

}
