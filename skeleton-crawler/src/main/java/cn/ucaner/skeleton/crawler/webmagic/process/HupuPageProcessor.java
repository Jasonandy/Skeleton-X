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
package cn.ucaner.skeleton.crawler.webmagic.process;

import cn.ucaner.skeleton.crawler.webmagic.utils.ProxyGeneratedUtil;
import cn.ucaner.skeleton.crawler.webmagic.utils.URLGeneratedUtil;
import cn.ucaner.skeleton.crawler.webmagic.utils.UserAgentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Random;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.crawler.webmagic.process
 * @Description： <p> HupuPageProcessor  </p>
 * <docs>
 *     http://webmagic.io/docs/zh/posts/ch4-basic-page-processor/pageprocessor.html
 * </docs>
 * @Author： - Jason
 * @CreatTime：2019/4/28 - 10:01
 * @Modify By：
 * @ModifyTime： 2019/4/28
 * @Modify marker：
 */
public class HupuPageProcessor implements PageProcessor {

    private static Logger logger = LoggerFactory.getLogger(HupuPageProcessor.class);

    public static final String URL_LIST = "https://bbs\\.hupu\\.com/bxj-\\d+";
    public static final String URL_POST = "https://bbs\\.hupu\\.com/\\d+\\-\\d+.html";
    public static final String URL_POST_1 = "https://bbs\\.hupu\\.com/\\d+\\.html";
    public static final String URL_USER = "https://my\\.hupu\\.com/\\d+";
    public static final int URL_LENGTH = "https://bbs.hupu.com/bxj-".length();

    /**
     * 讯代理订单号
     */
    private static final String ORDER_NUM = "ZF201710169692T66jkr";

    /**
     * 讯代理密码
     */
    private static final String SECRET = "3b23ace31a2447baa44d624e9c5fd0f5";

    /**
     * 抓取网站的相关配置，包括编码、抓取间隔、重试次数、代理、UserAgent等
     */
    private Site site = Site.me()
            /**
             * 设置代理
             */
            .addHeader("Proxy-Authorization", ProxyGeneratedUtil.authHeader(ORDER_NUM, SECRET, (int) (System.currentTimeMillis()/1000)))
            .setDisableCookieManagement(true)
            .setCharset("UTF-8")
            .setTimeOut(30000)
            .setRetryTimes(3)
            .setSleepTime(new Random().nextInt(20)*100)
            .setUserAgent(UserAgentUtil.getRandomUserAgent());


    /**
     * process
     * @param page page信息
     */
    @Override
    public void process(Page page) {
        logger.info("=== pageInfoUrl:{} ===",page.getUrl());
        //列表页
        if (page.getUrl().regex(URL_LIST).match()) {
            crawlList(page);
        }
        //主题帖第一页
        if (page.getUrl().regex(URL_POST_1).match()) {
            crawlPost(page);
        }
        //主题帖第一页以后
        if (page.getUrl().regex(URL_POST).match()) {
            crawlPost(page);
        }
        //用户页
        if (page.getUrl().regex(URL_USER).match()) {
            crawlUser(page);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 抓取论坛列表页信息
     * @param page 当前页面对象
     */
    private void crawlList(Page page) {
        List<String> listUrls = page.getHtml().links().regex("/\\d+\\.html").all();
        listUrls.forEach(e -> URLGeneratedUtil.generatePostURL(e));
        /**
         * 把所有帖子页的URL加入抓取队列
         */
        page.addTargetRequests(listUrls);
        listUrls.forEach(e -> page.addTargetRequests(URLGeneratedUtil.generatePostURLs(e)));
        String url = page.getUrl().toString();
        /**
         * 把所有列表页加入抓取队列
         */
        page.addTargetRequest(url.substring(0,URL_LENGTH).concat((Integer.parseInt(url.substring(URL_LENGTH)) + 1) + ""));
    }

    /**
     * 抓取主题帖内信息
     * @param page 当前页面对象
     */
    private void crawlPost(Page page) {
        /**
         * 把所有用户主页的URL加入抓取队列
         */
        page.addTargetRequests(page.getHtml().links().regex(URL_USER).all());
        //帖子标题
        String title = page.getHtml().xpath("//div[@class='bbs-hd-h1']/h1[@id='j_data']/text()").toString();
        //帖子作者
        String postAuthor = page.getHtml().xpath("//div[@class='author']//a[@class='u'][1]/text()").toString();
        //回复数
        int replyNum = Integer.parseInt(page.getHtml().xpath("//div[@class='bbs-hd-h1']/span[1]/span[1]/text()").toString().replaceAll("[\\u4e00-\\u9fa5]+", ""));
        setPost(title, postAuthor, replyNum, page);
        setTitleWord(title, page);
        //评论内容
        List<String> contentList = page.getHtml().xpath("//div[@class='w_reply clearfix']//td/text()").all();
        //评论点亮数
        List<String> litNumList = page.getHtml().xpath("//div[@class='w_reply clearfix']//span[@class='ilike_icon_list']/span[@class='stime']/text()").all();
        //评论作者
        List<String> commentAuthors = page.getHtml().xpath("//div[@class='w_reply clearfix']//div[@class='author']//a[@class='u']/text()").all();
        setComment(title,contentList, litNumList, commentAuthors, page);
    }

    /**
     * 抓取各用户主页
     * @param page 当前页面对象
     */
    private void crawlUser(Page page) {
        //用户名
        String name = page.getHtml().xpath("//div[@itemprop='name']/text()").toString();
        //用户所在地
        String address = page.getHtml().xpath("//div[@class='personalinfo']//span[@itemprop='address']/text()").toString();
        //用户主队
        String homeTeam = page.getHtml().xpath("//div[@class='personalinfo']//span[@itemprop='affiliation'][1]/a/text()").toString();
        //用户性别
        String gender = page.getHtml().xpath("//div[@class='personalinfo']//span[@itemprop='gender']/text()").toString();
        //用户被访问量
        int views = Integer.parseInt(page.getHtml().xpath("//div[@class='personal']//span[@class='f666'][1]/text()").toString().replaceAll("[^0-9]", ""));

        setUser(name, address, homeTeam, gender, views, page);
    }

    /**
     * 将抓取到的主题帖post信息传给pipeline进行后续处理
     * @param title 帖子标题
     * @param author 帖子作者
     * @param replyNum 帖子回复数
     * @param page 当前页面对象
     */
    private void setPost(String title, String author, int replyNum, Page page) {
        logger.info("setPost-title:{},author:{},replyNum:{}",title,author,replyNum);
    }

    /**
     * 将主题帖下的热评comment传给pipeline进行后续处理 - 存放帖子下的热评信息
     * @param title 热评所在主题帖的标题
     * @param contentList 热评列表
     * @param litNumList 评论点亮数列表
     * @param commentAuthors 评论作者列表
     * @param page 当前页面对象
     */
    private void setComment(String title, List<String> contentList, List<String> litNumList, List<String> commentAuthors, Page page) {
        logger.info("setComment-title:{},contentList:{},litNumList:{},commentAuthors:{}",title,contentList,litNumList,commentAuthors);
    }

    /**
     * 将主题帖标题的分词信息传给pipeline进行后续处理 - 存放抓取的帖子标题的分词
     * @param title 主题帖标题
     * @param page 当前页面对象
     */
    private void setTitleWord(String title, Page page) {
        logger.info("setTitleWord-title:{}",title);
    }

    /**
     * 将抓取到的用户信息user传给pipeline进行后续处理
     * @param name 用户名
     * @param address 所在地
     * @param homeTeam NBA主队
     * @param gender 性别
     * @param views 用户主页访问量
     * @param page 当前页面对象
     */
    private void setUser(String name, String address, String homeTeam, String gender, int views, Page page) {
        logger.info("setUser-title:{},name:{},address:{},gender:{},views:{}",name,address,gender,views);
    }

    public static void main(String[] args) {
        String xunProxyStr = ProxyGeneratedUtil.authHeader(ORDER_NUM, SECRET, (int) (System.currentTimeMillis() / 1000));
        logger.info("== 讯代理:{} ==",xunProxyStr);
    }

}
