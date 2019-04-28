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

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.crawler.webmagic.utils
 * @Description： <p> IPCheckUtil  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/28 - 9:56
 * @Modify By：
 * @ModifyTime： 2019/4/28
 * @Modify marker：
 */
public class IPCheckUtil {

    /**
     * 校验代理IP的有效性，测试地址为：http://www.ip138.com
     * @param ip 代理IP地址
     * @param port  代理IP端口
     * @return  此代理IP是否有效
     */
    public static boolean checkValidIP(String ip,Integer port){
        URL url ;
        HttpURLConnection connection = null;
        try {
            url = new URL("http://www.ip138.com");
            /**
             * 代理服务器
             */
            InetSocketAddress proxyAddr = new InetSocketAddress(ip, port);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddr);
            connection = (HttpURLConnection) url.openConnection(proxy);
            connection.setReadTimeout(4000);
            connection.setConnectTimeout(4000);
            connection.setRequestMethod("GET");

            if(connection.getResponseCode() == 200){
                connection.disconnect();
                return true;
            }
        } catch (Exception e) {
            connection.disconnect();
            return false;
        }
        return false;
    }
}
