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
package cn.ucaner.skeleton.crawler.webmagic.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.crawler.webmagic.utils
 * @Description： <p> URLGeneratedUtil  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/28 - 10:07
 * @Modify By：
 * @ModifyTime： 2019/4/28
 * @Modify marker：
 */
public class URLGeneratedUtil {

    public final static String PREFIX = "https://bbs.hupu.com";

    /**
     * 爬取的起始目录页位置
     */
    public final static int START_POS = 2;


    /**
     * 爬取的目录页页数
     */
    public final static int END_POS = 10;


    /**
     * generatePostURLs
     * @param postURL
     * @return
     */
    public static List<String> generatePostURLs(String postURL) {
        List<String> urls = new ArrayList<>();
        for(int i = START_POS; i <= END_POS; i++) {
            urls.add(postURL.substring(0, postURL.length() - 5) + "-" + i + ".html");
        }
        return urls;
    }

    /**
     * generatePostURL
     * @param url
     * @return
     */
    public static String generatePostURL(String url) {
        return PREFIX.concat(url);
    }

    public static void main(String[] args) {
        System.out.println(generatePostURL("https://bbs.hupu.com/27087739.html"));
    }


}
