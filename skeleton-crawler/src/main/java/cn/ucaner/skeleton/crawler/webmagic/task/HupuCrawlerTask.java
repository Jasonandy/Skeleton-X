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
package cn.ucaner.skeleton.crawler.webmagic.task;

import cn.ucaner.skeleton.crawler.webmagic.pipeline.HupuSpiderPipeline;
import cn.ucaner.skeleton.crawler.webmagic.process.HupuPageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import javax.annotation.Resource;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.crawler.webmagic.scheduler
 * @Description： <p> HupuCrawlerTask  </p>
 * <docs>
 *     http://webmagic.io/docs/zh/posts/ch6-custom-componenet/scheduler.html
 * </docs>
 * @Author： - Jason
 * @CreatTime：2019/4/28 - 11:08
 * @Modify By：
 * @ModifyTime： 2019/4/28
 * @Modify marker：
 */
@Component
@EnableScheduling
public class HupuCrawlerTask {

    private final Logger logger = LoggerFactory.getLogger(HupuCrawlerTask.class);

    public static final String BASE_URL = "https://bbs.hupu.com/bxj-1";

    /**
     * HupuSpiderPipeline
     */
    @Resource
    private HupuSpiderPipeline hupuSpiderPipeline;

    /**
     * 定时爬取数据
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void hupuTask(){
        logger.info("=== Hupu Schedluer Starting Url:{} ==",BASE_URL);
        long startTime, endTime;
        startTime = System.currentTimeMillis();
        try {
            Spider spider = Spider.create(new HupuPageProcessor());
            spider.addUrl(BASE_URL);
            spider.addPipeline(hupuSpiderPipeline);
            spider.thread(5);
            /**
             * 数据过滤 - 所有默认的Scheduler都使用HashSetDuplicateRemover来进行去重.
             *           666666 是估计的页面数量
             */
            spider.setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(666666)));
            spider.setExitWhenComplete(true);
            spider.start();
            spider.stop();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        endTime = System.currentTimeMillis();
        logger.info("=== Hupu end cost Time:{}秒 , 数据存入到库. ===",((endTime - startTime) / 1000));
    }


}
