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

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.crawler.webmagic.utils
 * @Description： <p> ProxyGeneratedUtil </p>
 * @Author： - Jason
 * @CreatTime：2019/4/28 - 10:03
 * @Modify By：
 * @ModifyTime： 2019/4/28
 * @Modify marker：
 */
public class ProxyGeneratedUtil {

    public static String authHeader(String orderno, String secret, int timestamp){
        /**
         * 拼装签名字符串
         */
        String planText = String.format("orderno=%s,secret=%s,timestamp=%d", orderno, secret, timestamp);
        /**
         * 计算签名
         */
        String sign = org.apache.commons.codec.digest.DigestUtils.md5Hex(planText).toUpperCase();

        /**
         * 拼装请求头Proxy-Authorization的值
         */
        String authHeader = String.format("sign=%s&orderno=%s&timestamp=%d", sign, orderno, timestamp);
        return authHeader;
    }

}
