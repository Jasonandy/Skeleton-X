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
package cn.ucaner.skeleton.crawler.webmagic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.crawler.webmagic
 * @Description： <p> CnblogPagePageProcessor  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 16:41
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 */
public class CnblogPagePageProcessor implements PageProcessor {

    private static Logger logger = LoggerFactory.getLogger(CnblogPagePageProcessor.class);

    /**
     * 需要爬的网站
     */
    private  static  String CRAWLER_WEBSITE = "https://www.cnblogs.com/jasonandy/p/9821565.html";

    private  static  Integer CRAWLER_THREAD_NUM = 10;
    /**
     * 匹配是否合法地址
     */
    private  static  String PATTERN_WEBSITE = "http://www.cnblogs.com/[a-z 0-9 -]+/p/[0-9]{7}.html";

    /**
     * 抓取网站的相关配置
     *          {
     *              编码  抓取间隔  重试次数
     *          }
     */
    private Site site = Site.me()
            //设置编码 utf-8
            .setCharset("utf-8")
            //设置UserAgent
            .setUserAgent("Skeleton")
            //设置超时时间,单位是毫秒
            .setSleepTime(10000)
            //设置重试次数
            .setRetryTimes(3)
            //添加一条cookie
            //.addCookie(String,String);
            //设置域名,需设置域名后,addCookie才可生效
            //.setDomain(String);
            //添加一条addHeader
            //addHeader(String,String);
            .setSleepTime(100);

    /**
     * 统计count
     */
    private static int count =0;

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        /**
         * 判断链接是否符合http://www.cnblogs.com/任意个数字字母-/p/7个数字.html格式
         */
        if(!page.getUrl().regex(PATTERN_WEBSITE).match()){

            /**
             * 一个站点的页面是很多的,一开始我们不可能全部列举出来,于是如何发现后续的链接,是一个爬虫不可缺少的一部分. - 爬取深度.
             *      page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
             *      用于获取所有满足"(https:/ /github\.com/\w+/\w+)"这个正则表达式的链接
             *      page.addTargetRequests()则将这些链接加入到待抓取的队列中去
             */
            //

            List<String> allPage = page.getHtml().xpath("//*[@id=\"post_list\"]/div/div[@class='post_item_body']/h3/a/@href").all();
            /**
             * 加入满足条件的链接
             */
            page.addTargetRequests(allPage);
        }else{

            String pageStr = page.getHtml().xpath("//*[@id=\"Header1_HeaderTitle\"]/text()").get();
            /**
             *获取页面需要的内容
             */
            logger.info("== 抓取的内容:{} == ",pageStr);
            count ++;
        }
    }

    /**
     * Just for test
     * @param args
     */
    public static void main(String[] args) {
        long startTime, endTime;
        logger.info(" === 爬虫启动开始爬取 == ");
        startTime = System.currentTimeMillis();
        Spider.create(new CnblogPagePageProcessor())
                .addUrl(CRAWLER_WEBSITE)
                //.addRequest(Request...)
                .thread(CRAWLER_THREAD_NUM)
                //设置Downloader，一个Spider只能有个一个Downloader
                //setDownloader(Downloader);
                //设置Scheduler,一个Spider只能有个一个Scheduler 定时任务
                //.setScheduler(Scheduler);
                //添加一个Pipeline,一个Spider可以有多个Pipeline
                //.addPipeline(Pipeline)
                //停止爬虫
                //.stop()
                //异步启动，当前线程继续执行
                //start()/runAsync()
                //启动,会阻塞当前线程执行
                //同步调用，并直接取得结果
                //.get(String);
                .run();

        endTime = System.currentTimeMillis();
        logger.info(" === 爬取结束耗时约 {} 秒 , 总共爬取{}条记录. == ",(endTime - startTime) / 1000,count);
    }
}
