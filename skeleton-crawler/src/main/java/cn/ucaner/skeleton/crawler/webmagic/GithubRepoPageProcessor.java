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

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.crawler.webmagic
 * @Description： <p> GithubRepoPageProcessor  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 15:53
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 */
public class GithubRepoPageProcessor implements PageProcessor {

    /**
     * AUTHOR
     */
    private  final static  String AUTHOR = "author";

    /**
     * NAME
     */
    private  final static  String NAME = "name";


    /**
     * FILE_PATH
     */
    private  final static  String FILE_PATH = "D:\\webmagic\\";

    /**
     * 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
     */
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    /**
     *  process是定制爬虫逻辑的核心接口 在这里编写抽取逻辑
     * @param page
     */
    @Override
    public void process(Page page) {

        /**
         * 部分三：从页面发现后续的url地址来抓取
         */
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());

        /**
         * 部分二：定义如何抽取页面信息，并保存下来
         */
        page.putField(AUTHOR, page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField(NAME, page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name")==null){
            page.setSkip(true);
        }

        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }


    /**
     * just for test
     * @param args
     */
    public static void main(String[] args) {
        /**
         *  从 "https://github.com/jasonandy"开始抓
         */
        Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://www.cnblogs.com/jasonandy/")
                .addPipeline(new JsonFilePipeline(FILE_PATH))
                .thread(5)
                .run();
    }
}
