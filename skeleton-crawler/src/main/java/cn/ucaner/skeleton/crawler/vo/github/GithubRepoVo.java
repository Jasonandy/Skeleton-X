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
package cn.ucaner.skeleton.crawler.vo.github;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.example.GithubRepo;
import us.codecraft.webmagic.model.HasKey;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.JsonFilePageModelPipeline;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.crawler.vo.github
 * @Description： <p> GithubRepo </p>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 17:19
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 */
@TargetUrl("https://github.com/\\w+/\\w+")
@HelpUrl({"https://github.com/\\w+\\?tab=repositories","https://github.com/\\w+","https://github.com/explore/*"})
public class GithubRepoVo implements HasKey {

    @ExtractBy(value = "//h1[@class='entry-title public']/strong/a/text()", notNull = true)
    private String name;

    @ExtractByUrl("https://github\\.com/(\\w+)/.*")
    private String author;

    @ExtractBy("//div[@id='readme']")
    private String readme;

    @ExtractBy(value = "//div[@class='repository-lang-stats']//li//span[@class='lang']",multi = true)
    private List<String> language;

    @ExtractBy("//a[@class='social-count js-social-count']/text()")
    private String star;

    @ExtractBy("//a[@class='social-count js-social-count']/text()")
    private String fork;

    @ExtractByUrl
    private String url;


    @Override
    public String key() {
        return author+":"+name;
    }

    public String getName() {
        return name;
    }

    public String getReadme() {
        return readme;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getLanguage() {
        return language;
    }

    public String getUrl() {
        return url;
    }

    public String getStar() {
        return star;
    }

    public String getFork() {
        return fork;
    }


    /**
     * just for test
     * @param args
     */
    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(0).setRetryTimes(3),
                new JsonFilePageModelPipeline(), GithubRepo.class)
                .addUrl("https://github.com/explore")
                .setScheduler(new FileCacheQueueScheduler("/data/webmagic/cache/")).thread(15).run();
    }

}
